package com.oversee.services;

import com.oversee.record.AgendamentoPorMesRelatorio;
import com.oversee.record.AgendamentoTotalPorAnoRelatorio;
import com.oversee.record.AgendamentoTotalPorClienteRelatorio;
import com.oversee.rn.RelatorioRN;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/relatorio")
public class RelatorioServices {

    @Inject
    RelatorioRN relatorioRN;

    @GET
    @Path("/agendamentospormes")
    public Response quantidadeAgendamentosPorMes(@QueryParam("idPrestador") Integer idPrestador, @QueryParam("ano")Integer ano) {
        List<AgendamentoPorMesRelatorio> agendamentos = relatorioRN.buscarAgendamentosPorMes(idPrestador, ano);

        if(agendamentos.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("Nenhum agendamento encontrado").build();
        }

        return Response.ok(agendamentos).build();
    }

    @GET
    @Path("/agendamentosporano")
    public Response quantidadeAgendamentosTotalPorAno(@QueryParam("idPrestador")Integer idPrestador, @QueryParam("ano")Integer ano) {
        List<AgendamentoTotalPorAnoRelatorio> agendamentos = relatorioRN.buscarAgendamentosPorAno(idPrestador);

        if(agendamentos.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("Nenhum agendamento encontrado").build();
        }

        return Response.ok(agendamentos).build();
    }

    @GET
    @Path("/agendamentosporcliente")
    public Response quantidadeAgendamentosTotalPorCliente(@QueryParam("idPrestador")Integer idPrestador, @QueryParam("ano")Integer ano) {
        List<AgendamentoTotalPorClienteRelatorio> agendamentos = relatorioRN.buscarAgendamentosPorCliente(idPrestador, ano);

        if(agendamentos.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("Nenhum agendamento encontrado").build();
        }

        return Response.ok(agendamentos).build();
    }
}
