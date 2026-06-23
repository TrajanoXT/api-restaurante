CREATE TABLE tb_users (
                          id         BIGSERIAL PRIMARY KEY,
                          nome       VARCHAR(255) NOT NULL,
                          email      VARCHAR(255) NOT NULL UNIQUE,
                          password   VARCHAR(255) NOT NULL,
                          role       VARCHAR(20)  NOT NULL,
                          active     BOOLEAN      NOT NULL DEFAULT TRUE,
                          created_at TIMESTAMP,
                          updated_at TIMESTAMP
);