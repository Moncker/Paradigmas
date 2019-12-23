
-- tables
-- Table: Favorito
CREATE TABLE Favorito (
    coordenadaX double NOT NULL,
    coordenadaY double NOT NULL,
    CONSTRAINT Favorito_pk PRIMARY KEY (coordenadaX,coordenadaY),
    CONSTRAINT Favorito_Localizacion FOREIGN KEY (coordenadaX,coordenadaY)
    REFERENCES Localizacion (coordenadaX,coordenadaY) ON UPDATE CASCADE ON DELETE CASCADE
);

-- Table: Historial
CREATE TABLE Historial (
    id_tiempo integer NOT NULL CONSTRAINT Historial_pk PRIMARY KEY,
    coordenadaX double NOT NULL,
    coordenadaY double NOT NULL,
    CONSTRAINT Historial_Tiempo FOREIGN KEY (id_tiempo)
    REFERENCES Tiempo (id_tiempo) ON DELETE CASCADE,
    CONSTRAINT Historial_Localizacion FOREIGN KEY (coordenadaX,coordenadaY)
    REFERENCES Localizacion (coordenadaX,coordenadaY)
);

-- Table: Localizacion
CREATE TABLE Localizacion (
    nombre_ciudad varchar(40) NOT NULL,
    coordenadaX double NOT NULL,
    coordenadaY double NOT NULL,
    CONSTRAINT Localizacion_pk PRIMARY KEY (coordenadaX,coordenadaY)
);

-- Table: Tag
CREATE TABLE Tag (
    nombre_tag varchar(40) NOT NULL,
    coordenadaX double NOT NULL,
    coordenadaY double NOT NULL,
    CONSTRAINT Tag_pk PRIMARY KEY (coordenadaX,coordenadaY),
    CONSTRAINT Tag_Localizacion FOREIGN KEY (coordenadaX,coordenadaY)
    REFERENCES Localizacion (coordenadaX,coordenadaY) ON UPDATE CASCADE ON DELETE CASCADE
);

-- Table: Tiempo
CREATE TABLE Tiempo (
    id_tiempo integer PRIMARY KEY AUTOINCREMENT,
    grados double NOT NULL,
    estado varchar(25) NOT NULL,
    humedad double NOT NULL,
    fecha date NOT NULL
);



