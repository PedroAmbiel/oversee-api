package com.oversee.dto;

import java.time.LocalDateTime;

public class AgendamentoLimiteConsumesDTO {

    LocalDateTime dataInicio;
    LocalDateTime dataFim;

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
}
