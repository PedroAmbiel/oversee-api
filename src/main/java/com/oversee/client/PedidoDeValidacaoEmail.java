package com.oversee.client;

public class PedidoDeValidacaoEmail extends Comunicado {

    private String email;

    public PedidoDeValidacaoEmail(String email) {
        this.email = email;
    }

    public static Validado validarEmail(PedidoDeValidacaoEmail email) {
        if(!email.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {//Email inválido sem @ ou .com ou outro .algo
            return new Validado(false, "Informe um email válido");
        }
        return new Validado(true, "Prestador válido");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
