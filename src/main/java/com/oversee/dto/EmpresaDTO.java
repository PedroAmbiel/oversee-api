package com.oversee.dto;

import com.oversee.entity.Empresa;
import java.time.LocalDate;

public class EmpresaDTO {

    private Integer id;
    private Integer fkPrestador;

    private String razaoSocial;
    private String nomeFantasia;
    private String telefone;
    private String email;
    private String cnpj;

    private EnderecoDTO endereco;

    private LocalDate dataAbertura;

    public EmpresaDTO(Integer id, Integer fkPrestador, String razaoSocial, String nomeFantasia, String telefone, String email, String cnpj, EnderecoDTO endereco, LocalDate dataAbertura) {
        this.id = id;
        this.fkPrestador = fkPrestador;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.telefone = telefone;
        this.email = email;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.dataAbertura = dataAbertura;
    }

    public EmpresaDTO(Empresa empresa) {
        this.id = empresa.id.intValue();
        this.cnpj = empresa.getCnpj();
        this.email = empresa.getEmail();
        this.razaoSocial = empresa.getRazaoSocial();
        this.nomeFantasia = empresa.getNomeFantasia();
        this.telefone = empresa.getTelefone();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFkPrestador() {
        return fkPrestador;
    }

    public void setFkPrestador(Integer fkPrestador) {
        this.fkPrestador = fkPrestador;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }
}
