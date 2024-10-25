package com.oversee.rn;

import com.oversee.dto.AtualizarClienteDTO;
import com.oversee.dto.ClienteDTO;
import com.oversee.exception.RegraDeNegocioException;
import com.oversee.record.ClienteTodos;
import com.oversee.entity.Cliente;
import com.oversee.entity.Prestador;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.hibernate.PropertyValueException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ClienteRN {

    @Transactional
    public Boolean novoCliente(ClienteDTO cliente) throws RegraDeNegocioException {
        Cliente novoCliente = new Cliente(
                cliente.getNome(),
                cliente.getDataNascimento(),
                cliente.getCpf(),
                cliente.getRg(),
                Prestador.findById(cliente.getFkPrestador()),
                cliente.getDataCadastro(),
                cliente.getCancelado(),
                cliente.getDataCancelado());

        //Caso o cliente ja esteja cadastrado para o prestador solicitante
        if(verificarClientePrestadorJaCadastrado(novoCliente)){
            //Não cadastra novo cliente
            return false;
        }else{
            //Cadastra novo cliente
            try {
                novoCliente.persist();
            }catch (PropertyValueException e){
                System.out.println("Erro ao persistir novo cliente: PRESTADOR INCORRETO");
                throw new RegraDeNegocioException("O prestador foi informado de forma incorreta");
            }catch (Exception e){
                System.out.println("Erro ao persistir novo cliente: " + e.getMessage());
                throw new RegraDeNegocioException("Erro ao salvar novo cliente");
            }

            return true;
        }
    }

    @Transactional
    public ClienteDTO buscarClientePorId(Integer idCliente, Integer idPrestador){
        String hql = "SELECT c " +
                "FROM Cliente c " +
                "WHERE c.id = :IDCLIENTE " +
                "AND c.prestador.id = :IDPRESTADOR";

        Optional<Cliente> cliente = Cliente.find(hql,
                Parameters.with("IDCLIENTE", idCliente)
                        .and("IDPRESTADOR", idPrestador)).singleResultOptional();

        return cliente.map(ClienteDTO::new).orElse(null);

    }

    @Transactional
    public List<ClienteTodos> buscarTodosClientes(Integer idPrestador){
        String hql = "FROM Cliente c " +
                "JOIN c.prestador p " +
                "WHERE p.id = :IDPRESTADOR " +
                "AND c.cancelado = false";

        return Cliente.find(hql, Parameters.with("IDPRESTADOR", idPrestador)).project(ClienteTodos.class).list();
    }

    @Transactional
    public Boolean verificarClientePrestadorJaCadastrado(Cliente cliente){
        String hql = "SELECT c " +
                "FROM Cliente c " +
                "WHERE c.cpf = :CPF " +
                "AND c.prestador = :PRESTADOR";

        Optional<Cliente> result = Cliente.find(hql, Parameters.with("CPF", cliente.getCpf())
                                    .and("PRESTADOR", cliente.getPrestador())).singleResultOptional();


        return result.isPresent();
    }

    @Transactional
    public Boolean cancelarCliente(Integer idCliente){
        Cliente cliente = Cliente.findById(idCliente.longValue());

        if(cliente != null){
            cliente.setDataCancelado(LocalDateTime.now());
            cliente.setCancelado(true);

            return true;
        }

        return false;
    }

    @Transactional
    public Boolean atualizarCliente(AtualizarClienteDTO clienteDTO) throws RegraDeNegocioException{
        Cliente cliente = Cliente.findById(clienteDTO.getId().longValue());
        if(cliente != null) {
            cliente.setNome(clienteDTO.getNome());
            cliente.setDataNascimento(clienteDTO.getDataNascimento());
            cliente.persistAndFlush();
            return true;
        }else{
            throw new RegraDeNegocioException("Cliente não encontrado");
        }

    }
}
