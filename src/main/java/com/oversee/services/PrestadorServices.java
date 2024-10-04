package com.oversee.services;

import com.oversee.client.*;
import com.oversee.dto.EmpresaDTO;
import com.oversee.dto.LoginDTO;
import com.oversee.dto.PrestadorDTO;
import com.oversee.exception.RegraDeNegocioException;
import com.oversee.rn.EmpresaRN;
import com.oversee.rn.PrestadorRN;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/prestador")
public class PrestadorServices {

    @Inject
    PrestadorRN prestadorRN;

    @Inject
    EmpresaRN empresaRN;

    @POST
    @Path("/criar")
    @Consumes
    public Response inserirNovoPrestador(PrestadorDTO dto) throws Exception {
        Parceiro servidor = Cliente.iniciarConexao();
        Response response = null;

        if(servidor != null){//Validar os dados do Prestador
            //servidor.receba(new PedidoDeValidacaoCpfCnpj(dto.getCpf()));
            Comunicado comunicado = null;

            servidor.receba(new PedidoDeValidacaoNovoPrestador(dto.getEmail(), dto.getCpf(), dto.getSenha()));
            do
            {
                comunicado = (Comunicado)servidor.espie();
            }
            while (!(comunicado instanceof Validado));
            Validado resultadoLogin = (Validado)servidor.envie();
            if(!resultadoLogin.isValidado()) return Response.status(Response.Status.BAD_REQUEST).entity("CPF ou Senha inválida").build();
            servidor.adeus();//Finaliza conexão com o servidor
        }else{
            return Response.status(Response.Status.BAD_REQUEST).entity("Servidor indisponível, tente mais tarde").build();
        }

        if (prestadorRN.criarNovoPrestador(dto))
            response = Response.ok("Criado com sucesso").build();
        else
            response = Response.status(Response.Status.BAD_REQUEST).entity("Prestador já cadastrado").build();

        return response;
    }

    @GET
    @Path("/buscar")
    @Produces
    public Response buscarPrestadorPorId(@QueryParam("id") Long id) {
        PrestadorDTO prestador = prestadorRN.buscarPrestadorDTO(id);

        if(prestador != null) {
            return Response.ok(prestador).build();
        }
        else
            return Response.status(Response.Status.NO_CONTENT).build();
    }

    @POST
    @Path("/login")
    @Produces
    public Response login(LoginDTO login) {
        //Parceiro servidor = Cliente.iniciarConexao();

        //Retirado verificação se cpf foi preenchido corretamente
        /*if(servidor != null) {
            servidor.receba(new PedidoDeValidacaoLogin(login.getCpf(), login.getSenha()));
            Comunicado comunicado = null;
            do
            {
                comunicado = (Comunicado)servidor.espie();
            }
            while (!(comunicado instanceof Validado));
            Validado resultado = (Validado)servidor.envie ();
            if(!resultado.isValidado()){
                return Response.status(Response.Status.BAD_REQUEST).entity("Usuário ou senha incorreto").build();
            }
        }else{
            return Response.status(Response.Status.BAD_REQUEST).entity("Servidor indisponível, tente mais tarde").build();
        }*/

        PrestadorDTO prestadorAutenticado = prestadorRN.validarLogin(login);
        if(prestadorAutenticado != null){
            return Response.ok(prestadorAutenticado).build();
        }else{
            return Response.status(Response.Status.UNAUTHORIZED).entity("Usuário ou senha incorretos").build();
        }
    }
    @GET
    @Path("/buscarempresa")
    public Response verificarEmpresaPrestador(@QueryParam("idPrestador") Integer idPrestador){
        EmpresaDTO empresa = empresaRN.buscarEmpresaPorPrestador(idPrestador);
        if (empresa != null)
            return Response.ok(empresa).build();
        else
            return Response.status(Response.Status.NOT_FOUND).entity("Prestador não possui empresa cadastrada").build();
    }
}
