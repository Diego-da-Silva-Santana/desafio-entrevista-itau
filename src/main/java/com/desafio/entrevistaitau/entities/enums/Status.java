package com.desafio.entrevistaitau.entities.enums;

import java.util.Optional;

public enum Status {

    ATIVADO(1),
    DESATIVADO(2),
    RESERVADO(3),
    ALUGADO(4);

    private int code;

    private Status(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Status valueOf(int code) {
        for (Status value : Status.values()) {
            if (code == value.getCode()) {
                return value;
            }
        }
        throw new IllegalArgumentException("Código " + code + "do Status do carro inválido ");
    }
}