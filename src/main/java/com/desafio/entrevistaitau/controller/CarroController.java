package com.desafio.entrevistaitau.controller;

import com.desafio.entrevistaitau.dto.CarroDTO;
import com.desafio.entrevistaitau.services.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}