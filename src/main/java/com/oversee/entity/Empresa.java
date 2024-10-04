package com.oversee.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "empresas", schema = "oversee")
public class Empresa extends PanacheEntity {

    @Column(name = "razao_social")
    private String razaoSocial;

    @Column(name = "nome_fantasia")
    private String nomeFantasia;

    @Column
    private String cnpj;

    @Column(name = "data_abertura")
    private LocalDate dataAbertura;

    @OneToOne
    @JoinColumn(name = "fk_prestador")
    private Prestador prestador;

    @Column
    private String email;

    @Column
    private String telefone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "fk_endereco")
    private Endereco endereco;

    public Empresa(String razaoSocial, String nomeFantasia, String cnpj, LocalDate dataAbertura, Prestador prestador, String email, String telefone, Endereco endereco) {
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.dataAbertura = dataAbertura;
        this.prestador = prestador;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public Empresa() { }

    public Prestador getPrestador() {
        return prestador;
    }

    public void setPrestador(Prestador prestador) {
        this.prestador = prestador;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
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
}
