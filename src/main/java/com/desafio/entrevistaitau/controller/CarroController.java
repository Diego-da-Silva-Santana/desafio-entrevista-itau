package com.desafio.entrevistaitau.controller;

import com.desafio.entrevistaitau.dto.CarroDTO;
import com.desafio.entrevistaitau.entities.Carro;
import com.desafio.entrevistaitau.services.CarroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/carros")
public class CarroController {
    @Autowired
    private CarroService service;

    @GetMapping
    public ResponseEntity<List<CarroDTO>> listagemCarros() {
        List<CarroDTO> list = service.listagemCarros();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CarroDTO> verDetalhesCarro(@PathVariable Long id) {
        CarroDTO carroDTO = service.listarCarro(id);
        return ResponseEntity.ok().body(carroDTO);
    }

    @PostMapping
    public ResponseEntity<CarroDTO> adicionarCarro(@Valid @RequestBody Carro carro) {
        CarroDTO carroDTO = service.adicionarCarro(carro);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(carro.getId()).toUri();
        return ResponseEntity.created(uri).body(carroDTO);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<CarroDTO> atualizarDadosCarro(@RequestBody CarroDTO carroDTO, @PathVariable("id") Long id) {
        CarroDTO dadosCarro = service.atualizarDadosCarro(id, carroDTO);

        return ResponseEntity.ok().body(dadosCarro);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarCarro(@PathVariable Long id){
        service.deletarCarro(id);
        return ResponseEntity.noContent().build();

    }
}
