package com.oversee.services;


import com.oversee.client.*;
import com.oversee.dto.AtualizarClienteDTO;
import com.oversee.dto.ClienteDTO;
import com.oversee.exception.RegraDeNegocioException;
import com.oversee.record.ClienteTodos;
import com.oversee.rn.ClienteRN;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/cliente")
public class ClientServices {
    
    @Inject
    ClienteRN clienteRN;

    /*@GET
    @Path("/server")
    public Response serverConnection() throws Exception {
        server.receba(new PedidoDeOperacao('+', 10));
        return Response.ok("Conta realizada com sucesso").build();
    }*/

    /*@GET
    @Path("/receba")
    public String receberServer() throws Exception {
        double valor = 0;
        server.receba(new PedidoDeResultado());
        Comunicado comunicado = null;
        do
        {
            comunicado = server.espie ();
        }
        while (!(comunicado instanceof Resultado));
        Resultado resultado = (Resultado) server.envie();
        valor = resultado.getValorResultante();
        System.out.println ("Resultado atual: "+resultado.getValorResultante()+"\n");
        return String.valueOf(valor);
    }*/


    @POST
    @Path("/novo")
    public Response criarNovoCliente(ClienteDTO cliente) throws Exception {
        Parceiro servidor = Cliente.iniciarConexao();

        if(servidor != null){//Validar o CPF informado
            servidor.receba(new PedidoDeValidacaoCpfCnpj(cliente.getCpf()));
            Comunicado comunicado = null;
            do
            {
                comunicado = (Comunicado)servidor.espie();
            }
            while (!(comunicado instanceof Validado));
            Validado resultadoLogin = (Validado)servidor.envie();
            if(!resultadoLogin.isValidado()) return Response.status(Response.Status.BAD_REQUEST).entity("CPF inválido").build();
            servidor.adeus();//Finaliza conexão com o servidor
        }else{
            return Response.status(Response.Status.BAD_REQUEST).entity("Servidor indisponível, tente mais tarde").build();
        }

        try{
            if(clienteRN.novoCliente(cliente)){
                return Response.ok("Cliente Cadastrado").build();
            }
            else{
                return Response.status(Response.Status.BAD_REQUEST).entity("Cliente já cadastrado").build();
            }
        }catch(RegraDeNegocioException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    @GET
    @Path("/buscar")
    public Response buscarClientePorId(@QueryParam("idCliente") Integer idCliente, @QueryParam("idPrestador") Integer idPrestador) {
        ClienteDTO cliente = clienteRN.buscarClientePorId(idCliente, idPrestador);
        return cliente != null ? Response.ok(cliente).build() : Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/buscartodos")
    public Response buscarTodosClientesPrestador(@QueryParam("idPrestador") Integer idPrestador){
        List<ClienteTodos> clientes = clienteRN.buscarTodosClientes(idPrestador);
        return Response.ok(clientes).build();
    }

    @PUT
    @Path("/cancelar")
    public Response cancelarCliente(@QueryParam("idCliente") Integer idCliente){
        if(clienteRN.cancelarCliente(idCliente)){
            return Response.ok("Cliente cancelado com sucesso!").build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).entity("Não foi possivel cancelar cliente").build();
        }
    }

    @PUT
    @Path("/atualizar")
    public Response atualizarCliente(AtualizarClienteDTO atualizarClienteDTO){
        try{
            if(clienteRN.atualizarCliente(atualizarClienteDTO)){
                return Response.ok("Cliente atualizado com sucesso!").build();
            }else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }catch(RegraDeNegocioException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

}
