package com.oversee.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.xml.bind.DatatypeConverter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedido_redefinicao_senha", schema = "oversee-adm")
public class RedefinicaoDeSenha extends PanacheEntity {

    @JoinColumn(name = "fk_prestador", nullable = false)
    @ManyToOne
    private Prestador prestador;

    @Column(name = "data_pedido", nullable = false)
    private LocalDateTime dataPedido;

    @Column(name = "validade", nullable = false)
    private LocalDateTime validade;

    @Column(name = "ativo", nullable = false)
    private boolean ativo;

    @Column(name = "ip_requisitou")
    private String ipRequisitou;

    @Column(name = "identificador")
    private String identificador;

    public RedefinicaoDeSenha(Prestador prestador, String ipRequisitou) throws NoSuchAlgorithmException {
        this.prestador = prestador;
        this.setDataPedido(LocalDateTime.now());
        this.setValidade(LocalDateTime.now().plusMinutes(30));
        this.setAtivo(true);
        this.ipRequisitou = ipRequisitou;
        this.setIdentificador(this.prestador.getSobrenome());
    }

    public RedefinicaoDeSenha() {

    }


    public Prestador getPrestador() {
        return prestador;
    }

    public void setPrestador(Prestador prestador) {
        this.prestador = prestador;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public LocalDateTime getValidade() {
        return validade;
    }

    public void setValidade(LocalDateTime validade) {
        this.validade = validade;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getIpRequisitou() {
        return ipRequisitou;
    }

    public void setIpRequisitou(String ipRequisitou) {
        this.ipRequisitou = ipRequisitou;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(identificador.getBytes());
        byte[] digest = md.digest();
        this.identificador = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
    }
}
