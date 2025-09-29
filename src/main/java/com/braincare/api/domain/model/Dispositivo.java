package com.braincare.api.domain.model;

public class Dispositivo {

    private Long id;

    private Long usuarioId;

    private TipoDispositivo tipoDispositivo;

    private Integer numeroSerie;

    private Boolean status;

    public enum TipoDispositivo {
        ESP32,
        STM32,
        SENSOR_CARDIACO
    }

}
