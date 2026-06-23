# 🍽️ API Restaurante

API REST para gerenciamento completo de restaurante — controle de mesas, garçons, cardápio e pedidos com regras de negócio reais.

## 🚀 Tecnologias

- **Java 21**
- **Spring Boot 4.1**
- **Spring Data JPA**
- **PostgreSQL**
- **Flyway** — versionamento do banco de dados
- **Lombok**
- **JUnit 5 + Mockito** — testes unitários

## 📦 Funcionalidades

- Cadastro e gerenciamento de **mesas** com status (LIVRE, OCUPADA, INATIVA)
- Cadastro de **garçons** com validação de CPF customizada
- Cadastro de **produtos** por categoria (Prato principal, Bebida, Entrada, Sobremesa)
- Abertura e gerenciamento de **pedidos** por mesa
- Adição e remoção de **itens** com snapshot de preço no momento do pedido
- Transição de **status do pedido** com regras de negócio (ABERTO → EM_PREPARO → PRONTO → ENTREGUE)
- Cancelamento de pedido com liberação automática da mesa
- Recálculo automático do total do pedido

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
│   │   ├── service/
│   │   └── validation/
│   └── resources/
│       └── db/migration/
└── test/
```

## ⚙️ Como rodar

### Pré-requisitos

- Java 21+
- PostgreSQL rodando localmente
- Maven

### Configuração

1. Clone o repositório
```bash
git clone https://github.com/TrajanoXT/api-restaurante.git
cd api-restaurante
```

2. Configure o banco no `application.properties`
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/restaurante
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

3. Rode o projeto — o Flyway cria as tabelas automaticamente
```bash
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`

## 📋 Endpoints

### Garçons
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/garcons` | Cadastrar garçom |
| GET | `/garcons` | Listar garçons |
| GET | `/garcons/{id}` | Buscar por ID |
| PUT | `/garcons/{id}` | Atualizar garçom |
| DELETE | `/garcons/{id}` | Deletar garçom |

### Mesas
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/mesas` | Cadastrar mesa |
| GET | `/mesas` | Listar mesas |
| GET | `/mesas/{id}` | Buscar por ID |
| PUT | `/mesas/{id}` | Atualizar mesa |
| DELETE | `/mesas/{id}` | Deletar mesa |

### Produtos
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/produto` | Cadastrar produto |
| GET | `/produto` | Listar produtos |
| GET | `/produto/{id}` | Buscar por ID |
| PUT | `/produto/{id}` | Atualizar produto |
| DELETE | `/produto/{id}` | Deletar produto |

### Pedidos
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

**Criar um pedido:**
```json
POST /pedidos
{
  "mesaId": 1,
  "garcomId": 1
}
```

**Adicionar item ao pedido:**
```json
POST /pedidos/1/itens
{
  "produtoId": 1,
  "quantidade": 2,
  "observacao": "sem cebola"
}
```

**Avançar status:**
```
PATCH /pedidos/1/status?status=EM_PREPARO
```

## 🧪 Testes

```bash
./mvnw test
```

Cobertura atual: `CpfValidator` e `PedidoService` com testes unitários via JUnit 5 e Mockito.

## 📌 Próximos passos

- [ ] Autenticação com Spring Security + JWT
- [ ] Roles por perfil (ADMIN, GARCOM)
- [ ] Paginação nos endpoints de listagem
- [ ] Documentação com Swagger/OpenAPI
