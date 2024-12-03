CREATE TABLE users
(
    id        BIGSERIAL PRIMARY KEY,
    username  VARCHAR(50) NOT NULL,
    name      VARCHAR(50) NOT NULL,
    surname   VARCHAR(50) NOT NULL,
    birthdate DATE        NOT NULL,
    gender    VARCHAR(10),
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    version INT DEFAULT 0,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP
);

CREATE TABLE roles (
   id BIGSERIAL PRIMARY KEY,
   name VARCHAR(50) NOT NULL UNIQUE,
   description TEXT,
   version INT DEFAULT 0,
   created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   updated_date TIMESTAMP
);

CREATE TABLE user_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    version INT DEFAULT 0,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE
);

INSERT INTO roles (name, description) VALUES
      ('USER', 'Basic user role'),
      ('ADMIN', 'Administrator role'),
      ('OPERATION', 'Operation role');

