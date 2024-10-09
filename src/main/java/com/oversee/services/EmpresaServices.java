package com.oversee.services;

import com.oversee.client.*;
import com.oversee.dto.EmpresaDTO;
import com.oversee.exception.RegraDeNegocioException;
import com.oversee.rn.EmpresaRN;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("/empresa")
public class EmpresaServices {

    @Inject
    EmpresaRN  empresaRN;

    @POST
    @Path("/novo")
    public Response inserirNovaEmpresa(EmpresaDTO empresaDTO) throws Exception {
        Parceiro servidor  = Cliente.iniciarConexao();

        if(servidor != null){
            Comunicado comunicado = null;
            Validado resultado = null;

            //Pedido pro servidor para validar o CNPJ
            servidor.receba(new PedidoDeValidacaoCpfCnpj(empresaDTO.getCnpj()));
            do{
                comunicado = servidor.espie();
            }while(!(comunicado instanceof Validado));
            resultado = (Validado) servidor.envie();
            if(!resultado.isValidado()){
                return Response.status(Response.Status.BAD_REQUEST).entity(resultado.getMensagem()).build();
            }
            //Pedido pro servidor para validar a sigla de Estado
            servidor.receba(new PedidoDeValidacaoEstado(empresaDTO.getEndereco().getEstado()));
            do{
                comunicado = servidor.espie();
            }while(!(comunicado instanceof Validado));
            resultado = (Validado) servidor.envie();
            if(!resultado.isValidado()){
                return Response.status(Response.Status.BAD_REQUEST).entity(resultado.getMensagem()).build();
            }

            servidor.receba(new PedidoDeValidacaoEmail(empresaDTO.getEmail()));
            do{
                comunicado = servidor.espie();
            }while(!(comunicado instanceof Validado));
            resultado = (Validado) servidor.envie();
            if(!resultado.isValidado()){
                return Response.status(Response.Status.BAD_REQUEST).entity(resultado.getMensagem()).build();
            }
        }else{
            return Response.status(Response.Status.BAD_REQUEST).entity("Servidor indisponível, tente mais tarde").build();
        }


        try {
            if (empresaRN.inserirNovaEmpresa(empresaDTO))

                return Response.ok("Empresa cadastrada com sucesso").build();
            else
                return Response.status(Response.Status.BAD_REQUEST).entity("Empresa já cadastrada").build();
        }catch (RegraDeNegocioException e){
            return Response.status(Response.Status.BAD_REQUEST).entity("Prestador não encontrado").build();
        }
    }

    @GET
    @Path("/buscar")
    public Response buscarEmpresa(@QueryParam("cnpj") String cnpj) {
        try{
            return Response.ok(empresaRN.buscarEmpresaPorCPNJ(cnpj)).build();
        }catch(RegraDeNegocioException e){
            return Response.status(Response.Status.BAD_REQUEST).entity("Não foi possivel encontrar a empresa").build();
        }

    }

}
