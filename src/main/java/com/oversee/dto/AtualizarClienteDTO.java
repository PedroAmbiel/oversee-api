package com.oversee.dto;

import java.time.LocalDate;

public class AtualizarClienteDTO {

    private Integer id;
    private LocalDate dataNascimento;
    private String nome;
    private byte[] fotoPerfil;

    public AtualizarClienteDTO(){}

    public AtualizarClienteDTO(LocalDate dataNascimento, String nome, Integer id) {
        this.dataNascimento = dataNascimento;
        this.nome = nome;
        this.id = id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(byte[] fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
}
