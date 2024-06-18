CREATE TABLE `aluno` (
     `id_aluno` bigint NOT NULL AUTO_INCREMENT,
     `cpf` varchar(255) DEFAULT NULL,
     `data_cadastro` datetime(6) DEFAULT NULL,
     `endereco` varchar(255) DEFAULT NULL,
     `nome` varchar(255) DEFAULT NULL,
     `password` varchar(255) DEFAULT NULL,
     `role` tinyint DEFAULT NULL,
     `telefone` varchar(255) DEFAULT NULL,
     `id_plano` bigint DEFAULT NULL,
     PRIMARY KEY (`id_aluno`),
     UNIQUE KEY `UK_crrvmtky7d9tfarahi4jahewg` (`cpf`)

);