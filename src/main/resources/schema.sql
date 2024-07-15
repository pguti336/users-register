CREATE TABLE usuarios
(
    id         UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    email      VARCHAR(256) NOT NULL,
    password   VARCHAR(256) NOT NULL,
    created    DATETIME,
    modified   DATETIME,
    last_login DATETIME,
    active     BOOLEAN      NOT NULL
);

CREATE TABLE telefonos
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    number       VARCHAR(9),
    country_code VARCHAR(3),
    city_code    VARCHAR(3),
    user_id      UUID,
    CONSTRAINT fk_usuario
        FOREIGN KEY (user_id)
            REFERENCES usuarios (id)
            ON DELETE CASCADE
);