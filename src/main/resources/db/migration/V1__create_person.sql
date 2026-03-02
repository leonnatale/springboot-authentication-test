CREATE TABLE person (
    id bigint AUTO_INCREMENT NOT NULL,
    name varchar(255) NOT NULL,
    birthdate date NOT NULL,
    role varchar(16) NOT NULL,
    PRIMARY KEY (id)
)