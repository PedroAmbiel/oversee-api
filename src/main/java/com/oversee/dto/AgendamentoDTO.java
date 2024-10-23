package com.oversee.dto;

import com.oversee.entity.Agendamento;
import com.oversee.model.TipoAgendamento;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AgendamentoDTO implements Serializable {

    private long id;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;

    private String descricao;

    private Integer fkPrestador;

    private Integer fkCliente;

    private TipoAgendamento tipoAgendamento;

    private Boolean cancelado;

    private String titulo;

    public AgendamentoDTO(long id, String descricao, Integer fkPrestador, Integer fkCliente, LocalDateTime dataInicio, LocalDateTime dataFim, TipoAgendamento tipoAgendamento, Boolean cancelado, String titulo) {
        this.id = id;
        this.descricao = descricao;
        this.fkPrestador = fkPrestador;
        this.fkCliente = fkCliente;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.tipoAgendamento = tipoAgendamento;
        this.cancelado = cancelado;
        this.titulo = titulo;
    }

    public AgendamentoDTO(Agendamento agendamento) {
        this.descricao = agendamento.getDescricao();
        this.id = agendamento.id;
        this.dataInicio = agendamento.getDataInicio();
        this.dataFim = agendamento.getDataFim();
        this.tipoAgendamento = agendamento.getTipoAgendamento();
        this.fkCliente = agendamento.getCliente().getId().intValue();
        this.fkPrestador = agendamento.getPrestador().getId().intValue();
        this.cancelado = agendamento.getCancelado();
        this.titulo = agendamento.getTitulo();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getFkPrestador() {
        return fkPrestador;
    }

    public void setFkPrestador(Integer fkPrestador) {
        this.fkPrestador = fkPrestador;
    }

    public Integer getFkCliente() {
        return fkCliente;
    }

    public void setFkCliente(Integer fkCliente) {
        this.fkCliente = fkCliente;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public TipoAgendamento getTipoAgendamento() {
        return tipoAgendamento;
    }

    public void setTipoAgendamento(TipoAgendamento tipoAgendamento) {
        this.tipoAgendamento = tipoAgendamento;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Boolean getCancelado() {
        return cancelado;
    }

    public void setCancelado(Boolean cancelado) {
        this.cancelado = cancelado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
