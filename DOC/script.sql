DROP SCHEMA IF EXISTS dds2017;

CREATE SCHEMA dds2017;
USE dds2017;

DROP TABLE IF EXISTS cuenta;
DROP TABLE IF EXISTS condicion;
DROP TABLE IF EXISTS indicador;
DROP TABLE IF EXISTS empresa;
DROP TABLE IF EXISTS metodologia;


CREATE TABLE metodologia (
	id_metodologia 			int AUTO_INCREMENT,
    nombre 					varchar(50),
    PRIMARY KEY(id_metodologia)
);

CREATE TABLE empresa (
	id_empresa	   int AUTO_INCREMENT,
	nombre    	   varchar(50),
	anio_fundacion date,
	id_metodologia int,
    PRIMARY KEY (id_empresa),
    FOREIGN KEY (id_metodologia) REFERENCES metodologia(id_metodologia)
);

CREATE TABLE indicador (
	id_indicador int AUTO_INCREMENT,
	nombre       varchar(50),
	formula      varchar(50),
    id_empresa   int,
    PRIMARY KEY (id_indicador),
    FOREIGN KEY (id_empresa) REFERENCES empresa(id_empresa)
);

CREATE TABLE condicion (
	id_condicion int AUTO_INCREMENT,
	nombre       varchar(50),
	id_metodologia int, 
    id_indicador int,
    anio date,
    tipo_condicion varchar(50),
    PRIMARY KEY (id_condicion),
    FOREIGN KEY(id_metodologia) REFERENCES metodologia(id_metodologia),
	FOREIGN KEY(id_indicador) REFERENCES indicador(id_indicador)
);

CREATE TABLE cuenta (
	id_cuenta   int AUTO_INCREMENT ,
	nombre      varchar(50),
	valor       decimal(8,2),
	fecha_desde date,
	fecha_hasta date,
    id_empresa  int,
    PRIMARY KEY (id_cuenta),
	FOREIGN KEY(id_empresa) REFERENCES empresa(id_empresa)
);


