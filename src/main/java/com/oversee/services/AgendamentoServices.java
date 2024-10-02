package com.oversee.services;

import com.oversee.dto.AgendamentoDTO;
import com.oversee.exception.RegraDeNegocioException;
import com.oversee.rn.AgendamentoRN;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/agendamento")
public class AgendamentoServices {

    @Inject
    AgendamentoRN agendamentoRN;

    @POST
    @Consumes
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
    @Produces
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

    @GET
    @Produces
    @Path("/buscarcliente")
    public Response buscarAgendamentosPorCliente(@QueryParam("idCliente") Integer idCliente, @QueryParam("idPrestador") Integer idPrestador) {
        return Response.ok(agendamentoRN.buscarAgendamentosCliente(idCliente, idPrestador)).build();
    }

    @GET
    @Produces
    @Path("/buscarprestador")
    public Response buscarAgendamentosPorPrestador(@QueryParam("idPrestador") Integer idPrestador) {
        return Response.ok(agendamentoRN.buscarAgendamentosPrestador(idPrestador)).build();
    }

}
