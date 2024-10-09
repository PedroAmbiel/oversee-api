package com.oversee.services;

import com.oversee.entity.RedefinicaoDeSenha;
import com.oversee.rn.EmailRN;
import io.vertx.ext.web.RoutingContext;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("/email")
public class EmailServices {

    @Inject
    RoutingContext context;
    @Inject
    EmailRN emailRN;


    @GET
    @Path("/redefinirsenha")
    public Response redefinirsenha(@QueryParam("cpf") String cpf) {
        try{
            RedefinicaoDeSenha pedido = emailRN.criarPedidoRedefinicaoSenha(cpf, context.request().remoteAddress().hostAddress());

            if (pedido == null) return Response.status(Response.Status.BAD_REQUEST).entity("Prestador não encontrado").build();

            emailRN.enviarEmailRedefinicaoSenha(pedido);
            return Response.ok("O link para redefinição será enviado no email de cadastro do prestador").build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}
