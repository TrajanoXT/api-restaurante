CREATE TABLE garcom (
    id     BIGSERIAL PRIMARY KEY,
    nome   VARCHAR(255) NOT NULL,
    cpf    VARCHAR(14)  NOT NULL UNIQUE,
    ativo  BOOLEAN      NOT NULL DEFAULT TRUE
);

CREATE TABLE mesa (
    id        BIGSERIAL PRIMARY KEY,
    numero    BIGINT       NOT NULL UNIQUE,
    quantidade INTEGER     NOT NULL,
    status    VARCHAR(20)  NOT NULL
);

CREATE TABLE produto (
    id         BIGSERIAL PRIMARY KEY,
    nome       VARCHAR(255)   NOT NULL,
    descricao  VARCHAR(255),
    preco      NUMERIC(10, 2) NOT NULL,
    categoria  VARCHAR(50)    NOT NULL,
    disponivel BOOLEAN        NOT NULL DEFAULT TRUE
);

CREATE TABLE pedidos (
    id          BIGSERIAL PRIMARY KEY,
    mesa_id     BIGINT         NOT NULL REFERENCES mesa(id),
    garcom_id   BIGINT         NOT NULL REFERENCES garcom(id),
    status      VARCHAR(20)    NOT NULL,
    criado_em   TIMESTAMP      NOT NULL,
    fechado_em  TIMESTAMP,
    total       NUMERIC(10, 2) NOT NULL DEFAULT 0
);

CREATE TABLE item_pedido (
    id               BIGSERIAL PRIMARY KEY,
    pedido_id        BIGINT         NOT NULL REFERENCES pedidos(id),
    produto_id       BIGINT         NOT NULL REFERENCES produto(id),
    quantidade       INTEGER        NOT NULL,
    preco_unitario   NUMERIC(10, 2) NOT NULL,
    observacao       VARCHAR(255)
);