# Desafio Itau

## Sobre o projeto

O projeto é uma aplicação WEB desenvolvida para participar do processo seletivo para vaga Analista Júnior.

A Aplicação consiste em uma API Rest, onde seja possível receber dados de carros e realizar solicitações HTTP GET, POST, DELETE e PATCH



## Modelo conceitual




## Tecnologias utilizadas

- Java 11
- Spring Boot
- Maven
- JPA / Hibernate
- H2
- JUnit 5
- Mockito 3



## Estrutura

![padrão camadas (1)](https://github.com/Diego-da-Silva-Santana/desafio-entrevista-itau/assets/129296254/227e37e2-6445-4a7f-b294-112f18251017)

## Rotas
[POST]
<b>/carros</b>

Exemplo:<br>
Headers<br>
Content-Type = application/json<br><br>
Body
``` json    
{
	"chassi": "1PPZZZ7T004285",
	"nome": "Monza",
	"fabricante": " Monza S.P.A.",
	"ano": 2022,
	"cor": "Preto",
	"status": "RESERVADO",
	"placa": "SS088"
}
```
Retorno<br>
Status: 201 Created
``` json
{
	"id": 11,
	"chassi": "1PPZZZ7T004285",
	"nome": "Monza",
	"fabricante": " Monza S.P.A.",
	"ano": 2022,
	"cor": "Preto",
	"status": "RESERVADO",
	"placa": "SS088"
}
```


[GET]
<b>/carros/{id}</b> <br>

Exemplo:<br>
/carros/1 <br>
Retorno <br>
Status: 200 OK
``` json
{
	"id": 1,
	"chassi": "9BWZZZ377VT004251",
	"nome": "Lamborghini Urus",
	"fabricante": "Automobili Lamborghini S.P.A.",
	"ano": 2023,
	"cor": "Branco",
	"status": "ALUGADO",
	"placa": "OPA0148"
}
```


[GET]
<b>/carros/agrupados</b> <br>

Exemplo:<br>
/carros/agrupados <br>
Retorno<br>
Status: 200 OK
``` json
{
	"Honda S.P.A.": [
		{
			"id": 4,
			"chassi": "0JRZZZ377VT004211",
			"nome": "New Civic",
			"fabricante": "Honda S.P.A.",
			"ano": 1995,
			"cor": "Preto",
			"status": "RESERVADO",
			"placa": "SSS0894"
		},
		{
			"id": 8,
			"chassi": "0JRZZZ377VT004278",
			"nome": "New Civic",
			"fabricante": "Honda S.P.A.",
			"ano": 1998,
			"cor": "Preto",
			"status": "RESERVADO",
			"placa": "SSS0894"
		},
		{
			"id": 6,
			"chassi": "0JRZZZ377VT004299",
			"nome": "New Civic",
			"fabricante": "Honda S.P.A.",
			"ano": 2000,
			"cor": "Preto",
			"status": "RESERVADO",
			"placa": "SSS0894"
		}
	],
	"Chevrollet S.P.A.": [
		{
			"id": 10,
			"chassi": "0JRZZZ377VT00436",
			"nome": "New Civic",
			"fabricante": "Chevrollet S.P.A.",
			"ano": 1960,
			"cor": "Preto",
			"status": "RESERVADO",
			"placa": "SSS0894"
		},
		{
			"id": 5,
			"chassi": "0JRZZZ377VT004222",
			"nome": "New City",
			"fabricante": "Chevrollet S.P.A.",
			"ano": 2014,
			"cor": "Preto",
			"status": "RESERVADO",
			"placa": "SSS0894"
		},
		{
			"id": 3,
			"chassi": "I96ZZZ377VT004285",
			"nome": "Corsa",
			"fabricante": "Chevrollet S.P.A.",
			"ano": 2015,
			"cor": "Preto",
			"status": "RESERVADO",
			"placa": "SSS0894"
		}
	],
	"Automobili Lamborghini S.P.A.": [
		{
			"id": 7,
			"chassi": "0JRZZZ377VT004295",
			"nome": "New Civic",
			"fabricante": "Automobili Lamborghini S.P.A.",
			"ano": 2014,
			"cor": "Preto",
			"status": "RESERVADO",
			"placa": "SSS0894"
		},
		{
			"id": 1,
			"chassi": "9BWZZZ377VT004251",
			"nome": "Lamborghini Urus",
			"fabricante": "Automobili Lamborghini S.P.A.",
			"ano": 2023,
			"cor": "Branco",
			"status": "ALUGADO",
			"placa": "OPA0148"
		}
	],
	" Monza S.P.A.": [
		{
			"id": 11,
			"chassi": "1PPZZZ7T004285",
			"nome": "Monza",
			"fabricante": " Monza S.P.A.",
			"ano": 2022,
			"cor": "Preto",
			"status": "RESERVADO",
			"placa": "SS088"
		}
	],
	"Monza S.P.A.": [
		{
			"id": 9,
			"chassi": "0JRZZZ377VT004296",
			"nome": "New Civic",
			"fabricante": "Monza S.P.A.",
			"ano": 2014,
			"cor": "Preto",
			"status": "RESERVADO",
			"placa": "SSS0894"
		},
		{
			"id": 2,
			"chassi": "1PPZZZ377VT004285",
			"nome": "Monza",
			"fabricante": "Monza S.P.A.",
			"ano": 2022,
			"cor": "Preto",
			"status": "RESERVADO",
			"placa": "SSS0894"
		}
	]
}
```

[GET]
<b>/carros</b> <br>

Exemplo:<br>
/carros <br>
Retorno<br>
Status: 200 OK
``` json
[
	{
		"id": 1,
		"chassi": "9BWZZZ377VT004251",
		"nome": "Lamborghini Urus",
		"fabricante": "Automobili Lamborghini S.P.A.",
		"ano": 2023,
		"cor": "Branco",
		"status": "ALUGADO",
		"placa": "OPA0148"
	},
	{
		"id": 2,
		"chassi": "1PPZZZ377VT004285",
		"nome": "Monza",
		"fabricante": "Monza S.P.A.",
		"ano": 2022,
		"cor": "Preto",
		"status": "RESERVADO",
		"placa": "SSS0894"
	},
	{
		"id": 3,
		"chassi": "I96ZZZ377VT004285",
		"nome": "Corsa",
		"fabricante": "Chevrollet S.P.A.",
		"ano": 2015,
		"cor": "Preto",
		"status": "RESERVADO",
		"placa": "SSS0894"
	},
	{
		"id": 4,
		"chassi": "0JRZZZ377VT004211",
		"nome": "New Civic",
		"fabricante": "Honda S.P.A.",
		"ano": 1995,
		"cor": "Preto",
		"status": "RESERVADO",
		"placa": "SSS0894"
	},
	{
		"id": 5,
		"chassi": "0JRZZZ377VT004222",
		"nome": "New City",
		"fabricante": "Chevrollet S.P.A.",
		"ano": 2014,
		"cor": "Preto",
		"status": "RESERVADO",
		"placa": "SSS0894"
	},
	{
		"id": 6,
		"chassi": "0JRZZZ377VT004299",
		"nome": "New Civic",
		"fabricante": "Honda S.P.A.",
		"ano": 2000,
		"cor": "Preto",
		"status": "RESERVADO",
		"placa": "SSS0894"
	},
	{
		"id": 7,
		"chassi": "0JRZZZ377VT004295",
		"nome": "New Civic",
		"fabricante": "Automobili Lamborghini S.P.A.",
		"ano": 2014,
		"cor": "Preto",
		"status": "RESERVADO",
		"placa": "SSS0894"
	},
	{
		"id": 8,
		"chassi": "0JRZZZ377VT004278",
		"nome": "New Civic",
		"fabricante": "Honda S.P.A.",
		"ano": 1998,
		"cor": "Preto",
		"status": "RESERVADO",
		"placa": "SSS0894"
	},
	{
		"id": 9,
		"chassi": "0JRZZZ377VT004296",
		"nome": "New Civic",
		"fabricante": "Monza S.P.A.",
		"ano": 2014,
		"cor": "Preto",
		"status": "RESERVADO",
		"placa": "SSS0894"
	},
	{
		"id": 10,
		"chassi": "0JRZZZ377VT00436",
		"nome": "New Civic",
		"fabricante": "Chevrollet S.P.A.",
		"ano": 1960,
		"cor": "Preto",
		"status": "RESERVADO",
		"placa": "SSS0894"
	},
	{
		"id": 11,
		"chassi": "1PPZZZ7T004285",
		"nome": "Monza",
		"fabricante": " Monza S.P.A.",
		"ano": 2022,
		"cor": "Preto",
		"status": "RESERVADO",
		"placa": "SS088"
	}
]
```

[DELETE]
<b>/carros</b>

Exemplo: /carros/1<br>

Retorno<br>
Status: 204 No Content

## Códigos de erros

- <b>404 Not Found</b>: O carro com ID: 1 não foi encontrado na base de dados.
- <b>404 Not Found</b>: Já existe um carro com o mesmo Chassi: 1PPZZZ7T004285.
- <b>404 Not Found</b>: Dados do carro não foi encontrado na base de dados..



## Autor
Diego da Silva Santana

https://www.linkedin.com/in/diego-da-silva-santana-252a41b3/
