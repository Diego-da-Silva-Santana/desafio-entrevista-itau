package com.desafio.entrevistaitau.entities;

import com.desafio.entrevistaitau.dto.CarroDTO;
import com.desafio.entrevistaitau.entities.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_carro")
public class Carro {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Chassi obrigatório")
    private String chassi;
    @NotBlank(message = "Nome do carro obrigatório")
    private String nome;
    @NotBlank(message = "Nome do fabricante obrigatório")
    private String fabricante;
    @NotNull(message = " Data do ano obrigatório")
    private Integer ano;
    @NotBlank(message = "Cor do carro obrigatório")
    private String cor;
    @NotNull(message = "Status obrigatório")
    private Integer status;
    @NotBlank(message = "Placa do carro obrigatório")
    private String placa;

    public Carro() {
    }

    public Carro(Long id, String chassi,
                 String nome, String fabricante,
                 Integer ano, String cor,
                 Status status, String placa) {
        this.id = id;
        this.chassi = chassi;
        this.nome = nome;
        this.fabricante = fabricante;
        this.ano = ano;
        this.cor = cor;
        setStatus(status);
        this.placa = placa;
    }

    public CarroDTO toCarroDTO() {
        return new CarroDTO(id, chassi, nome, fabricante, ano, cor, Status.valueOf(status), placa);
    }

    public void atualizacaoDadosCarro(CarroDTO carroDTO) {

        if (carroDTO.getChassi() != null) {
            this.chassi = carroDTO.getChassi();
        }
        if (carroDTO.getNome() != null) {
            this.nome = carroDTO.getNome();
        }
        if (carroDTO.getFabricante() != null) {
            this.fabricante = carroDTO.getFabricante();
        }
        if (carroDTO.getAno() != null) {
            this.ano = carroDTO.getAno();
        }
        if (carroDTO.getCor() != null) {
            this.cor = carroDTO.getCor();
        }
        if (carroDTO.getStatus() != null) {
            this.status = carroDTO.getStatus().getCode();
        }
        if (carroDTO.getPlaca() != null) {
            this.placa = carroDTO.getPlaca();
        }
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

    public Integer getAno() {
        return ano;
    }

    public String getCor() {
        return cor;
    }

    public Status getStatus() {
        return Status.valueOf(status);
    }

    public String getPlaca() {
        return placa;
    }

    public void setStatus(Status status) {
        if (status != null) {
            this.status = status.getCode();
        }
    }
}