
-- tables
-- Table: Favorito
CREATE TABLE Favorito (
    coordenadaX float NOT NULL,
    coordenadaY float NOT NULL,
    CONSTRAINT Favorito_pk PRIMARY KEY (coordenadaX,coordenadaY),
    CONSTRAINT Favorito_Localizacion FOREIGN KEY (coordenadaX,coordenadaY)
    REFERENCES Localizacion (coordenadaX,coordenadaY) ON UPDATE CASCADE ON DELETE CASCADE
);

-- Table: Historial
CREATE TABLE Historial (
    id_tiempo integer NOT NULL CONSTRAINT Historial_pk PRIMARY KEY,
    coordenadaX float NOT NULL,
    coordenadaY float NOT NULL,
    CONSTRAINT Historial_Tiempo FOREIGN KEY (id_tiempo)
    REFERENCES Tiempo (id_tiempo) ON DELETE CASCADE,
    CONSTRAINT Historial_Localizacion FOREIGN KEY (coordenadaX,coordenadaY)
    REFERENCES Localizacion (coordenadaX,coordenadaY)
);

-- Table: Localizacion
CREATE TABLE Localizacion (
    nombre_ciudad varchar(40) NOT NULL,
    coordenadaX float NOT NULL,
    coordenadaY float NOT NULL,
    CONSTRAINT Localizacion_pk PRIMARY KEY (coordenadaX,coordenadaY)
);

-- Table: Tag
CREATE TABLE Tag (
    nombre_tag varchar(40) NOT NULL,
    coordenadaX float NOT NULL,
    coordenadaY float NOT NULL,
    CONSTRAINT Tag_pk PRIMARY KEY (coordenadaX,coordenadaY, nombre_tag),
    CONSTRAINT Tag_Localizacion FOREIGN KEY (coordenadaX,coordenadaY)
    REFERENCES Localizacion (coordenadaX,coordenadaY) ON UPDATE CASCADE ON DELETE CASCADE
);

-- Table: Tiempo
CREATE TABLE Tiempo (
    id_tiempo integer PRIMARY KEY AUTOINCREMENT,
    grados double NOT NULL,
    estado varchar(25) NOT NULL,
    humedad double NOT NULL,
    fecha date NOT NULL,
    fecha_consulta date NOT NULL
);

-- Table: HistorialCoordenada
CREATE TABLE HistorialCoordenada(
    id_tiempo integer NOT NULL CONSTRAINT Historial_Coordenada_pk PRIMARY KEY,
    coordenadaX float NOT NULL,
    coordenadaY float NOT NULL,
    CONSTRAINT Historial_Coordenada_Tiempo_fk FOREIGN KEY (id_tiempo)
    REFERENCES Tiempo (id_tiempo) ON DELETE CASCADE
);

-- Table: tagCoordenada
CREATE TABLE TagCoordenada(
    coordenadaX float NOT NULL,
    coordenadaY float NOT NULL,
    nombre_tag varchar(40) NOT NULL,
    CONSTRAINT TagCoordenada_pk PRIMARY KEY (coordenadaX,coordenadaY, nombre_tag)
);



