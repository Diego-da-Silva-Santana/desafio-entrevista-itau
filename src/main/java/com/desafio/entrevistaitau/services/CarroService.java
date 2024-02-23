package com.desafio.entrevistaitau.services;

import com.desafio.entrevistaitau.dto.CarroDTO;
import com.desafio.entrevistaitau.entities.Carro;
import com.desafio.entrevistaitau.repositories.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarroService {

    @Autowired
    private CarroRepository repository;

    public List<CarroDTO> listagemCarros() {
        List<Carro> carros = repository.findAll();
        return carros.stream().map(Carro::toCarroDTO).collect(Collectors.toList());
    }


}
