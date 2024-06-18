CREATE TABLE `plano` (
     `codigo_plano` bigint NOT NULL AUTO_INCREMENT,
     `nome_plano` varchar(255) DEFAULT NULL,
     `valor` double NOT NULL,
     PRIMARY KEY (`codigo_plano`)
);