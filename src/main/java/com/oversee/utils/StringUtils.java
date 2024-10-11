package com.oversee.utils;

public class StringUtils {

    public static String removerPontuacaoCpfCnpj(String documento){
        return documento.replace("[^0-9]", "");
    }
}
