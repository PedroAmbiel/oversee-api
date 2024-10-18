package com.oversee.dto;

import com.oversee.entity.Cliente;
import io.quarkus.hibernate.orm.panache.common.ProjectedFieldName;

import java.time.LocalDate;

public class ClienteDTO {

    private Integer id;
    private LocalDate dataNascimento;
    private String nome;
    private String rg;
    private String cpf;
    private Integer fkPrestador;
    private LocalDate dataCadastro;

    public ClienteDTO(Cliente cliente){
        this.id = cliente.getId().intValue();
        this.dataNascimento = cliente.getDataNascimento();
        this.nome = cliente.getNome();
        this.rg = cliente.getRg();
        this.cpf = cliente.getCpf();
        this.fkPrestador = cliente.getPrestador().getId().intValue();
        this.dataCadastro = cliente.getDataCadastro();
    }

//    public ClienteDTO(LocalDate dataNascimento, String nome, String rg, String cpf, @ProjectedFieldName("p.id") Integer fkPrestador) {
//        this.dataNascimento = dataNascimento;
//        this.nome = nome;
//        this.rg = rg;
//        this.cpf = cpf;
//        this.fkPrestador = fkPrestador;
//    }

    public ClienteDTO() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getFkPrestador() {
        return fkPrestador;
    }

    public void setFkPrestador(Integer fkPrestador) {
        this.fkPrestador = fkPrestador;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
