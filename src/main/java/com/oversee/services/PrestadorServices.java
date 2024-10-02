package com.oversee.services;

import com.oversee.dto.PrestadorDTO;
import com.oversee.rn.PrestadorRN;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("/prestador")
public class PrestadorServices {

    @Inject
    PrestadorRN prestadorRN;

    @POST
    @Path("/criar")
    public Response inserirNovoPrestador(PrestadorDTO dto) {
        Response response = null;

        if (prestadorRN.criarNovoPrestador(dto))
            response = Response.ok("Criado com sucesso").build();
        else
            response = Response.status(Response.Status.BAD_REQUEST).entity("Prestador já cadastrado").build();

        return response;
    }

    @GET
    @Path("/buscar")
    public Response buscarPrestadorPorId(@QueryParam("id") Long id) {
        return Response.ok(prestadorRN.buscarPrestador(id)).build();
    }
}
