package com.desafio.entrevistaitau.config;

import com.desafio.entrevistaitau.entities.Carro;
import com.desafio.entrevistaitau.entities.enums.Status;
import com.desafio.entrevistaitau.repositories.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private CarroRepository carroRepository;

    @Override
    public void run(String... args) throws Exception {

        Carro c1 = new Carro(null, "9BWZZZ377VT004251", "Lamborghini Urus", "Automobili Lamborghini S.P.A.", 2023, "Branco", Status.ALUGADO, "OPA0148");
        Carro c2 = new Carro(null, "1PPZZZ377VT004285", "Monza", "Monza S.P.A.", 2022, "Preto", Status.RESERVADO, "SSS0894");
        Carro c3 = new Carro(null, "I96ZZZ377VT004285", "Corsa", "Chevrollet S.P.A.", 2015, "Preto", Status.RESERVADO, "SSS0894");
        Carro c4 = new Carro(null, "0JRZZZ377VT004211", "New Civic", "Honda S.P.A.", 1995, "Preto", Status.RESERVADO, "SSS0894");
        Carro c5 = new Carro(null, "0JRZZZ377VT004222", "New City", "Chevrollet S.P.A.", 2014, "Preto", Status.RESERVADO, "SSS0894");
        Carro c6 = new Carro(null, "0JRZZZ377VT004299", "New Civic", "Honda S.P.A.", 2000, "Preto", Status.RESERVADO, "SSS0894");
        Carro c7 = new Carro(null, "0JRZZZ377VT004295", "New Civic", "Automobili Lamborghini S.P.A.", 2014, "Preto", Status.RESERVADO, "SSS0894");
        Carro c8 = new Carro(null, "0JRZZZ377VT004278", "New Civic", "Honda S.P.A.", 1998, "Preto", Status.RESERVADO, "SSS0894");
        Carro c9 = new Carro(null, "0JRZZZ377VT004296", "New Civic", "Monza S.P.A.", 2014, "Preto", Status.RESERVADO, "SSS0894");
        Carro c10 = new Carro(null, "0JRZZZ377VT00436", "New Civic", "Chevrollet S.P.A.", 1960, "Preto", Status.RESERVADO, "SSS0894");

        carroRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10));
    }
}
