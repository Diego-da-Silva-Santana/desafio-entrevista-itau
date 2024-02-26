package com.desafio.entrevistaitau.repositories;

import com.desafio.entrevistaitau.entities.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro, Long> {

    boolean existsByChassi(String chassi);

    boolean existsByPlaca(String placa);

}
