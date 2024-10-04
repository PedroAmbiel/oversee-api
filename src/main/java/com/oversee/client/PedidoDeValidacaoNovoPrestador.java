package com.oversee.client;

public class PedidoDeValidacaoNovoPrestador extends Comunicado {

    private String email;
    private String cpf;
    private String senha;

    public PedidoDeValidacaoNovoPrestador(String email, String cpf, String senha) {
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
    }

    public static boolean validarNovoPrestador(PedidoDeValidacaoNovoPrestador novoPrestador) {
        if(!PedidoDeValidacaoLogin.validarLogin(new PedidoDeValidacaoLogin(novoPrestador.getCpf(), novoPrestador.getSenha()))) {//Valida CPF e Senha
            return false;
        }else if(!novoPrestador.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {//Email inválido sem @ ou .com ou outro .algo
            return false;
        }
        return true;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
