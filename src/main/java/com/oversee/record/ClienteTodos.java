package com.oversee.record;

import io.quarkus.hibernate.orm.panache.common.ProjectedFieldName;

import java.time.LocalDate;

public record ClienteTodos(@ProjectedFieldName("c.id") Long id,
                           @ProjectedFieldName("c.dataNascimento") LocalDate dataNascimento,
                           @ProjectedFieldName("c.nome") String nome,
                           String rg,
                           @ProjectedFieldName("c.cpf") String cpf,
                           @ProjectedFieldName("p.id") Long fkPrestador,
                           @ProjectedFieldName("c.dataCadastro") LocalDate dataCadastro,
                           @ProjectedFieldName("c.cancelado")Boolean cancelado){

}
