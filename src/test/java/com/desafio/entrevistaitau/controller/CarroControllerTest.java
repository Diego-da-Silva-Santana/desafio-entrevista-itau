package com.desafio.entrevistaitau.controller;

import com.desafio.entrevistaitau.dto.CarroDTO;
import com.desafio.entrevistaitau.entities.Carro;
import com.desafio.entrevistaitau.entities.enums.Status;
import com.desafio.entrevistaitau.services.CarroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarroController.class)
class CarroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarroService carroService;

    @Test
    void listagemCarros_QuandoReceberRequisicao_DeveRetornar200ComListaDeCarros() throws Exception {
        CarroDTO carroDTO1 = new CarroDTO(1L, "9BWZZZ377VT004251", "Urus", "Automobili Lamborghini ", 2023, "Branco", Status.ALUGADO, "OPA0148");
        CarroDTO carroDTO2 = new CarroDTO(2L, "9BWZZZ377VT004252", "Civic Urus", "Automobili Honda", 2021, "Preto", Status.ALUGADO, "OPA0111");

        when(carroService.listagemCarros(any(),any(),any())).thenReturn(Arrays.asList(carroDTO1, carroDTO2));

        mockMvc.perform(MockMvcRequestBuilders.get("/carros")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].chassi").value("9BWZZZ377VT004251"))
                .andExpect(jsonPath("$[0].nome").value("Urus"))
                .andExpect(jsonPath("$[0].fabricante").value("Automobili Lamborghini "))
                .andExpect(jsonPath("$[0].ano").value(2023))
                .andExpect(jsonPath("$[0].cor").value("Branco"))
                .andExpect(jsonPath("$[0].status").value(Status.ALUGADO.name()))
                .andExpect(jsonPath("$[1].chassi").value("9BWZZZ377VT004252"))
                .andExpect(jsonPath("$[1].nome").value("Civic Urus"))
                .andExpect(jsonPath("$[1].fabricante").value("Automobili Honda"))
                .andExpect(jsonPath("$[1].ano").value(2021))
                .andExpect(jsonPath("$[1].cor").value("Preto"))
                .andExpect(jsonPath("$[1].status").value(Status.ALUGADO.name()));
    }

    @Test
    void listarCarros_QuandoReceberRequisicaoComParametroId_DeveRetornar200ComDadosDoCarroReferenteAoId() throws Exception {
        CarroDTO carroDTO = new CarroDTO(1L, "9BWZZZ377VT004251", "Urus", "Automobili Lamborghini ", 2023, "Branco", Status.ALUGADO, "OPA0148");

        when(carroService.listarCarro(1L)).thenReturn(carroDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/carros/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.chassi").value("9BWZZZ377VT004251"))
                .andExpect(jsonPath("$.nome").value("Urus"))
                .andExpect(jsonPath("$.fabricante").value("Automobili Lamborghini "))
                .andExpect(jsonPath("$.ano").value(2023))
                .andExpect(jsonPath("$.cor").value("Branco"))
                .andExpect(jsonPath("$.status").value(Status.ALUGADO.name()))
                .andExpect(jsonPath("$.placa").value("OPA0148"));
    }

    @Test
    void adicionarCarro_QuandoReceberRequisicaoComDadosCarroNoCorpo_deveRetornar201Created() throws Exception {

        Carro carro = new Carro(1L, "9BWZZZ377VT004251", "Urus", "Automobili Lamborghini ", 2023, "Branco", Status.ALUGADO, "OPA0148");
        CarroDTO carroDTO = new CarroDTO(1L, "9BWZZZ377VT004251", "Urus", "Automobili Lamborghini ", 2023, "Branco", Status.ALUGADO, "OPA0148");

        when(carroService.adicionarCarro(any(Carro.class))).thenReturn(carroDTO);

        mockMvc.perform(post("/carros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(carro)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.chassi").value("9BWZZZ377VT004251"))
                .andExpect(jsonPath("$.nome").value("Urus"))
                .andExpect(jsonPath("$.fabricante").value("Automobili Lamborghini "))
                .andExpect(jsonPath("$.ano").value(2023))
                .andExpect(jsonPath("$.cor").value("Branco"))
                .andExpect(jsonPath("$.status").value(Status.ALUGADO.name()))
                .andExpect(jsonPath("$.placa").value("OPA0148"));

        Mockito.verify(carroService, times(1)).adicionarCarro(any(Carro.class));
    }

    @Test
    void deletarCarro_QuandoRecebeberRequisicaocomParametroId_deveRetornar204NoContent() throws Exception {

        Long carroId = 1L;
        doNothing().when(carroService).deletarCarro(carroId);

        mockMvc.perform(delete("/carros/{id}", carroId))
                .andExpect(status().isNoContent());

        Mockito.verify(carroService, times(1)).deletarCarro(carroId);
    }

    @Test
    void atualizarStatusCarro_QuandoReceberRequisicaoComStatus_deveRetonar200comStatusAtualizado() throws Exception {

        CarroDTO carroDTO = new CarroDTO(1L, "9BWZZZ377VT004251", "Urus", "Automobili Lamborghini ", 2023, "Branco", Status.ALUGADO, "OPA0148");
        CarroDTO carroAtualizado = new CarroDTO(1L, "9BWZZZ377VT004251", "Urus", "Automobili Lamborghini ", 2023, "Branco", Status.ALUGADO, "OPA0148");

        Mockito.when(carroService.atualizarStatusCarro(Mockito.anyLong(), Mockito.any(CarroDTO.class))).thenReturn(carroAtualizado);

        mockMvc.perform(patch("/carros/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(carroDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ALUGADO"));

        Mockito.verify(carroService, Mockito.times(1)).atualizarStatusCarro(Mockito.anyLong(), Mockito.any(CarroDTO.class));
    }
}