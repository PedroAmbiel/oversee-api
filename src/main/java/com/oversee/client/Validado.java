package com.oversee.client;

public class Validado extends Comunicado{

    private boolean validado;

    public Validado(boolean validado) {
        this.validado = validado;
    }

    public boolean isValidado() {
        return validado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }
}
