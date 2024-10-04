package com.oversee.rn;

import com.oversee.dto.EmpresaDTO;
import com.oversee.dto.EnderecoDTO;
import com.oversee.entity.Cliente;
import com.oversee.entity.Empresa;
import com.oversee.entity.Endereco;
import com.oversee.exception.RegraDeNegocioException;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.Optional;


@ApplicationScoped
public class EmpresaRN {

    @Inject
    PrestadorRN prestadorRN;

    @Inject
    EnderecoRN enderecoRN;

    @Transactional
    public boolean inserirNovaEmpresa(EmpresaDTO empresa) throws RegraDeNegocioException {
        try {
            Empresa novaEmpresa = new Empresa(
                    empresa.getRazaoSocial(),
                    empresa.getNomeFantasia(),
                    empresa.getCnpj(),
                    empresa.getDataAbertura(),
                    prestadorRN.buscarPrestador(empresa.getFkPrestador().longValue()),
                    empresa.getEmail(),
                    empresa.getTelefone(),
                    new Endereco(empresa.getEndereco()));

            if (!verificarEmpresaJaCadastrada(novaEmpresa)) {
                novaEmpresa.persist();
                return true;
            } else {
                return false;
            }
        }catch (RegraDeNegocioException ex){
            System.out.println(ex.getMessage());
            throw new RegraDeNegocioException("Prestador não encontrado");
        }
    }

    public EmpresaDTO buscarEmpresaPorCPNJ(String cnpj) throws RegraDeNegocioException {
        String hql = "from Empresa where cpnj = :CNPJ";

        Optional<Empresa> empresa = Empresa.find(hql, Parameters.with("CNPJ", cnpj)).singleResultOptional();

        if(empresa.isEmpty()){
            throw new RegraDeNegocioException("Empresa não encontrada");
        }

        try{
            EmpresaDTO empresaDTO = new EmpresaDTO(empresa.get());
            EnderecoDTO enderecoDTO = new EnderecoDTO(enderecoRN.buscarEnderecoPorEmpresa(empresa.get().id));
            empresaDTO.setEndereco(enderecoDTO);
            return empresaDTO;
        }catch (RegraDeNegocioException ex){
            System.out.println(":buscarEmpresaPorCPNJ: RegraDeNegocioException " + ex.getMessage());
            throw new RegraDeNegocioException(ex.getMessage());
        }

    }

    public EmpresaDTO buscarEmpresaPorPrestador(Integer idPrestador){
        String hql = "FROM Empresa WHERE prestador.id = :IDPRESTADOR";

        Optional<Empresa> empresa = Empresa.find(hql, Parameters.with("IDPRESTADOR", idPrestador)).singleResultOptional();
        if (empresa.isPresent()) {
            return new EmpresaDTO(empresa.get());
        }else
            return null;
    }

    public boolean verificarEmpresaJaCadastrada(Empresa empresa) {
        String hql = "SELECT e " +
                "FROM Empresa e " +
                "WHERE e.cnpj = :CNPJ ";

        Optional<Empresa> result = Empresa.find(hql, Parameters.with("CNPJ", empresa.getCnpj())).singleResultOptional();

        return result.isPresent();
    }


}
