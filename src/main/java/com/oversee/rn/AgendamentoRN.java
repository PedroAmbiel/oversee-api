package com.oversee.rn;

import com.oversee.dto.AgendamentoDTO;
import com.oversee.entity.Agendamento;
import com.oversee.entity.Cliente;
import com.oversee.entity.Prestador;
import com.oversee.exception.RegraDeNegocioException;
import com.oversee.record.AgendamentoTodos;
import com.oversee.record.ClienteTodos;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.hibernate.NonUniqueResultException;
import org.hibernate.PropertyValueException;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AgendamentoRN {

    @Transactional
    public Boolean criarNovoAgendamento(AgendamentoDTO agendamento) throws RegraDeNegocioException {
        Agendamento novoAgendamento = new Agendamento(
                agendamento.getDataInicio(),
                agendamento.getDataFim(),
                Prestador.findById(agendamento.getFkPrestador()),
                Cliente.findById(agendamento.getFkCliente()),
                agendamento.getDescricao(),
                agendamento.getTipoAgendamento());

        try {
            if(!verificarHorarioExisteAgendamento(novoAgendamento)) {
                novoAgendamento.persist();
                return true;
            }else
                return false;

        }catch(NonUniqueResultException e) {
            System.out.println("Erro ao criar novo agendamento: " + e.getMessage());
            return false;
        }catch (PropertyValueException e){
            System.out.println("Erro ao criar novo agendamento: " + e.getMessage());
            throw new RegraDeNegocioException("Body malformado, algum campo veio nulo");
        }
    }

    public AgendamentoDTO buscarAgendamentoPorId(Integer idAgendamento) throws RegraDeNegocioException {
        String hql = "SELECT a " +
                "FROM Agendamento a " +
                "WHERE a.id = :IDAGENDAMENTO ";


        Optional<Agendamento> agendamento = Agendamento.find(hql,
                Parameters.with("IDAGENDAMENTO", idAgendamento)).singleResultOptional();

        return agendamento.map(AgendamentoDTO::new).orElse(null);
    }

    public List<AgendamentoTodos> buscarAgendamentosCliente(Integer idCliente, Integer idPrestador){
        String hql = "FROM Agendamento a " +
                "JOIN a.prestador p " +
                "JOIN a.cliente c " +
                "WHERE c.id = :IDCLIENTE " +
                "AND p.id = :IDPRESTADOR ";

        return Agendamento.find(hql, Parameters.with("IDCLIENTE", idCliente).and("IDPRESTADOR", idPrestador)).project(AgendamentoTodos.class).list();
    }

    public List<AgendamentoTodos> buscarAgendamentosPrestador(Integer idPrestador){
        String hql = "FROM Agendamento a " +
                "JOIN a.prestador p " +
                "JOIN a.cliente c " +
                "WHERE p.id = :IDPRESTADOR ";

        return Agendamento.find(hql, Parameters.with("IDPRESTADOR", idPrestador)).project(AgendamentoTodos.class).list();
    }


    public Boolean verificarHorarioExisteAgendamento(Agendamento agendamento){
        String hql = "SELECT a " +
                "FROM Agendamento a " +
                "WHERE a.prestador = :PRESTADOR " +
                "AND (a.dataInicio BETWEEN :HORARIO1 AND :HORARIO2 " +
                "OR a.dataFim BETWEEN :HORARIO1 AND :HORARIO2) " +
                "AND a.tipoAgendamento = :TIPOAGENDAMENTO";


        Optional<Agendamento> agenda = Agendamento.find(hql,
                Parameters.with("PRESTADOR", agendamento.getPrestador())
                            .and("HORARIO1", agendamento.getDataInicio())
                            .and("HORARIO2", agendamento.getDataFim())
                            .and("TIPOAGENDAMENTO", agendamento.getTipoAgendamento())
                            ).singleResultOptional();

        return agenda.isPresent();
    }
}
