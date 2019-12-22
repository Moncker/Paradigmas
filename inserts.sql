-- inserts tiempos

insert into Tiempo (grados, estado, humedad, Fecha) values(15, 'Nublado', 50, '2019-08-23');
insert into Tiempo (grados, estado, humedad, Fecha) values(12, 'Nublado', 60, '2019-12-14');
insert into Tiempo (grados, estado, humedad, Fecha) values(11, 'Nublado', 70, '2019-12-11');
insert into Tiempo (grados, estado, humedad, Fecha) values(21, 'Soleado', 35, '2019-12-10');
insert into Tiempo (grados, estado, humedad, Fecha) values(25, 'Soleado', 20, '2019-11-02');
insert into Tiempo (grados, estado, humedad, Fecha) values(23, 'Soleado', 20, '2019-12-15');
insert into Tiempo (grados, estado, humedad, Fecha) values(34, 'Soleado', 15, '2019-05-21');
insert into Tiempo (grados, estado, humedad, Fecha) values(27, 'Soleado', 24, '2019-06-26');
insert into Tiempo (grados, estado, humedad, Fecha) values(20, 'Soleado', 28, '2019-09-19');
insert into Tiempo (grados, estado, humedad, Fecha) values(10, 'Nublado', 72, '2019-11-04');
insert into Tiempo (grados, estado, humedad, Fecha) values(5, 'Lloviendo', 85, '2019-12-11');
insert into Tiempo (grados, estado, humedad, Fecha) values(2, 'Lloviendo', 92, '2019-12-13');
insert into Tiempo (grados, estado, humedad, Fecha) values(16, 'Nublado', 55, '2019-10-01');

-- inserts localizacion

insert into Localizacion (nombre_ciudad, coordenadaX, coordenadaY) values ('Castellon', 0.25, 0.65);
insert into Localizacion (nombre_ciudad, coordenadaX, coordenadaY) values ('Villareal', 0.45, 0.85);
insert into Localizacion (nombre_ciudad, coordenadaX, coordenadaY) values ('Berlin', 14.25, 5.45);
insert into Localizacion (nombre_ciudad, coordenadaX, coordenadaY) values ('Beijing', 56.45, 89.25);
insert into Localizacion (nombre_ciudad, coordenadaX, coordenadaY) values ('Madrid', 2.97, 1.32);

-- inserts historial

insert into Historial (id_tiempo, coordenadaX, coordenadaY) values (1, 0.25, 0.65);
insert into Historial (id_tiempo, coordenadaX, coordenadaY) values (3, 0.45, 0.85);
insert into Historial (id_tiempo, coordenadaX, coordenadaY) values (6, 56.45, 89.25);
insert into Historial (id_tiempo, coordenadaX, coordenadaY) values (10, 0.45, 0.85);

-- inserts tags

insert into Tag (nombre_tag, coordenadaX, coordenadaY) values('cs', 0.25, 0.65);
insert into Tag (nombre_tag, coordenadaX, coordenadaY) values('madriz', 2.97, 1.32);

-- inserts favoritos

insert into Favorito (coordenadaX, coordenadaY) values(0.25, 0.65);
insert into Favorito (coordenadaX, coordenadaY) values( 0.45, 0.85);