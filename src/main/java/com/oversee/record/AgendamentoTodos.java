package com.oversee.record;

import com.oversee.model.TipoAgendamento;
import io.quarkus.hibernate.orm.panache.common.ProjectedFieldName;

import java.time.LocalDateTime;

public record AgendamentoTodos(@ProjectedFieldName("a.id") Long id, String descricao, @ProjectedFieldName("p.id") Long fkPrestador,@ProjectedFieldName("c.id") Long fkCliente, LocalDateTime dataInicio, LocalDateTime dataFim, TipoAgendamento tipoAgendamento) {
}
