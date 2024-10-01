package com.oversee.dto;

import com.oversee.entity.Prestador;

import java.io.Serializable;
import java.time.LocalDate;

public class PrestadorDTO {

    private Integer id;
    private String nome;
    private String email;
    private String telefone;
    private String senha;
    private String cpf;
    private LocalDate dataNascimento;
    private String sobrenome;

    public PrestadorDTO(Prestador prestador){
        this.id = prestador.getId().intValue();
        this.nome = prestador.getNome();
        this.email = prestador.getEmail();
        this.telefone = prestador.getTelefone();
        this.senha = prestador.getSenha();
        this.cpf = prestador.getCpf();
        this.dataNascimento = prestador.getDataNascimento();
        this.sobrenome = prestador.getSobrenome();
    }

    public PrestadorDTO() {}


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
}
