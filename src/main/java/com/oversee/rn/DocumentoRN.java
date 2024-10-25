package com.oversee.rn;

import com.oversee.dto.DocumentoDTO;
import com.oversee.entity.Cliente;
import com.oversee.entity.Documento;
import com.oversee.entity.Prestador;
import com.oversee.exception.RegraDeNegocioException;
import com.oversee.record.DocumentoTodos;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class DocumentoRN {

    @Transactional
    public Boolean cadastrarNovoDocumento(DocumentoDTO documentoDTO) {

        Documento documento = new Documento(
                Cliente.findById(documentoDTO.getIdCliente()),
                documentoDTO.getSuspenso(),
                Prestador.findById(documentoDTO.getIdPrestador()),
                documentoDTO.getDocumento(),
                documentoDTO.getNomeArquivo(),
                documentoDTO.getTipoDocumento(),
                documentoDTO.getExtensaoDocumento()
        );

        try{
            documento.persistAndFlush();
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Transactional
    public List<DocumentoTodos> buscarDocumentosPorPrestador(Integer idPrestador) throws RegraDeNegocioException {
        String hql = "FROM Documento WHERE prestador.id = :IDPRESTADOR AND suspenso = false";

        List<DocumentoTodos> documentos;
        try {
            documentos = Documento.find(hql, Parameters.with("IDPRESTADOR", idPrestador)).project(DocumentoTodos.class).list();
        } catch (Exception e) {
            System.out.println(e);
            throw new RegraDeNegocioException("Não foi possível consultar os documentos");
        }

        return documentos;
    }

    @Transactional
    public List<DocumentoTodos> buscarDocumentosPorCliente(Integer idCliente) throws RegraDeNegocioException {
        String hql = "FROM Documento WHERE cliente.id = :IDCLIENTE AND suspenso = false";

        List<DocumentoTodos> documentos;
        try{
            documentos = Documento.find(hql, Parameters.with("IDCLIENTE", idCliente)).project(DocumentoTodos.class).list();
        }catch (Exception e){
            System.out.println(e);
            throw new RegraDeNegocioException("Não foi possível consultar os documentos");
        }

        return documentos;
    }

    @Transactional
    public Boolean suspenderDocumento(Integer idDocumento) throws RegraDeNegocioException {

        Documento documento = Documento.findById(idDocumento);
        if (documento == null) {
            throw new RegraDeNegocioException("Documento não encontrado!");
        }
        documento.setSuspenso(true);
        documento.setDataSuspensao(LocalDateTime.now());
        documento.persistAndFlush();
        return true;

    }

}
