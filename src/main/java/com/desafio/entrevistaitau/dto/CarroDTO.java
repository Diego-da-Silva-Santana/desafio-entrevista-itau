package com.desafio.entrevistaitau.dto;

import com.desafio.entrevistaitau.entities.enums.Status;

import java.time.LocalDateTime;
import java.util.Date;

public class CarroDTO {

    private Long id;
    private String chassi;
    private String nome;
    private String fabricante;
    private Date ano;
    private String cor;
    private Status status;
    private String placa;

    public CarroDTO() {
    }

    public CarroDTO(Long id, String chassi,
                    String nome, String fabricante,
                    Date ano, String cor,
                    Status status, String placa) {
        this.id = id;
        this.chassi = chassi;
        this.nome = nome;
        this.fabricante = fabricante;
        this.ano = ano;
        this.cor = cor;
        this.status = status;
        this.placa = placa;
    }

    public Long getId() {
        return id;
    }

    public String getChassi() {
        return chassi;
    }

    public String getNome() {
        return nome;
    }

    public String getFabricante() {
        return fabricante;
    }

    public Date getAno() {
        return ano;
    }

    public String getCor() {
        return cor;
    }

    public Status getStatus() {
        return status;
    }

    public String getPlaca() {
        return placa;
    }
}
