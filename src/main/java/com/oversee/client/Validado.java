package com.oversee.client;

public class Validado extends Comunicado{

    private boolean validado;
    private String mensagem;

    public Validado(boolean validado, String mensagem) {
        this.validado = validado;
        this.mensagem = mensagem;
    }

    public boolean isValidado() {
        return validado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
