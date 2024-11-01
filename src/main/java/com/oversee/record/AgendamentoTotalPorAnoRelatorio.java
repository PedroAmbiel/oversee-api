package com.oversee.record;

import io.quarkus.hibernate.orm.panache.common.ProjectedFieldName;

public record AgendamentoTotalPorAnoRelatorio(
        @ProjectedFieldName("ano") Integer ano,
        @ProjectedFieldName("qtdAgendamentos") Long qtdAgendamentos

) {
}
