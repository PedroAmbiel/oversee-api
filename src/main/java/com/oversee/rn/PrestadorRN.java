package com.oversee.rn;

import com.oversee.dto.PrestadorDTO;
import com.oversee.entity.Prestador;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

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
    public PrestadorDTO buscarPrestador(Long id){
        Prestador prestador = Prestador.findById(id);

        return new PrestadorDTO(prestador);
    }

    @Transactional
    public Boolean prestadorJaCadastrado(String cpf){
        String hql = "SELECT p FROM Prestador p WHERE p.cpf = :cpf";

        return Prestador.find(hql, Parameters.with("cpf", cpf)).count() != 0;
    }
}
