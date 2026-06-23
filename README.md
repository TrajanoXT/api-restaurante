# 🍽️ API Restaurante

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.1-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-JWT-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![Flyway](https://img.shields.io/badge/Flyway-Migration-CC0200?style=for-the-badge&logo=flyway&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-Compose-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)
## 🚀 Tecnologias

- **Java 21**
- **Spring Boot 4.1**
- **Spring Security + JWT** — autenticação e autorização por roles
- **Spring Data JPA**
- **PostgreSQL**
- **Flyway** — versionamento do banco de dados
- **Docker + Docker Compose** — containerização da aplicação e banco
- **SpringDoc OpenAPI** — documentação automática dos endpoints
- **Lombok**
- **JUnit 5 + Mockito** — testes unitários

## 📦 Funcionalidades

- Autenticação com **JWT** e controle de acesso por **roles** (ADMIN, GARCOM)
- Cadastro e gerenciamento de **mesas** com status (LIVRE, OCUPADA, INATIVA)
- Cadastro de **garçons** com validação de CPF customizada
- Cadastro de **produtos** por categoria (Prato principal, Bebida, Entrada, Sobremesa)
- Abertura e gerenciamento de **pedidos** por mesa
- Adição e remoção de **itens** com snapshot de preço no momento do pedido
- Transição de **status do pedido** com regras de negócio (ABERTO → EM_PREPARO → PRONTO → ENTREGUE)
- Cancelamento de pedido com liberação automática da mesa
- Recálculo automático do total do pedido

## 🔐 Autenticação

A API usa JWT. Para acessar os endpoints protegidos:

1. Registre um usuário em `POST /auth/register`
2. Faça login em `POST /auth/login` e copie o token retornado
3. Envie o token no header de todas as requisições:
```
Authorization: Bearer seu_token_aqui
```

### Permissões por role

| Ação | ADMIN | GARCOM |
|------|-------|--------|
| Gerenciar mesas, garçons e produtos | ✅ | ❌ |
| Abrir e operar pedidos | ✅ | ✅ |
| Consultar (GETs) | ✅ | ✅ |

## 🗂️ Estrutura do Projeto

```
src/
├── main/
│   ├── java/dev/trajano/restaurante/
│   │   ├── controller/
│   │   ├── domain/
│   │   │   ├── entity/
│   │   │   └── enums/
│   │   ├── dto/
│   │   ├── exceptions/
│   │   ├── mapper/
│   │   ├── repository/
│   │   ├── security/
│   │   ├── service/
│   │   └── validation/
│   └── resources/
│       └── db/migration/
│           ├── V1__criar_tabelas.sql
│           └── V2__criar_tabela_usuarios.sql
└── test/
```

## ⚙️ Como rodar

### 🐳 Com Docker (recomendado)

Pré-requisitos: **Docker Desktop** instalado e rodando.

```bash
git clone https://github.com/TrajanoXT/api-restaurante.git
cd api-restaurante
docker compose up --build
```

A API estará disponível em `http://localhost:8080`. O banco PostgreSQL sobe automaticamente junto.

Para parar:
```bash
docker compose down
```

### 💻 Localmente

Pré-requisitos: Java 21+, PostgreSQL, Maven.

1. Clone o repositório
```bash
git clone https://github.com/TrajanoXT/api-restaurante.git
cd api-restaurante
```

2. Configure as variáveis de ambiente ou edite o `application.properties`:
```properties
DB_URL=jdbc:postgresql://localhost:5432/restaurante
DB_USER=seu_usuario
DB_PASS=sua_senha
jwt.secret=sua-chave-secreta-com-minimo-32-caracteres
jwt.expiration=86400000
```

3. Rode o projeto — o Flyway cria as tabelas automaticamente:
```bash
./mvnw spring-boot:run
```

## 📋 Endpoints

### Autenticação (público)
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/auth/register` | Registrar usuário |
| POST | `/auth/login` | Login e geração do token |

### Garçons (ADMIN)
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/garcons` | Cadastrar garçom |
| GET | `/garcons` | Listar garçons |
| GET | `/garcons/{id}` | Buscar por ID |
| PUT | `/garcons/{id}` | Atualizar garçom |
| DELETE | `/garcons/{id}` | Deletar garçom |

### Mesas (ADMIN)
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/mesas` | Cadastrar mesa |
| GET | `/mesas` | Listar mesas |
| GET | `/mesas/{id}` | Buscar por ID |
| PUT | `/mesas/{id}` | Atualizar mesa |
| DELETE | `/mesas/{id}` | Deletar mesa |

### Produtos (ADMIN)
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/produto` | Cadastrar produto |
| GET | `/produto` | Listar produtos |
| GET | `/produto/{id}` | Buscar por ID |
| PUT | `/produto/{id}` | Atualizar produto |
| DELETE | `/produto/{id}` | Deletar produto |

### Pedidos (ADMIN e GARCOM)
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/pedidos` | Abrir pedido |
| GET | `/pedidos` | Listar pedidos |
| GET | `/pedidos/{id}` | Buscar por ID |
| PUT | `/pedidos/{id}` | Atualizar garçom do pedido |
| DELETE | `/pedidos/{id}` | Deletar pedido |
| PATCH | `/pedidos/{id}/status` | Avançar status |
| POST | `/pedidos/{id}/itens` | Adicionar item |
| DELETE | `/pedidos/{id}/itens/{itemId}` | Remover item |

## 🔄 Fluxo de Status do Pedido

```
ABERTO → EM_PREPARO → PRONTO → ENTREGUE
  ↓            ↓
CANCELADO   CANCELADO
```

## 📝 Exemplo de uso

**Registrar e fazer login:**
```json
POST /auth/register
{
  "nome": "Carlos Admin",
  "email": "admin@restaurante.com",
  "password": "123456",
  "role": "ADMIN"
}
```
```json
POST /auth/login
{
  "email": "admin@restaurante.com",
  "password": "123456"
}
```

**Criar um pedido:**
```json
POST /pedidos
Authorization: Bearer seu_token

{
  "mesaId": 1,
  "garcomId": 1
}
```

**Adicionar item ao pedido:**
```json
POST /pedidos/1/itens
Authorization: Bearer seu_token

{
  "produtoId": 1,
  "quantidade": 2,
  "observacao": "sem cebola"
}
```

**Avançar status:**
```
PATCH /pedidos/1/status?status=EM_PREPARO
Authorization: Bearer seu_token
```

## 📖 Documentação

Com o projeto rodando, acesse a documentação interativa dos endpoints:
```
http://localhost:8080/swagger-ui/index.html
```

## 🧪 Testes

```bash
./mvnw test
```

Cobertura atual: `CpfValidator` e `PedidoService` com testes unitários via JUnit 5 e Mockito.

## 📌 Próximos passos

- [ ] Paginação nos endpoints de listagem
- [ ] Testes de integração
- [ ] Relatório de faturamento por mesa e garçom
