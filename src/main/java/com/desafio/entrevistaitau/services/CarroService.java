package com.desafio.entrevistaitau.services;

import com.desafio.entrevistaitau.dto.CarroDTO;
import com.desafio.entrevistaitau.entities.Carro;
import com.desafio.entrevistaitau.exceptions.DuplicateCarException;
import com.desafio.entrevistaitau.exceptions.ResourceNotFoundException;
import com.desafio.entrevistaitau.repositories.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {

    @Autowired
    private CarroRepository repository;

    public List<CarroDTO> listagemCarros(String nome, String fabricante, Integer ano) {
        List<Carro> carros = repository.findAll();

        if (nome != null) {
            carros = carros.stream()
                    .filter(carro -> carro.getNome().equals(nome))
                    .collect(Collectors.toList());
        }

        if (fabricante != null) {
            carros = carros.stream()
                    .filter(carro -> carro.getFabricante().equals(fabricante))
                    .collect(Collectors.toList());
        }

        if (ano != null) {
            carros = carros.stream()
                    .filter(carro -> carro.getAno().equals(ano))
                    .collect(Collectors.toList());
        }
        return carros.stream().map(Carro::toCarroDTO).collect(Collectors.toList());
    }

    public CarroDTO listarCarro(Long id) {
        Optional<Carro> optionalCarro = repository.findById(id);
        return optionalCarro.orElseThrow(() -> new ResourceNotFoundException("O carro com ID: " + id + " não foi encontrado na base de dados.")).toCarroDTO();
    }

    public CarroDTO adicionarCarro(Carro carro) {
        if (repository.existsByChassi(carro.getChassi())) {
            throw new DuplicateCarException("Já existe um carro com o mesmo Chassi: " + carro.getChassi());
        }

        if (repository.existsByPlaca(carro.getPlaca())) {
            throw new DuplicateCarException("Já existe um carro com a mesma Placa: " + carro.getPlaca());
        }
        return repository.save(carro).toCarroDTO();
    }

    public CarroDTO atualizarStatusCarro(Long id, CarroDTO carroDTO) {

        Optional<Carro> possivelDadosCarroEntity = this.repository.findById(id);
        if (possivelDadosCarroEntity.isEmpty()) {
            throw new ResourceNotFoundException("Dados do carro não foi encontrado na base de dados.");
        }
        Carro carro = possivelDadosCarroEntity.get();
        carro.atualizacaoDadosCarro(carroDTO);
        repository.save(carro);

        return carro.toCarroDTO();
    }

    public void deletarCarro(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("O carro com ID: " + id + " não foi encontrado na base de dados.");
        }
        repository.deleteById(id);
    }

    public Map<String, List<CarroDTO>> listarCarrosAgrupadosPorFabricante() {

        List<Carro> carros = repository.findAll();

        return carros.stream()
                .sorted(Comparator.comparing(Carro::getFabricante)
                        .thenComparing(Carro::getAno))
                .map(Carro::toCarroDTO)
                .collect(Collectors.groupingBy(CarroDTO::getFabricante));
    }
}