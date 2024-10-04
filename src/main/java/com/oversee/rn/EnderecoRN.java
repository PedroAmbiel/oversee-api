package com.oversee.rn;

import com.oversee.dto.EnderecoDTO;
import com.oversee.entity.Empresa;
import com.oversee.entity.Endereco;
import com.oversee.exception.RegraDeNegocioException;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class EnderecoRN {

    public boolean cadastrarEndereco(EnderecoDTO enderecoDTO) {
        Endereco novoEndereco = new Endereco(
                enderecoDTO.getLogradouro(),
                enderecoDTO.getNumero(),
                enderecoDTO.getBairro(),
                enderecoDTO.getCidade(),
                enderecoDTO.getEstado(),
                enderecoDTO.getCep(),
                enderecoDTO.getComplemento());

        novoEndereco.persist();
        return true;

    }

    public Endereco buscarEnderecoPorEmpresa(Long idEmpresa) throws RegraDeNegocioException {
        String hql = "FROM Empresa e WHERE e.idEmpresa = :IDEMPRESA";

        Optional<Endereco> endereco = Endereco.find(hql, Parameters.with("IDEMPRESA", idEmpresa)).singleResultOptional();

        if(endereco.isPresent()) {
            return endereco.get();
        }else{
            throw new RegraDeNegocioException("Endereço não encontrado");
        }

    }
}
