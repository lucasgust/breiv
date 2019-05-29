Breiv Payment APIs    
=
O projeto contempla APIs para processar e consultar pagamento

Execução
-
1. Clonar o repositório
2. Acessar o diretório raiz do projeto `/breiv` e executar o build via linha de comando: `./gradlew clean build`
3. Acessar o diretório `/docker` e executar `docker-compose up`
4. A URL `http://localhost:8080/actuator/health` deverá retornar `{"status":"UP"}` quando a aplicação estiver no ar

Documentação
-
As APIs e os modelos podem ser vistos na URL `http://localhost:8080/swagger-ui.html`

O arquivo de coleção do Postman se encontra no diretório `/postman`

Arquitetura
-

A aplicação foi desenvolvida em Kotlin, utilizando os princípios do [Clean Architecture](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html)

Ela tem como foco principal a independência das entidades/regras de negócio sobre tecnologias, frameworks ou até mesmo agentes externos. Sua modularização promove manutenibilidade e testabilidade, garantindo qualidade e longevidade à aplicação.

Estrutura do Projeto
-
```
    ├── entity                     # Entidades/regras de negócio
    ├── usecases                   # Casos de uso da aplicação
    ├── web                        # Interface Web
    ├── payment                    # Agente externo que processa os pagamentos (ex.: Moip)
    ├── persistence                # Repositório de dados
    ├── application                # Une todos os módulos
    └── README.md
```