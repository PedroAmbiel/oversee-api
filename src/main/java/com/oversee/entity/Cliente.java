package com.oversee.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "clientes", schema = "quarkus")
public class Cliente extends PanacheEntity {

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String rg;

    public Cliente() {

    }


    private int getAnos(){
        int idade = 0;
        idade = LocalDate.now().getYear() - this.dataNascimento.getYear();

        if(this.dataNascimento.getDayOfYear() >= LocalDate.now().getDayOfYear())
            idade ++;

        return idade;
    }


    public Cliente(String nome, LocalDate dataNascimento, String cpf, String rg) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.rg = rg;
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

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }
}
