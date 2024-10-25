package com.oversee.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "clientes", schema = "oversee")
public class Cliente extends PanacheEntity {

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String rg;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_prestador", nullable = false)
    private Prestador prestador;

    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;

    @Column(name = "cancelado")
    private Boolean cancelado;

    @Column(name = "data_cancelado")
    private LocalDateTime dataCancelado;

    public Cliente() {

    }

    private int getAnos(){
        int idade = 0;
        idade = LocalDate.now().getYear() - this.dataNascimento.getYear();

        if(this.dataNascimento.getDayOfYear() >= LocalDate.now().getDayOfYear())
            idade ++;

        return idade;
    }


    public Cliente(String nome, LocalDate dataNascimento, String cpf, String rg, Prestador prestador, LocalDate dataCadastro) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.rg = rg;
        this.prestador = prestador;
        this.dataCadastro = dataCadastro;
    }

    public Cliente(String nome, LocalDate dataNascimento, String cpf, String rg, Prestador prestador, LocalDate dataCadastro, Boolean cancelado, LocalDateTime dataCancelado) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.rg = rg;
        this.prestador = prestador;
        this.dataCadastro = dataCadastro;
        this.cancelado = cancelado;
        this.dataCancelado = dataCancelado;
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

    public Prestador getPrestador() {
        return prestador;
    }

    public void setPrestador(Prestador prestador) {
        this.prestador = prestador;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Boolean getCancelado() {
        return cancelado;
    }

    public void setCancelado(Boolean cancelado) {
        this.cancelado = cancelado;
    }

    public LocalDateTime getDataCancelado() {
        return dataCancelado;
    }

    public void setDataCancelado(LocalDateTime dataCancelado) {
        this.dataCancelado = dataCancelado;
    }
}
