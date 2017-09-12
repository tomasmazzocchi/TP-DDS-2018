CREATE SCHEMA dds2017;

CREATE TABLE Metodologia (
	id_metodologia int,
    PRIMARY KEY(id_metodologia)
);

CREATE TABLE Condicion (
	id_condicion int AUTO_INCREMENT,
	nombre       varchar(50),
	id_metodologia int, 
    PRIMARY KEY (id_condicion),
    FOREIGN KEY(id_metodologia) REFERENCES Metodologia(id_metodologia)
);


CREATE TABLE Empresa (
	id_empresa	   int AUTO_INCREMENT,
	nombre    	   varchar(50),
	anio_fundacion date,
	id_metodologia int,
    PRIMARY KEY (id_empresa),
    FOREIGN KEY (id_metodologia) REFERENCES Metodologia(id_metodologia)
);

CREATE TABLE Cuenta (
	id_cuenta   int AUTO_INCREMENT ,
	nombre      varchar(50),
	valor       decimal(8,2),
	fecha_desde date,
	fecha_hasta date,
    id_empresa  int,
    PRIMARY KEY (id_cuenta),
	FOREIGN KEY(id_empresa) REFERENCES Empresa(id_empresa)
);


CREATE TABLE Indicador (
	id_indicador int AUTO_INCREMENT,
	nombre       varchar(50),
	formula      varchar(50),
    id_empresa   int,
    PRIMARY KEY (id_indicador),
    FOREIGN KEY (id_empresa) REFERENCES Empresa(id_empresa)
);