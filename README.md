# Escola_MVC

API REST de gestão escolar desenvolvida com **Spring Boot** seguindo o padrão **MVC** em camadas (Controller → Service → Repository → Entity). O projeto cadastra e lista **Professores** e **Salas** em um banco **MySQL**, e os endpoints foram testados com o **Postman** (métodos `GET` e `POST`).
---

## Estrutura do projeto

```
Escola_MVC
├── .mvn/wrapper
├── src
│   ├── main
│   │   ├── java/com/seduc2/escola
│   │   │   ├── EscolaApplication.java      # Classe principal
│   │   │   ├── controller
│   │   │   │   ├── ProfessorController.java
│   │   │   │   └── SalaController.java
│   │   │   ├── service
│   │   │   │   ├── ProfessorService.java
│   │   │   │   └── SalaService.java
│   │   │   ├── repository
│   │   │   │   ├── ProfessorRepository.java
│   │   │   │   └── SalaRepository.java
│   │   │   └── entity
│   │   │       ├── Professor.java
│   │   │       └── Sala.java
│   │   └── resources
│   │       └── application.properties
│   └── test
│       └── java/com/seduc2/escola/EscolaApplicationTests.java
├── pom.xml
├── mvnw / mvnw.cmd
└── README.md
```

### Responsabilidade de cada camada

- **Entity** — classes mapeadas com `@Entity`, que viram tabelas no banco.
- **Repository** — interfaces que estendem `JpaRepository` e fazem o acesso ao banco.
- **Service** — regras de negócio; intermedeia o controller e o repository.
- **Controller** — expõe os endpoints REST (`@RestController`).

---

## Classes e atributos

### `Professor`

| Atributo | Tipo | Descrição |
|---|---|---|
| `id` | `int` | Chave primária, gerada automaticamente (`IDENTITY`) |
| `nome` | `String` | Nome do professor |
| `idade` | `int` | Idade do professor |
| `cpf` | `int` | CPF do professor |

Possui construtor vazio, construtor completo, construtor sem `id`, além de *getters* e *setters*.

### `Sala`

| Atributo | Tipo | Descrição |
|---|---|---|
| `id` | `int` | Chave primária, gerada automaticamente (`IDENTITY`) |
| `nome` | `String` | Nome/identificação da sala |
| `cadeiras` | `String` | Quantidade de cadeiras |
| `mesas` | `int` | Quantidade de mesas |
| `alunos` | `int` | Quantidade de alunos |

Possui construtor vazio, construtor completo, construtor sem `id`, além de *getters* e *setters*.

### Métodos por camada

| Classe | Método | Função |
|---|---|---|
| `ProfessorController` | `procurarProfessor()` | `GET` — lista todos os professores |
| `ProfessorController` | `criarPerfil(Professor)` | `POST` — cadastra um professor |
| `ProfessorService` | `procurarProfessor()` | Retorna `repository.findAll()` |
| `ProfessorService` | `criarPerfil(Professor)` | Salva o professor com `repository.save()` |
| `SalaController` | `mostrarSala()` | `GET` — lista todas as salas |
| `SalaController` | `criarSala(Sala)` | `POST` — cadastra uma sala |
| `SalaService` | `buscarTodos()` | Retorna `repository.findAll()` |
| `SalaService` | `criarSala(Sala)` | Salva a sala com `repository.save()` |

---

## Endpoints

Base URL: `http://localhost:8080`

| Método | Rota | Descrição |
|---|---|---|
| `GET` | `/professores` | Lista todos os professores |
| `POST` | `/professores` | Cadastra um novo professor |
| `GET` | `/` | Lista todas as salas |
| `POST` | `/` | Cadastra uma nova sala |

> As rotas de `Sala` estão na raiz (`/`), pois o `SalaController` usa `@RequestMapping()` sem caminho.

---

## Como executar

### Pré-requisitos

- JDK 25
- MySQL em execução na porta `3306`
- Postman (ou outro cliente HTTP) para testar

### 1. Clonar o repositório

```bash
git clone https://github.com/MemzOp/Escola_MVC.git
cd Escola_MVC
```

### 2. Criar o banco de dados

```sql
CREATE DATABASE escola;
```

### 3. Configurar o `application.properties`

Arquivo: `src/main/resources/application.properties`

```properties
spring.application.name=escola
spring.datasource.url=jdbc:mysql://localhost:3306/escola
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
server.error.include-message=always
server.error.include-binding-errors=always
```

Ajuste `username` e `password` conforme o seu MySQL. Com `ddl-auto=update`, as tabelas `professor` e `sala` são criadas automaticamente na primeira execução.

### 4. Rodar a aplicação

```bash
# Linux / macOS
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

Ou execute a classe `EscolaApplication` direto pela IDE (IntelliJ). A API sobe em `http://localhost:8080`.

---

## Testando no Postman

### Professores

**GET — listar professores**

```
GET http://localhost:8080/professores
```

**POST — cadastrar professor**

```
POST http://localhost:8080/professores
Content-Type: application/json
```

```json
{
  "nome": "Carlos Silva",
  "idade": 40,
  "cpf": 123456789
}
```

### Salas

**GET — listar salas**

```
GET http://localhost:8080/
```

**POST — cadastrar sala**

```
POST http://localhost:8080/
Content-Type: application/json
```

```json
{
  "nome": "Sala 101",
  "cadeiras": "30",
  "mesas": 15,
  "alunos": 28
}
```

### Passo a passo no Postman

1. Crie uma nova requisição e escolha o método (`GET` ou `POST`).
2. Informe a URL do endpoint.
3. Nos `POST`, abra a aba **Body**, selecione **raw** e o formato **JSON**, e cole o corpo da requisição.
4. Clique em **Send**.
5. Faça um `GET` em seguida para confirmar que o registro foi salvo.

O `POST` não retorna corpo (os métodos são `void`), então o sucesso aparece como status **200 OK**.

---

## Possíveis melhorias

- Alterar o tipo de `cpf` em `Professor` para `String` ou `long`: um CPF tem 11 dígitos e ultrapassa o limite do tipo `int` (2.147.483.647).
- Alterar `cadeiras` em `Sala` para `int`, igual a `mesas` e `alunos`.
- Adicionar endpoints `PUT` e `DELETE` para completar o CRUD.
- Retornar o objeto salvo e o status `201 Created` nos `POST`.
- Validar os dados de entrada (Bean Validation).
- Mover usuário e senha do banco para variáveis de ambiente.

---

## Autor

Desenvolvido por **MemzOp** como atividade do SENAI.
