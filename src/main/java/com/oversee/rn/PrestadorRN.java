package com.oversee.rn;

import com.oversee.dto.LoginDTO;
import com.oversee.dto.PrestadorDTO;
import com.oversee.entity.Prestador;
import com.oversee.exception.RegraDeNegocioException;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.Optional;

@ApplicationScoped
public class PrestadorRN {

    @Transactional
    public Boolean criarNovoPrestador(PrestadorDTO prestador) {
        if(prestador.getCpf() != null && !prestadorJaCadastrado(prestador.getCpf())){ //Prestador precisa ter cpf e não pode já estar cadastrado
            Prestador novoPrestador = new Prestador(
                    prestador.getNome(),
                    prestador.getCpf(),
                    prestador.getEmail(),
                    prestador.getDataNascimento(),
                    prestador.getTelefone(),
                    prestador.getSobrenome(),
                    prestador.getSenha()
            );
            novoPrestador.persist();
            return true;
        }
        return false;
    }

    @Transactional
    public PrestadorDTO buscarPrestadorDTO(Long id){
        Prestador prestador = Prestador.findById(id);
        if(prestador != null){
            return new PrestadorDTO(prestador);
        }else{
            return null;
        }
    }

    public Prestador buscarPrestador(Long id) throws RegraDeNegocioException {
        Prestador prestador  = Prestador.findById(id);
        if(prestador != null){
            return prestador;
        }else{
            throw new RegraDeNegocioException("Prestador não encontrado");
        }
    }

    public Prestador buscarPrestadorPorCpf(String cpf){
        String hql = "FROM Prestador WHERE cpf = :CPF";
        Optional<Prestador> prestador = Prestador.find(hql, Parameters.with("CPF", cpf)).singleResultOptional();

        return prestador.orElse(null);
    }

    public PrestadorDTO validarLogin(LoginDTO login){
        Prestador prestador = buscarPrestadorPorCpf(login.getCpf());
        if(prestador != null && BcryptUtil.matches(login.getSenha(), prestador.getSenha())){
            return new PrestadorDTO(prestador);
        }
        return null;
    }

    @Transactional
    public Boolean prestadorJaCadastrado(String cpf){
        String hql = "SELECT p FROM Prestador p WHERE p.cpf = :cpf";

        return Prestador.find(hql, Parameters.with("cpf", cpf)).count() != 0;
    }
}
