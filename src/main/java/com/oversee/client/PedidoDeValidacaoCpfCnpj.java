package com.oversee.client;

public class PedidoDeValidacaoCpfCnpj extends Comunicado {

    private String documento;

    public PedidoDeValidacaoCpfCnpj(String documento) {
        this.documento = documento;
    }

    public static boolean validarCpfCnpj(PedidoDeValidacaoCpfCnpj documento) {

        if(documento.getDocumento().length() == 11){
            // Remove caracteres não numéricos
            documento.setDocumento(documento.getDocumento().replaceAll("[^\\d]", ""));

            // Verifica se o CPF tem 11 dígitos ou é uma sequência de números repetidos
            if (documento.getDocumento().matches("(\\d)\\1{10}")) {
                return false;
            }

            // Calcula o primeiro dígito verificador
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += (documento.getDocumento().charAt(i) - '0') * (10 - i);
            }
            int primeiroDigitoVerificador = 11 - (soma % 11);
            if (primeiroDigitoVerificador >= 10) {
                primeiroDigitoVerificador = 0;
            }

            // Verifica o primeiro dígito
            if (primeiroDigitoVerificador != (documento.getDocumento().charAt(9) - '0')) {
                return false;
            }

            // Calcula o segundo dígito verificador
            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += (documento.getDocumento().charAt(i) - '0') * (11 - i);
            }
            int segundoDigitoVerificador = 11 - (soma % 11);
            if (segundoDigitoVerificador >= 10) {
                segundoDigitoVerificador = 0;
            }

            // Verifica o segundo dígito
            return segundoDigitoVerificador == (documento.getDocumento().charAt(10) - '0');


            //Valida CNPJ
        }else if(documento.getDocumento().length() == 14){
            documento.setDocumento(documento.getDocumento().replaceAll("[^\\d]", ""));

            // Verifica se o CNPJ tem 14 dígitos ou é uma sequência de números repetidos
            if (documento.getDocumento().matches("(\\d)\\1{13}")) {
                return false;
            }

            // Arrays de multiplicadores para o cálculo dos dígitos verificadores
            int[] multiplicador1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            int[] multiplicador2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

            // Calcula o primeiro dígito verificador
            int soma = 0;
            for (int i = 0; i < 12; i++) {
                soma += (documento.getDocumento().charAt(i) - '0') * multiplicador1[i];
            }
            int primeiroDigitoVerificador = 11 - (soma % 11);
            if (primeiroDigitoVerificador >= 10) {
                primeiroDigitoVerificador = 0;
            }

            // Verifica o primeiro dígito
            if (primeiroDigitoVerificador != (documento.getDocumento().charAt(12) - '0')) {
                return false;
            }

            // Calcula o segundo dígito verificador
            soma = 0;
            for (int i = 0; i < 13; i++) {
                soma += (documento.getDocumento().charAt(i) - '0') * multiplicador2[i];
            }
            int segundoDigitoVerificador = 11 - (soma % 11);
            if (segundoDigitoVerificador >= 10) {
                segundoDigitoVerificador = 0;
            }

            // Verifica o segundo dígito
            return segundoDigitoVerificador == (documento.getDocumento().charAt(13) - '0');

        }else{
            return false;
        }
    }


    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
}
