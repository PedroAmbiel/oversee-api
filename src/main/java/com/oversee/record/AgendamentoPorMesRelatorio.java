package com.oversee.record;

import io.quarkus.hibernate.orm.panache.common.ProjectedFieldName;

public record AgendamentoPorMesRelatorio(
        @ProjectedFieldName("mes") Integer mes,
        @ProjectedFieldName("qtdAgendamentos")Long qtdAgendamentos) {
}
//O ano do relatório é regrado pelo endpoint, ele recebe o ano que será exibido aqui