package com.oversee.services;


import com.oversee.client.*;
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

    Parceiro server = Cliente.iniciarConexao();
    
    @Inject
    ClienteRN clienteRN;

    @GET
    @Path("/server")
    public Response serverConnection() throws Exception {
        server.receba(new PedidoDeOperacao('+', 10));
        return Response.ok("Conta realizada com sucesso").build();
    }

    @GET
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
    }


    @POST
    @Path("/novo")
    @Consumes
    public Response criarNovoCliente(ClienteDTO cliente) {
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
    @Produces
    public Response buscarTodosClientesPrestador(@QueryParam("idPrestador") Integer idPrestador){
        List<ClienteTodos> clientes = clienteRN.buscarTodosClientes(idPrestador);
        return Response.ok(clientes).build();
    }
}
