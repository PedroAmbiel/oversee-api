package com.oversee.rn;

import com.oversee.entity.Agendamento;
import com.oversee.record.AgendamentoPorMesRelatorio;
import com.oversee.record.AgendamentoTotalPorAnoRelatorio;
import com.oversee.record.AgendamentoTotalPorClienteRelatorio;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class RelatorioRN {

    public List<AgendamentoPorMesRelatorio> buscarAgendamentosPorMes(Integer idPrestador, Integer ano){
        String hql = "SELECT DISTINCT MONTH(a.dataInicio) AS mes, " +
                "COUNT(a) AS qtdAgendamentos " +
                "FROM Agendamento a " +
                "WHERE YEAR(a.dataInicio) = :ANO " +
                "AND a.prestador.id = :PRESTADOR " +
                "GROUP BY MONTH(a.dataInicio), YEAR(a.dataInicio) ";

        return Agendamento.find(hql, Parameters.with("ANO", ano).and("PRESTADOR", idPrestador)).project(AgendamentoPorMesRelatorio.class).list();
    }

    public List<AgendamentoTotalPorAnoRelatorio> buscarAgendamentosPorAno(Integer idPrestador){
        String hql = "SELECT DISTINCT YEAR(a.dataInicio) AS ano, " +
                "COUNT(a) AS qtdAgendamentos " +
                "FROM Agendamento a " +
                "WHERE a.prestador.id = :PRESTADOR " +
                "GROUP BY YEAR(a.dataInicio) ";

        return Agendamento.find(hql, Parameters.with("PRESTADOR", idPrestador)).project(AgendamentoTotalPorAnoRelatorio.class).list();
    }

    public List<AgendamentoTotalPorClienteRelatorio> buscarAgendamentosPorCliente(Integer idPrestador, Integer ano){
        String hql = "SELECT DISTINCT c.nome as nomeCliente, " +
                "COUNT(a) AS qtdAgendamentos " +
                "FROM Agendamento a " +
                "JOIN a.cliente c " +
                "WHERE YEAR(a.dataInicio) = :ANO " +
                "AND a.prestador.id = :PRESTADOR " +
                "GROUP BY YEAR(a.dataInicio), c.nome ";

        return Agendamento.find(hql, Parameters.with("ANO", ano).and("PRESTADOR", idPrestador)).project(AgendamentoTotalPorClienteRelatorio.class).list();
    }

}
