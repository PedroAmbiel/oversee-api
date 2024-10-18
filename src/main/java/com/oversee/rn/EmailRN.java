package com.oversee.rn;

import com.oversee.entity.Prestador;
import com.oversee.entity.RedefinicaoDeSenha;
import com.oversee.exception.RegraDeNegocioException;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.Optional;

@ApplicationScoped
public class EmailRN {

    @Inject
    PrestadorRN prestadorRN;
    @Inject
    Mailer mail;

    @Transactional
    public RedefinicaoDeSenha criarPedidoRedefinicaoSenha(String cpf, String ipRequisitou) throws Exception {
        Prestador prestador = prestadorRN.buscarPrestadorPorCpf(cpf);
        if(prestador != null){
            try{
                RedefinicaoDeSenha redefinicaoDeSenha = new RedefinicaoDeSenha(prestador, ipRequisitou);
                redefinicaoDeSenha.persistAndFlush();
                return redefinicaoDeSenha;
            }catch (Exception e){
                throw new Exception("Não foi possivel solicitar nova senha");
            }
        }else{
            return null;
        }
    }

    public Prestador buscarIdentificadorSenha(String identificador){
        String hql = "SELECT p FROM RedefinicaoDeSenha r " +
                "JOIN r.prestador p " +
                "WHERE r.identificador = :IDENTIFICADOR " +
                "AND r.ativo = true " +
                "ORDER BY r.id DESC " +
                "LIMIT 1 ";

        Optional<Prestador> prestador = Prestador.find(hql, Parameters.with("IDENTIFICADOR", identificador)).singleResultOptional();

        return prestador.orElse(null);
    }

    @Transactional
    public void finalizarPedidoRedefinicaoSenha(String identificador) throws RegraDeNegocioException {
        String hql = "FROM RedefinicaoDeSenha WHERE identificador = :IDENTIFICADOR AND ativo = TRUE";

        Optional<RedefinicaoDeSenha> redefinicao = RedefinicaoDeSenha.find(hql, Parameters.with("IDENTIFICADOR", identificador)).singleResultOptional();

        if(redefinicao.isPresent()){
            redefinicao.get().setAtivo(false);
            redefinicao.get().persistAndFlush();
        }else{
            throw new RegraDeNegocioException("Não foi possivel finalizar o pedido de senha!");
        }

    }

    public void enviarEmailRedefinicaoSenha(RedefinicaoDeSenha pedido){
        mail.send(Mail.withText(pedido.getPrestador().getEmail(),
                "Oversee - Redefinição de Senha",
                "Acesse o link para redefinir a senha http://localhost:3000/trocar-senha?identificador="+pedido.getIdentificador()));

    }

}
