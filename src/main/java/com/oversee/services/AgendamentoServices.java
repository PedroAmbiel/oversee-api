package com.oversee.services;

import com.oversee.dto.AgendamentoDTO;
import com.oversee.dto.AgendamentoLimiteConsumesDTO;
import com.oversee.exception.RegraDeNegocioException;
import com.oversee.rn.AgendamentoRN;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;

@Path("/agendamento")
public class AgendamentoServices {

    @Inject
    AgendamentoRN agendamentoRN;

    @POST
    @Path("/novo")
    public Response cadastrarNovoAgendamento(AgendamentoDTO agendamento) {
        try {
            if (agendamentoRN.criarNovoAgendamento(agendamento)) {
                return Response.ok("Agendamento criado com sucesso!").build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Já existe agendamento no período indicado").build();
            }
        }catch (RegraDeNegocioException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/buscar")
    public Response buscarAgendamentos(@QueryParam("idAgendamento") Integer idAgendamento) {
        try{
            AgendamentoDTO agendamento = agendamentoRN.buscarAgendamentoPorId(idAgendamento);
            if (agendamento != null) {
                return Response.ok(agendamentoRN.buscarAgendamentoPorId(idAgendamento)).build();
            }else{
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (RegraDeNegocioException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    //Busca TODOS os agendamentos por cliente
    @GET
    @Path("/buscarporcliente")
    public Response buscarAgendamentosPorCliente(@QueryParam("idCliente") Integer idCliente, @QueryParam("idPrestador") Integer idPrestador) {
        return Response.ok(agendamentoRN.buscarAgendamentosCliente(idCliente, idPrestador)).build();
    }

    //Busca TODOS os agendamentos por prestador sem limite
    @GET
    @Path("/buscarprestador")
    public Response buscarAgendamentosPorPrestador(@QueryParam("idPrestador") Integer idPrestador) {
        return Response.ok(agendamentoRN.buscarAgendamentosPrestador(idPrestador)).build();
    }

    //Busca agendamentos do prestador com limite de data inicio e data fim
    @POST
    @Path("/buscarprestadorlimite")
    public Response buscarAgendamentosPorPrestadorLimite(@QueryParam("idPrestador") Integer idPrestador, AgendamentoLimiteConsumesDTO agendamento) {
        return Response.ok(agendamentoRN.buscarAgendamentosPrestadorLimite(idPrestador, agendamento.getDataInicio(), agendamento.getDataFim())).build();
    }

    @PUT
    @Path("/cancelar")
    public Response cancelarAgendamento(@QueryParam("idAgendamento") Integer idAgendamento) {
        try{
            return Response.ok(agendamentoRN.cancelarAgendamento(idAgendamento)).build();
        }catch (RegraDeNegocioException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
