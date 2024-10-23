package com.oversee.services;

import com.oversee.dto.DocumentoDTO;
import com.oversee.exception.RegraDeNegocioException;
import com.oversee.record.DocumentoTodos;
import com.oversee.rn.DocumentoRN;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/documentos")
public class DocumentoServices {

    @Inject
    DocumentoRN documentoRN;

    @POST
    @Path("/novo")
    public Response cadastrarDocumento(DocumentoDTO documentoDTO) {
        if (documentoRN.cadastrarNovoDocumento(documentoDTO)) {
            return Response.ok("Documento cadastrado com sucesso!").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Não foi possível cadastrar o documento").build();
        }
    }

    @GET
    @Path("/buscarporprestador")
    public Response buscarDocumentoPorPrestador(@QueryParam("idPrestador") Integer idPrestador) {
        try {
            List<DocumentoTodos> documentos = documentoRN.buscarDocumentosPorPrestador(idPrestador);
            return Response.ok(documentos).build();
        } catch (RegraDeNegocioException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/buscarporcliente")
    public Response buscarDocumentoPorCliente(@QueryParam("idCliente") Integer idCliente) {
        try {
            List<DocumentoTodos> documentos = documentoRN.buscarDocumentosPorPrestador(idCliente);
            return Response.ok(documentos).build();
        } catch (RegraDeNegocioException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/suspender")
    public Response suspender(@QueryParam("idDocumento") Integer idDocumento) {
        try {
            if (documentoRN.suspenderDocumento(idDocumento)) {
                return Response.ok("Documento suspenso com sucesso!").build();
            }else{
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }catch (RegraDeNegocioException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
