package com.desafio.entrevistaitau.services;

import com.desafio.entrevistaitau.dto.CarroDTO;
import com.desafio.entrevistaitau.entities.Carro;
import com.desafio.entrevistaitau.entities.enums.Status;
import com.desafio.entrevistaitau.repositories.CarroRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(CarroService.class)
class CarroServiceTest {

    @MockBean
    private CarroRepository carroRepository;

    @Autowired
    private CarroService service;

    @Test
    void listagemCarros_QuandoReceberRequisicaoENãoEncontrarDadosNaBaseDeDados_deveretornarListaVazia() {
        when(carroRepository.findAll()).thenReturn(Collections.emptyList());

        List<CarroDTO> carros = service.listagemCarros("Carro1", "Fabricante1", 2000);

        assertTrue(carros.isEmpty());
    }

    @Test
    void listagemCarros_QuandoReceberRequisicaoENaoEncontrarDadosComOsParametros_deveretornarLista() {
        Carro carro = new Carro(1L, "9BWZZZ377VT004251", "Urus", "Automobili Lamborghini ", 2023, "Branco", Status.ALUGADO, "OPA0148");

        when(carroRepository.findAll()).thenReturn(Collections.singletonList(carro));

        List<CarroDTO> carros = service.listagemCarros("Urus", "Automobili Lamborghini ", 2023);

        assertFalse(carros.isEmpty());
    }

    @Test
    void listagemCarros_QuandoReceberRequisicaoEEncontrarDadosComOsParametros_deveretornarListaCorrespondenteAosCriteriosDeFiltros() {
        Carro carro = new Carro(1L, "9BWZZZ377VT004251", "Urus", "Automobili Lamborghini ", 2023, "Branco", Status.ALUGADO, "OPA0148");
        Carro carro1 = new Carro(1L, "9BWZZZ377VT004251", "Urus", "Automobili Lamborghini ", 2023, "Branco", Status.ALUGADO, "OPA0148");

        when(carroRepository.findAll()).thenReturn(Collections.singletonList(carro));

        List<CarroDTO> carros = service.listagemCarros("Urus", "Automobili Lamborghini ", 2023);

        assertFalse(carros.isEmpty());
        assertEquals("Urus", carros.get(0).getNome());
        assertEquals("Automobili Lamborghini ", carros.get(0).getFabricante());
        assertEquals(2023, carros.get(0).getAno());
    }

    @Test
    void listarCarro() {
    }

    @Test
    void adicionarCarro() {
    }

    @Test
    void atualizarStatusCarro() {
    }

    @Test
    void deletarCarro() {
    }

    @Test
    void listarCarrosAgrupadosPorFabricante() {
    }
}