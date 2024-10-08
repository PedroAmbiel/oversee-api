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

    public static Validado validarNovoPrestador(PedidoDeValidacaoNovoPrestador novoPrestador) {
        Validado pedidoLogin = PedidoDeValidacaoLogin.validarLogin(new PedidoDeValidacaoLogin(novoPrestador.getCpf(), novoPrestador.getSenha()));
        Validado pedidoEmail = PedidoDeValidacaoEmail.validarEmail(new PedidoDeValidacaoEmail(novoPrestador.getEmail()));


        if(!pedidoLogin.isValidado()) {//Valida CPF e Senha
            return pedidoLogin;
        }else if(!pedidoEmail.isValidado()) {//Email inválido sem @ ou .com ou outro .algo
            return pedidoEmail;
        }
        return new Validado(true, "Prestador válido");
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
