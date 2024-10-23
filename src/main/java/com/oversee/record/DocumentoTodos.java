package com.oversee.record;

import com.oversee.model.TipoDocumento;
import io.quarkus.hibernate.orm.panache.common.ProjectedFieldName;

import java.time.LocalDateTime;

public record DocumentoTodos(@ProjectedFieldName("id") Long id,
                             @ProjectedFieldName("cliente.id") Long idCliente,
                             @ProjectedFieldName("cliente.nome") String nomeCliente,
                             @ProjectedFieldName("prestador.id")Long idPrestador,
                             @ProjectedFieldName("dataCadastro") LocalDateTime dataCadastro,
                             @ProjectedFieldName("dataSuspensao") LocalDateTime dataSuspensao,
                             @ProjectedFieldName("documento") Byte[] documento,
                             @ProjectedFieldName("tipoDocumento") TipoDocumento tipoDocumento,
                             @ProjectedFieldName("nomeArquivo") String nomeArquivo,
                             @ProjectedFieldName("extensaoDocumento")String extensaoDocumento) {



}
