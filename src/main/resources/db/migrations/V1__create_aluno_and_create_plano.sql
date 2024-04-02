CREATE TABLE aluno(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome TEXT NOT NULL,
    dataCadastro TIMESTAMP,
    cpf TEXT NOT NULL UNIQUE,
    telefone TEXT NOT NULL,
    endereco TEXT NOT NULL,
    password TEXT NOT NULL,
    role TEXT NOT NULL,
    FOREIGN KEY (ID_PLANO) references plano(idPlano)
)

CREATE TABLE plano(
    idPlano BIGINT AUTO_INCREMENT PRIMARY KEY,
    nomePlano TEXT NOT NULL,
    valor DOUBLE PRECISION NOT NULL,

)

