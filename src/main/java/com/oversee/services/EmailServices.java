package com.oversee.services;

import com.oversee.client.*;
import com.oversee.dto.AlterarSenhaDTO;
import com.oversee.dto.PrestadorDTO;
import com.oversee.entity.Prestador;
import com.oversee.entity.RedefinicaoDeSenha;
import com.oversee.exception.RegraDeNegocioException;
import com.oversee.rn.EmailRN;
import com.oversee.rn.PrestadorRN;
import io.vertx.ext.web.RoutingContext;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/email")
public class EmailServices {

    @Inject
    RoutingContext context;
    @Inject
    EmailRN emailRN;
    @Inject
    PrestadorRN prestadorRN;


    @GET
    @Path("/redefinirsenha")
    public Response redefinirsenha(@QueryParam("cpf") String cpf) {
        try{
            Parceiro servidor = Cliente.iniciarConexao();

            if(servidor != null){//Validar os dados do Prestador
                //servidor.receba(new PedidoDeValidacaoCpfCnpj(dto.getCpf()));
                Comunicado comunicado = null;

                servidor.receba(new PedidoDeValidacaoCpfCnpj(cpf));
                do
                {
                    comunicado = (Comunicado)servidor.espie();
                }
                while (!(comunicado instanceof Validado));
                Validado resultadoLogin = (Validado)servidor.envie();
                if(!resultadoLogin.isValidado()) return Response.status(Response.Status.BAD_REQUEST).entity(resultadoLogin.getMensagem()).build();
                servidor.adeus();//Finaliza conexão com o servidor
            }else{
                return Response.status(Response.Status.BAD_REQUEST).entity("Servidor indisponível, tente mais tarde").build();
            }

            RedefinicaoDeSenha pedido = emailRN.criarPedidoRedefinicaoSenha(cpf, context.request().remoteAddress().hostAddress());

            if (pedido == null) return Response.status(Response.Status.NO_CONTENT).entity("Prestador não encontrado").build();

            emailRN.enviarEmailRedefinicaoSenha(pedido);
            return Response.ok("O link para redefinição será enviado no email de cadastro do prestador").build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/validar/redefinicaosenha")
    public Response validarRedefinicaoSenha(@QueryParam("identificador") String identificador) {
        Prestador prestador = emailRN.buscarIdentificadorSenha(identificador);
        if (prestador != null) {
            PrestadorDTO dto = new PrestadorDTO(prestador);
            return Response.ok(dto).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/alterarsenha")
    public Response alterarsenha(AlterarSenhaDTO alterarSenhaDTO) throws Exception {
        Parceiro servidor = Cliente.iniciarConexao();

        if(servidor != null){
            Comunicado comunicado = null;

            servidor.receba(new PedidoDeValidacaoSenha(alterarSenhaDTO.getNovaSenha()));
            do
            {
                comunicado = (Comunicado)servidor.espie();
            }
            while (!(comunicado instanceof Validado));
            Validado resultadoLogin = (Validado)servidor.envie();
            if(!resultadoLogin.isValidado()) return Response.status(Response.Status.BAD_REQUEST).entity(resultadoLogin.getMensagem()).build();
            servidor.adeus();//Finaliza conexão com o servidor
        }else{
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Servidor indisponível, tente mais tarde").build();
        }

        try{
            prestadorRN.alterarSenhaPrestador(alterarSenhaDTO);
            emailRN.finalizarPedidoRedefinicaoSenha(alterarSenhaDTO.getIdentificador());
            return Response.ok("Senha alterada com sucesso!").build();
        }catch (RegraDeNegocioException ex){
            return Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build();
        }


    }

}
