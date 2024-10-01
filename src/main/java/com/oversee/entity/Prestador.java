package com.oversee.entity;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Username;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prestadores", schema = "quarkus")
public class Prestador extends PanacheEntity {

    @Column
    private String nome;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column
    @Username
    private String cpf;

    @Column
    private String email;

    @Column
    private String telefone;

    @Column
    private String sobrenome;

    @Column
    @Password
    private String senha;

    public Prestador(String nome, String cpf, String email, LocalDate dataNascimento, String telefone, String sobrenome, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.sobrenome = sobrenome;
        this.senha = BcryptUtil.bcryptHash(senha);
        this.dataNascimento = dataNascimento;
    }

    public Prestador() {

    }

    public Long getId() {
        return id;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
