package com.oversee.record;

import io.quarkus.hibernate.orm.panache.common.ProjectedFieldName;

public record AgendamentoTotalPorClienteRelatorio(
        @ProjectedFieldName("nomeCliente") String nomeCliente,
        @ProjectedFieldName("qtdAgendamentos") Long qtdAgendamentos
) {
}
