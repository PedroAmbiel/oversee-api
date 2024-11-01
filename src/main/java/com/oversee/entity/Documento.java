package com.oversee.entity;

import com.oversee.dto.ClienteDTO;
import com.oversee.dto.DocumentoDTO;
import com.oversee.model.TipoDocumento;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "documentos", schema = "oversee")
public class Documento extends PanacheEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_cliente", nullable = false)
    private Cliente cliente;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "suspenso", nullable = false)
    private Boolean suspenso;

    @Column(name = "data_suspensao")
    private LocalDateTime dataSuspensao;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_prestador", nullable = false)
    private Prestador prestador;

    @Column(name = "tipo_documento", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;

    @Column(name = "documento", nullable = false)
    @Lob
    private Byte[] documento;

    @Column(name = "nome_arquivo", nullable = false)
    private String nomeArquivo;

    @Column(name = "extensao_documento")
    private String extensaoDocumento;

    @Column(name = "valor_documento")
    private String valorDocumento;

    public Documento() {

    }

    public Documento(Cliente cliente, Boolean suspenso, Prestador prestador, Byte[] documento, String nomeArquivo, TipoDocumento tipoDocumento, String extensaoDocumento, String valorDocumento) {
        this.cliente = cliente;
        this.dataCadastro = LocalDateTime.now();
        this.suspenso = suspenso;
        this.prestador = prestador;
        this.documento = documento;
        this.nomeArquivo = nomeArquivo;
        this.tipoDocumento = tipoDocumento;
        this.extensaoDocumento = extensaoDocumento;
        this.valorDocumento = valorDocumento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Boolean getSuspenso() {
        return suspenso;
    }

    public void setSuspenso(Boolean suspenso) {
        this.suspenso = suspenso;
    }

    public LocalDateTime getDataSuspensao() {
        return dataSuspensao;
    }

    public void setDataSuspensao(LocalDateTime dataSuspensao) {
        this.dataSuspensao = dataSuspensao;
    }

    public Prestador getPrestador() {
        return prestador;
    }

    public void setPrestador(Prestador prestador) {
        this.prestador = prestador;
    }

    public Byte[] getDocumento() {
        return documento;
    }

    public void setDocumento(Byte[] documento) {
        this.documento = documento;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getExtensaoDocumento() {
        return extensaoDocumento;
    }

    public void setExtensaoDocumento(String extensaoDocumento) {
        this.extensaoDocumento = extensaoDocumento;
    }

    public String getValorDocumento() {
        return valorDocumento;
    }

    public void setValorDocumento(String valorDocumento) {
        this.valorDocumento = valorDocumento;
    }
}
