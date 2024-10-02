package com.oversee.entity;

import com.oversee.model.TipoAgendamento;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "agendamentos", schema = "oversee")
public class Agendamento extends PanacheEntity {

    @Column(name = "data_inicio", nullable = false)
    private LocalDateTime dataInicio;

    @Column(name = "data_fim", nullable = false)
    private LocalDateTime dataFim;

    @ManyToOne
    @JoinColumn(name = "fk_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "fk_prestador", nullable = false)
    private Prestador prestador;

    @Column
    private String descricao;

    @Column(name = "tipo_agendamento", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoAgendamento tipoAgendamento;


    public Agendamento(LocalDateTime dataInicio, LocalDateTime dataFim, Prestador fkPrestador, Cliente cliente, String descricao, TipoAgendamento tipoAgendamento) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.cliente = cliente;
        this.descricao = descricao;
        this.prestador = fkPrestador;
        this.tipoAgendamento = tipoAgendamento;
    }

    public Agendamento() {}


    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Prestador getPrestador() {
        return prestador;
    }

    public void setPrestador(Prestador prestador) {
        this.prestador = prestador;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
}
