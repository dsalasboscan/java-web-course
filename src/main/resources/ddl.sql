CREATE DATABASE IF NOT EXISTS dbo;

USE dbo;

CREATE TABLE IF NOT EXISTS clientes (
  id int(11) NOT NULL AUTO_INCREMENT,
  nombre varchar(45) DEFAULT NULL,
  apellido varchar(45) DEFAULT NULL,
  email varchar(45) DEFAULT NULL,
  fecha_creacion varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
