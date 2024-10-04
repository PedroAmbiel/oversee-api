package com.oversee.client;

public class PedidoDeValidacaoEstado extends Comunicado{

    private String estado;


    public PedidoDeValidacaoEstado(String estado) {
        this.estado = estado;
    }

    public static boolean verificarEstado(String estado){
        for(String item : Dicionarios.ESTADOS){
            if(item.equals(estado.toUpperCase())){
                return true;
            }
        }
        return false;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
