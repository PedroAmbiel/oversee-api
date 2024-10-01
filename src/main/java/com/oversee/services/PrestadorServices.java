package com.oversee.services;

import com.oversee.dto.PrestadorDTO;
import com.oversee.model.Response;
import com.oversee.rn.PrestadorRN;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@Path("/prestador")
public class PrestadorServices {

    @Inject
    PrestadorRN prestadorRN;

    @POST
    @Path("/criar")
    public Response inserirNovoPrestador(PrestadorDTO dto) {
        Response response = null;

        if (prestadorRN.criarNovoPrestador(dto))
            response = new Response("Criado com sucesso", "200");
        else
            response = new Response("Prestador já cadastrado ", "200");

        return response;
    }

    @GET
    @Path("/buscar")
    public PrestadorDTO buscarPrestadorPorId(@QueryParam("id") Long id) {
        return prestadorRN.buscarPrestador(id);
    }
}
