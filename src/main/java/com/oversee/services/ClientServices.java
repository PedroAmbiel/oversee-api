package com.oversee.services;


import com.oversee.client.*;
import com.oversee.model.Response;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/client")
public class ClientServices {

    Parceiro server = Cliente.iniciarConexao();

    @GET
    @Path("/server")
    public Response serverConnection() throws Exception {
        server.receba(new PedidoDeOperacao('+', 10));
        return new Response("Conta realizada com sucesso", "200");
    }

    @GET
    @Path("/receba")
    public String receberServer() throws Exception {
        double valor = 0;
        server.receba(new PedidoDeResultado());
        Comunicado comunicado = null;
        do
        {
            comunicado = server.espie ();
        }
        while (!(comunicado instanceof Resultado));
        Resultado resultado = (Resultado) server.envie();
        valor = resultado.getValorResultante();
        System.out.println ("Resultado atual: "+resultado.getValorResultante()+"\n");
        return String.valueOf(valor);
    }
}
