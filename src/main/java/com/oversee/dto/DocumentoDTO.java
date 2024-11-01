package com.oversee.dto;

import com.oversee.entity.Documento;
import com.oversee.model.TipoDocumento;

import java.time.LocalDateTime;

public class DocumentoDTO {

    private Integer id;
    private Byte[] documento;
    private Integer idCliente;
    private Integer idPrestador;
    private Boolean suspenso;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataSuspenso;
    private TipoDocumento tipoDocumento;
    private String nomeArquivo;
    private String extensaoDocumento;
    private String valorDocumento;

    public DocumentoDTO() {}

    public DocumentoDTO(Documento documento) {
        this.id = documento.id.intValue();
        this.dataCadastro = documento.getDataCadastro();
        this.documento = documento.getDocumento();
        this.idCliente = documento.getCliente().id.intValue();
        this.idPrestador = documento.getPrestador().id.intValue();
        this.suspenso = documento.getSuspenso();
        this.dataSuspenso = documento.getDataSuspensao();
        this.tipoDocumento = documento.getTipoDocumento();
        this.valorDocumento = documento.getValorDocumento();
    }

    public DocumentoDTO(Integer id,
                        Byte[] documento,
                        Integer idCliente,
                        Integer idPrestador,
                        Boolean suspenso,
                        LocalDateTime dataCadastro,
                        LocalDateTime dataSuspenso,
                        TipoDocumento tipoDocumento,
                        String nomeArquivo,
                        String extensaoDocumento,
                        String valorDocumento) {
        this.id = id;
        this.documento = documento;
        this.idCliente = idCliente;
        this.idPrestador = idPrestador;
        this.suspenso = suspenso;
        this.dataCadastro = dataCadastro;
        this.dataSuspenso = dataSuspenso;
        this.tipoDocumento = tipoDocumento;
        this.nomeArquivo = nomeArquivo;
        this.extensaoDocumento = extensaoDocumento;
        this.valorDocumento = valorDocumento;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte[] getDocumento() {
        return documento;
    }

    public void setDocumento(Byte[] documento) {
        this.documento = documento;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdPrestador() {
        return idPrestador;
    }

    public void setIdPrestador(Integer idPrestador) {
        this.idPrestador = idPrestador;
    }

    public Boolean getSuspenso() {
        return suspenso;
    }

    public void setSuspenso(Boolean suspenso) {
        this.suspenso = suspenso;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDateTime getDataSuspenso() {
        return dataSuspenso;
    }

    public void setDataSuspenso(LocalDateTime dataSuspenso) {
        this.dataSuspenso = dataSuspenso;
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
