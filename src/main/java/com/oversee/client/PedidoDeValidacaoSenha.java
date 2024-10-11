package com.oversee.client;

public class PedidoDeValidacaoSenha extends Comunicado{

    private String senha;

    public static Validado validarSenha(PedidoDeValidacaoSenha pedidoDeValidacaoSenha) {
        if (!pedidoDeValidacaoSenha.getSenha().matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[\\W_]).+$")) {
            return new Validado(false, "Senha não atende aos requisitos mínimos");
        }
        return new Validado(true, "Senha válida");
    }

    public PedidoDeValidacaoSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
