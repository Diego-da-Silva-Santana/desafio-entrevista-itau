package com.desafio.entrevistaitau.config;

import com.desafio.entrevistaitau.entities.Carro;
import com.desafio.entrevistaitau.entities.enums.Status;
import com.desafio.entrevistaitau.repositories.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;
import java.util.Date;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private CarroRepository carroRepository;

    @Override
    public void run(String... args) throws Exception {

        Carro c1 = new Carro(null,"9BWZZZ377VT004251","Lamborghini Urus", "Automobili Lamborghini S.P.A.", new Date(2023-11-28), "Branco", Status.ALUGADO, "OPA0148");
        Carro c2 = new Carro(null,"1PPZZZ377VT004285","Monza", " Monza S.P.A.", new Date(2023-11-28), "Preto", Status.RESERVADO, "SSS0894");

        carroRepository.saveAll(Arrays.asList(c1,c2));


    }
}
