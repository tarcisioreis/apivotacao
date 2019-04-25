# apivotacao

API de Votação de Associados em pautas.


Descrição:

Api desenvolvida em Spring Boot persistindo dados com JPA Hibernate em banco de dados local MySQL, 
estrutura baseada em 3 camadas: Controller, Service e Repository, sendo a camada de banco Entity não sendo acessada diretamente 
mas, usando acesso via DTO(Data Transfer Object) pela camada Service.


Requisitos para funcionamento:

1) Ter o Eclipse com Maven para isso somente importar como Projeto Maven;

2) Ter um banco MySQL local ou remoto, configurar no diretório src/main/resources/application.properties os dados para aceso 
   ao banco.


Para ver os Web Services instalei o SwaggerUI para interagir com a API. Para acessar e testar a API no endereço local: http://localhost:8080/swagger-ui.html


Contato: Tarcisio Machado dos Reis - e-mail: tarcisio.reis.ti@gmail.com ou whatsapp 051 9 8490-4355.
