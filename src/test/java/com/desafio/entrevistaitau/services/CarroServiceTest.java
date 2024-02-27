package com.desafio.entrevistaitau.services;

import com.desafio.entrevistaitau.dto.CarroDTO;
import com.desafio.entrevistaitau.entities.Carro;
import com.desafio.entrevistaitau.entities.enums.Status;
import com.desafio.entrevistaitau.exceptions.DuplicateCarException;
import com.desafio.entrevistaitau.exceptions.ResourceNotFoundException;
import com.desafio.entrevistaitau.repositories.CarroRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
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
        when(carroRepository.findAll()).thenReturn(Collections.singletonList(carro));
        List<CarroDTO> carros = service.listagemCarros("Urus", "Automobili Lamborghini ", 2023);

        assertFalse(carros.isEmpty());
        assertEquals("Urus", carros.get(0).getNome());
        assertEquals("Automobili Lamborghini ", carros.get(0).getFabricante());
        assertEquals(2023, carros.get(0).getAno());
    }

    @Test
    void listarCarro_QuandoReceberRequisicaoComIdComoParametroENaoExistir_DeveLancarException() {
        when(carroRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.listarCarro(1L));
    }

    @Test
    void adicionarCarro_QuandoReceberRequisicaoECampoChassiJaExiste_DeveLancarException() {
        Carro carro = new Carro(1L, "9BWZZZ377VT004251", "Lamborghini Urus", "Automobili Lamborghini S.P.A.", 2023, "Branco", Status.ALUGADO, "OPA0148");
        when(carroRepository.existsByChassi(carro.getChassi())).thenReturn(true);

        assertThrows(DuplicateCarException.class, () -> service.adicionarCarro(carro));
    }

    @Test
    void adicionarCarro_QuandoReceberRequisicaoECampoplacaJaExiste_DeveLancarException() {
        Carro carro = new Carro(1L, "9BWZZZ377VT004251", "Lamborghini Urus", "Automobili Lamborghini S.P.A.", 2023, "Branco", Status.ALUGADO, "OPA0148");
        when(carroRepository.existsByPlaca(carro.getPlaca())).thenReturn(true);

        assertThrows(DuplicateCarException.class, () -> service.adicionarCarro(carro));
    }

    @Test
    public void deletarCarro_QuandoReceberRequisicaoComIdECarroExistir_DeveDeletarComSucesso() {
        Long id = 1L;
        when(carroRepository.existsById(id)).thenReturn(true);
        service.deletarCarro(id);

        Mockito.verify(carroRepository, times(1)).deleteById(id);
    }

    @Test
    public void deletarCarro_QuandoReceberRequisicaoComIdECarroNaoExistir_DeveLancarException() {
        Long id = 1L;
        when(carroRepository.existsById(id)).thenReturn(false);
        assertThrows(ResourceNotFoundException.class, () -> {
            service.deletarCarro(id);
        });

        Mockito.verify(carroRepository, times(0)).deleteById(id);
    }
}