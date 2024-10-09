package com.oversee.rn;

import com.oversee.entity.Prestador;
import com.oversee.entity.RedefinicaoDeSenha;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

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

    public void enviarEmailRedefinicaoSenha(RedefinicaoDeSenha pedido){
        mail.send(Mail.withText(pedido.getPrestador().getEmail(),
                "Oversee - Redefinição de Senha",
                "Acesse o link para redefinir a senha http://localhost:8080/redefinir-senha?identificador="+pedido.getIdentificador()));

    }

}
