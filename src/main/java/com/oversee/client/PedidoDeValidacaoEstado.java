package com.oversee.client;

public class PedidoDeValidacaoEstado extends Comunicado{

    private String estado;


    public PedidoDeValidacaoEstado(String estado) {
        this.estado = estado;
    }

    public static Validado verificarEstado(String estado){
        if(estado.length() == 2){
            for(String item : Dicionarios.ESTADOS){
                if(item.equals(estado.toUpperCase())){
                    return new Validado(true, "Estado válido");
                }
            }
        }
        return new Validado(false, "Informe a sigla de um estado válido");
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
