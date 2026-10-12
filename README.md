# SpringCep - API de Consulta de CEP

## 📋 Descrição

SpringCep é uma API REST desenvolvida em Spring Boot que permite consultar informações de endereços através de CEPs. O projeto consome a API pública do ViaCEP para fornecer dados completos de localização.

## 🚀 Tecnologias Utilizadas

- **Java 17** - Linguagem de programação
- **Spring Boot 3.5.4** - Framework para desenvolvimento de aplicações Java
- **Spring Web** - Módulo para criação de APIs REST
- **Maven** - Gerenciador de dependências e build
- **RestTemplate** - Cliente HTTP para consumir APIs externas

## 🔌 API Consumida

### ViaCEP
- **URL Base**: `https://viacep.com.br/ws/{cep}/json`
- **Descrição**: API gratuita que retorna informações de endereços brasileiros através de CEPs
- **Documentação**: [ViaCEP](https://viacep.com.br)
- **Formato de Resposta**: JSON

## 📁 Estrutura do Projeto

```
SpringCep/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/brescolanovaeratech/SpringCep/
│   │   │       ├── controller/
│   │   │       │   └── ConsultaController.java
│   │   │       ├── domain/
│   │   │       │   └── CepDomain.java
│   │   │       └── SpringCepApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
└── pom.xml
```

## 🛠️ Como Executar

### Pré-requisitos
- Java 17 ou superior
- Maven 3.6+

### Passos para execução
1. Clone o repositório
2. Navegue até a pasta do projeto: `cd SpringCep`
3. Execute o projeto: `./mvnw spring-boot:run`
4. A aplicação estará disponível em: `http://localhost:8080`

## 📡 Endpoints da API

### Consulta de CEP
- **URL**: `GET /consulta/{cep}`
- **Parâmetro**: `cep` - CEP no formato: 00000000
- **Exemplo**: `GET /consulta/01001000`
- **Resposta**: Objeto com informações do endereço

## 📊 Exemplo de Resposta

```json
{
  "cep": "01001-000",
  "logradouro": "Praça da Sé",
  "complemento": "",
  "bairro": "Sé",
  "localidade": "São Paulo",
  "uf": "SP",
  "ibge": "3550308",
  "gia": "1004",
  "ddd": "11",
  "siafi": "7107"
}
```

## 🏗️ Arquitetura

- **Controller Layer**: Gerencia as requisições HTTP
- **Domain Layer**: Define as entidades do sistema
- **Service Layer**: Lógica de negócio (a ser implementada)
- **Repository Layer**: Acesso a dados (a ser implementado)

## 🔧 Configurações

O projeto utiliza as configurações padrão do Spring Boot. Para personalizar, edite o arquivo `application.properties`.

## 🧪 Testes

Execute os testes com:
```bash
./mvnw test
```

## 📈 Melhorias Futuras

- [ ] Implementar cache para consultas frequentes
- [ ] Adicionar validação de CEP
- [ ] Implementar tratamento de erros
- [ ] Adicionar documentação com Swagger
- [ ] Implementar testes unitários e de integração
- [ ] Adicionar logging estruturado
- [ ] Implementar métricas com Actuator

## 👨‍💻 Desenvolvido por

Este projeto foi desenvolvido pelos alunos da **[Nova Era Tech](https://www.escolanovaeratech.com.br)** - uma escola de programação focada em IA e empreendedorismo.

### Sobre a Nova Era Tech
A Nova Era Tech acredita que os estudos abrem portas. Nossa missão é ensinar programação com propósito, aplicando aprendizados em projetos reais e utilizando inteligência artificial ao seu favor, seja para conquistar sua primeira vaga na área ou para construir sua independência com programação.

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.

## 🤝 Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.

## 📞 Suporte

Para dúvidas sobre o projeto, entre em contato através do nosso site: [https://www.escolanovaeratech.com.br](https://www.escolanovaeratech.com.br)
