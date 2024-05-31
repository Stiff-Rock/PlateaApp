DROP TABLE votar;
DROP TABLE gestionar;
DROP TABLE denuncia;
DROP TABLE configuracion;
DROP TABLE categoria;
DROP TABLE usuario;
DROP TABLE pregunta;

CREATE TABLE categoria (
    codigo VARCHAR2(20 CHAR) NOT NULL,
    nombre VARCHAR2(20 CHAR)
);

ALTER TABLE categoria ADD CONSTRAINT categoria_pk PRIMARY KEY (codigo);

CREATE TABLE configuracion (
    clave VARCHAR2(50 CHAR) NOT NULL,
    valor VARCHAR2(30 CHAR)
);

ALTER TABLE configuracion ADD CONSTRAINT configuracion_pk PRIMARY KEY (clave);

CREATE TABLE denuncia (
    codigo VARCHAR2(20 CHAR) NOT NULL,
    imagen BLOB,
    direccion VARCHAR2(50 CHAR),
    cp INTEGER,
    estado CHAR(20),
    fecha DATE,
    usuario_nick VARCHAR2(20 CHAR),
    categoria_codigo VARCHAR2(20 CHAR) NOT NULL,
    descripcion VARCHAR(300 CHAR)
);

ALTER TABLE denuncia ADD CONSTRAINT denuncia_pk PRIMARY KEY (codigo);

CREATE TABLE gestionar (
    usuario_nick VARCHAR2(20 CHAR) NOT NULL,
    denuncia_codigo VARCHAR2(20 CHAR) NOT NULL,
    gestion VARCHAR2(20 CHAR),
    fecha DATE
);

ALTER TABLE gestionar ADD CONSTRAINT gestionar_pk PRIMARY KEY (usuario_nick, denuncia_codigo);

CREATE TABLE pregunta (
    codigo VARCHAR2(20 CHAR) NOT NULL,
    cuestion VARCHAR2(60 CHAR)
);

ALTER TABLE pregunta ADD CONSTRAINT pregunta_pk PRIMARY KEY (codigo);

CREATE TABLE usuario (
    nick VARCHAR2(20 CHAR) NOT NULL,
    apellido VARCHAR2(20 CHAR),
    nombre VARCHAR2(20 CHAR),
    cp INTEGER,
    pwd VARCHAR2(60 CHAR),
    admin CHAR(1) NOT NULL,
    foto BLOB,
    pregunta_codigo VARCHAR2(20 CHAR) NOT NULL,
    respuesta VARCHAR(200 CHAR) NOT NULL
);

ALTER TABLE usuario ADD CONSTRAINT usuario_pk PRIMARY KEY (nick);

CREATE TABLE votar (
    usuario_nick VARCHAR2(20 CHAR) NOT NULL,
    denuncia_codigo VARCHAR2(20 CHAR) NOT NULL,
    upvote CHAR(1),
    favorito CHAR(1)
);

ALTER TABLE votar ADD CONSTRAINT votar_pk PRIMARY KEY (usuario_nick, denuncia_codigo);

ALTER TABLE denuncia
    ADD CONSTRAINT denuncia_categoria_fk FOREIGN KEY (categoria_codigo)
        REFERENCES categoria (codigo);

ALTER TABLE denuncia
    ADD CONSTRAINT denuncia_usuario_fk FOREIGN KEY (usuario_nick)
        REFERENCES usuario (nick);

ALTER TABLE gestionar
    ADD CONSTRAINT gestionar_denuncia_fk FOREIGN KEY (denuncia_codigo)
        REFERENCES denuncia (codigo);

ALTER TABLE gestionar
    ADD CONSTRAINT gestionar_usuario_fk FOREIGN KEY (usuario_nick)
        REFERENCES usuario (nick);

ALTER TABLE usuario
    ADD CONSTRAINT usuario_pregunta_fk FOREIGN KEY (pregunta_codigo)
        REFERENCES pregunta (codigo);

ALTER TABLE votar
    ADD CONSTRAINT votar_denuncia_fk FOREIGN KEY (denuncia_codigo)
        REFERENCES denuncia (codigo);

ALTER TABLE votar
    ADD CONSTRAINT votar_usuario_fk FOREIGN KEY (usuario_nick)
        REFERENCES usuario (nick);



INSERT INTO pregunta (codigo, cuestion) VALUES ('PRE001', '¿Cuál es el nombre del colegio de tu infancia?');

INSERT INTO pregunta (codigo, cuestion) VALUES ('PRE002', '¿Cuál es el nombre de tu primera mascota?');

INSERT INTO pregunta (codigo, cuestion) VALUES ('PRE003', '¿Cuál es la ciudad en la que se conocieron tus padres?');

INSERT INTO pregunta (codigo, cuestion) VALUES ('PRE004', '¿Cuál fue tu primera videoconsola?');

INSERT INTO pregunta (codigo, cuestion) VALUES ('PRE005', '¿Cuál es el año de nacimiento de tu abuelo paterno?');



INSERT INTO usuario (nick, apellido, nombre, cp, pwd, admin, foto, pregunta_codigo, respuesta)
VALUES ('carlangas', 'Perez', 'Carlos', 28672, 'caballobonito123', 'N', EMPTY_BLOB(), 'PRE001', 'IES Arganda');

INSERT INTO usuario (nick, apellido, nombre, cp, pwd, admin, foto, pregunta_codigo, respuesta)
VALUES ('Antontito', 'Luo', 'Algo', 289211, 'jose123', 'S', EMPTY_BLOB(), 'PRE002', 'Pepe');

INSERT INTO usuario (nick, apellido, nombre, cp, pwd, admin, foto, pregunta_codigo, respuesta)
VALUES ('Yaggiotor', 'Pernas', 'AlgoMas', 21311, 'CONTRASENA', 'N', EMPTY_BLOB(), 'PRE003', 'El Cairo');

INSERT INTO usuario (nick, apellido, nombre, cp, pwd, admin, foto, pregunta_codigo, respuesta)
VALUES ('cHion', 'Osma', 'Hugo', 289211, '12345678ab', 'S', EMPTY_BLOB(), 'PRE001', 'Hermanos Garcia Noblejas');

INSERT INTO usuario (nick, apellido, nombre, cp, pwd, admin, foto, pregunta_codigo, respuesta)
VALUES ('pedrito', 'Camacho', 'Pedro', 282411, 'programador123', 'S', EMPTY_BLOB(), 'PRE002', 'Picachu');

INSERT INTO usuario (nick, apellido, nombre, cp, pwd, admin, foto, pregunta_codigo, respuesta)
VALUES ('HugoRojo', 'Hugo', 'Rojo', 28942, 'overbuuquing', 'N', EMPTY_BLOB(), 'PRE003', 'Groningen');

INSERT INTO usuario (nick, apellido, nombre, cp, pwd, admin, foto, pregunta_codigo, respuesta)
VALUES ('juancho', 'Juan', 'Panza', 252512, 'juanchopanza3132', 'S', EMPTY_BLOB(), 'PRE002', 'Mody');

INSERT INTO usuario (nick, apellido, nombre, cp, pwd, admin, foto, pregunta_codigo, respuesta)
VALUES ('Pichon', 'Javier', 'Campos', 24917, 'aeiou112233', 'S', EMPTY_BLOB(), 'PRE004', 'PS4');

INSERT INTO usuario (nick, apellido, nombre, cp, pwd, admin, foto, pregunta_codigo, respuesta)
VALUES ('Marieleta', 'Mariela', 'Sancho', 26944, 'aguila3!', 'N', EMPTY_BLOB(), 'PRE005', '20 de agosto 1964');

INSERT INTO usuario (nick, apellido, nombre, cp, pwd, admin, foto, pregunta_codigo, respuesta)
VALUES ('antontito', 'luo', 'anton', 289211, 'jose123', 'S', EMPTY_BLOB(), 'PRE002', 'Pepe');

INSERT INTO usuario (NICK, APELLIDO, NOMBRE, CP, PWD, ADMIN, FOTO, PREGUNTA_CODIGO, RESPUESTA)
VALUES ('yaggo', 'pernas', 'yago', '28670', '123', 'S', EMPTY_BLOB(), 'PRE001', 'hnos');

INSERT INTO usuario (NICK, APELLIDO, NOMBRE, CP, PWD, ADMIN, FOTO, PREGUNTA_CODIGO, RESPUESTA)
VALUES ('yaguitor', 'pernas', 'yago', '24917', '123123', 'N', EMPTY_BLOB(), 'PRE004', 'PS4');



INSERT INTO categoria (codigo, nombre) VALUES ('CAT001', 'Alumbrado');

INSERT INTO categoria (codigo, nombre) VALUES ('CAT002', 'Bache');

INSERT INTO categoria (codigo, nombre) VALUES ('CAT003', 'Vandalismo');

INSERT INTO categoria (codigo, nombre) VALUES ('CAT004', 'Basura');

INSERT INTO categoria (codigo, nombre) VALUES ('CAT005', 'Desgaste');

INSERT INTO categoria (codigo, nombre) VALUES ('CAT006', 'Arbolado');

INSERT INTO categoria (codigo, nombre) VALUES ('CAT007', 'Infraestructura vial');

INSERT INTO categoria (codigo, nombre) VALUES ('CAT008', 'Emergencia ambiental');



INSERT INTO configuracion (clave, valor) VALUES ('Esquema de colores', 'Oscuro');

INSERT INTO configuracion (clave, valor) VALUES ('Calidad de imajen', '480px');

INSERT INTO configuracion (clave, valor) VALUES ('Idioma', 'español');

INSERT INTO configuracion (clave, valor) VALUES ('Abrir aplicacion al inicio del sitema operativo', 'No');

INSERT INTO configuracion (clave, valor) VALUES ('Mantener en segundo plano al cerrar', 'No');

INSERT INTO configuracion (clave, valor) VALUES ('Mantener sesión iniciada', 'Si');

INSERT INTO configuracion (clave, valor) VALUES ('Codigo Admin', '6HI29');



INSERT INTO denuncia (codigo, imagen, direccion, cp, estado, fecha, usuario_nick, categoria_codigo, descripcion)
VALUES ('DEN001', EMPTY_BLOB(), 'Avenida Principe de Asturias 124', 28001, 'Nueva', TO_DATE('2023-12-04', 'YYYY-MM-DD'), 'carlangas', 'CAT001','Árbol caído bloqueando la calle Principal.');

INSERT INTO denuncia (codigo, imagen, direccion, cp, estado, fecha, usuario_nick, categoria_codigo, descripcion)
VALUES ('DEN002', EMPTY_BLOB(), 'Calle San Idelfonso 45', 28002, 'Finalizada', TO_DATE('2024-02-23', 'YYYY-MM-DD'), 'Antontito', 'CAT002','Hoyo grande en la avenida Central.');

INSERT INTO denuncia (codigo, imagen, direccion, cp, estado, fecha, usuario_nick, categoria_codigo, descripcion)
VALUES ('DEN003', EMPTY_BLOB(), 'Calle Alejandro Malaspina 4', 28003, 'En proceso', TO_DATE('2024-01-18', 'YYYY-MM-DD'), 'Yaggiotor', 'CAT003','Farola caída en la esquina de la calle 5');

INSERT INTO denuncia (codigo, imagen, direccion, cp, estado, fecha, usuario_nick, categoria_codigo, descripcion)
VALUES ('DEN004', EMPTY_BLOB(), 'Calle Geronimo 7', 28004, 'Finalizada', TO_DATE('2024-01-07', 'YYYY-MM-DD'), 'HugoRojo', 'CAT002','Muro de la escuela primaria grafiteado con pintas ofensivas.');

INSERT INTO denuncia (codigo, imagen, direccion, cp, estado, fecha, usuario_nick, categoria_codigo, descripcion)
VALUES ('DEN005', EMPTY_BLOB(), 'Calle San Alfonso 9', 28005, 'Nueva', TO_DATE('2024-03-11', 'YYYY-MM-DD'), 'Antontito', 'CAT003', 'Árbol caído en el parque de la colonia Vista Hermosa.');

INSERT INTO denuncia (codigo, imagen, direccion, cp, estado, fecha, usuario_nick, categoria_codigo, descripcion)
VALUES ('DEN006', EMPTY_BLOB(), 'Calle Malasaña, 32', 28006, 'Finalizada', TO_DATE('2024-03-11', 'YYYY-MM-DD'), 'carlangas', 'CAT003','Farola caída en la esquina de la calle 5.');

INSERT INTO denuncia (codigo, imagen, direccion, cp, estado, fecha, usuario_nick, categoria_codigo, descripcion)
VALUES ('DEN007', EMPTY_BLOB(), 'Calle de la Luna', '28001', 'Nueva', TO_DATE('2024-05-15', 'YYYY-MM-DD'), 'yaggo', 'CAT001', 'Un árbol ha caído sobre la acera bloqueando el paso peatonal');

INSERT INTO denuncia (codigo, imagen, direccion, cp, estado, fecha, usuario_nick, categoria_codigo, descripcion)
VALUES ('DEN008', EMPTY_BLOB(), 'Avenida del Sol', '08001', 'En proceso', TO_DATE('2024-05-18', 'YYYY-MM-DD'), 'yaggo', 'CAT001', 'Se ha detectado una fuga de agua en una tubería subterránea, se está trabajando en su reparación');

INSERT INTO denuncia (codigo, imagen, direccion, cp, estado, fecha, usuario_nick, categoria_codigo, descripcion)
VALUES ('DEN009', EMPTY_BLOB(), 'Calle de la Palmera', '46001', 'Finalizada', TO_DATE('2024-05-20', 'YYYY-MM-DD'), 'yaggo', 'CAT001', 'Se ha producido un choque entre dos vehículos en una intersección, ya se han despejado los vehículos involucrados y se ha restablecido el tráfico');

INSERT INTO denuncia (codigo, imagen, direccion, cp, estado, fecha, usuario_nick, categoria_codigo, descripcion)
VALUES ('DEN010', EMPTY_BLOB(), 'Avenida de los Pinos', '41001', 'Nueva', TO_DATE('2024-05-23', 'YYYY-MM-DD'), 'Pichon', 'CAT001', 'Se ha producido un corte de energía en varios edificios del centro de la ciudad, se está investigando la causa');

INSERT INTO denuncia (codigo, imagen, direccion, cp, estado, fecha, usuario_nick, categoria_codigo, descripcion)
VALUES ('DEN011', EMPTY_BLOB(), 'Calle del Mar', '48001', 'En proceso', TO_DATE('2024-05-28', 'YYYY-MM-DD'), 'yaggo', 'CAT001', 'Las intensas lluvias han causado inundaciones en varias calles del barrio, equipos de rescate están ayudando a los residentes afectados');

INSERT INTO denuncia (codigo, imagen, direccion, cp, estado, fecha, usuario_nick, categoria_codigo, descripcion)
VALUES ('DEN012', EMPTY_BLOB(), 'Avenida de las Flores', '18001', 'Finalizada', TO_DATE('2024-06-02', 'YYYY-MM-DD'), 'juancho', 'CAT001', 'Un deslizamiento de tierra ha bloqueado una carretera rural en las afueras de la ciudad, se está evaluando la situación para iniciar labores de limpieza');

INSERT INTO denuncia (codigo, imagen, direccion, cp, estado, fecha, usuario_nick, categoria_codigo, descripcion)
VALUES ('DEN013', EMPTY_BLOB(), 'Calle del Fuego', '29001', 'En proceso', TO_DATE('2024-06-05', 'YYYY-MM-DD'), 'Marieleta', 'CAT001', 'Se ha declarado un incendio forestal en las montañas cercanas a la ciudad, los bomberos están combatiendo las llamas y evacuando áreas cercanas');

INSERT INTO denuncia (codigo, imagen, direccion, cp, estado, fecha, usuario_nick, categoria_codigo, descripcion)
VALUES ('DEN014', EMPTY_BLOB(), 'Avenida de los Naranjos', '50001', 'Finalizada', TO_DATE('2024-06-08', 'YYYY-MM-DD'), 'yaggo', 'CAT001', 'Una avería en la red de transporte público ha causado la interrupción del servicio de autobuses en varias rutas principales, la situación ha sido solventada y el servicio ha sido restablecido');

INSERT INTO denuncia (codigo, imagen, direccion, cp, estado, fecha, usuario_nick, categoria_codigo, descripcion)
VALUES ('DEN015', EMPTY_BLOB(), 'Calle del agua', '29001', 'Nueva', TO_DATE('2022-06-05', 'YYYY-MM-DD'), 'Marieleta', 'CAT001', 'Se ha declarado un incendio forestal en las montañas cercanas a la ciudad, los bomberos están combatiendo las llamas y evacuando áreas cercanas');

INSERT INTO denuncia (codigo, imagen, direccion, cp, estado, fecha, usuario_nick, categoria_codigo, descripcion)
VALUES ('DEN016', EMPTY_BLOB(), 'Avenida de los Manzanos', '50001', 'Nueva', TO_DATE('2021-06-08', 'YYYY-MM-DD'), 'yaggo', 'CAT001', 'Una avería en la red de transporte público ha causado la interrupción del servicio de autobuses en varias rutas principales, la situación ha sido solventada y el servicio ha sido restablecido');

INSERT INTO denuncia (codigo, imagen, direccion, cp, estado, fecha, usuario_nick, categoria_codigo, descripcion)
VALUES ('DEN017', EMPTY_BLOB(), 'Calle Arroyo Fresco 28', 28017, 'Nueva', TO_DATE('2024-03-15', 'YYYY-MM-DD'), 'Antontito', 'CAT004', 'Árbol caído en el parque de la colonia Vista Hermosa.');

INSERT INTO denuncia (codigo, imagen, direccion, cp, estado, fecha, usuario_nick, categoria_codigo, descripcion)
VALUES ('DEN018', EMPTY_BLOB(), 'Calle Reina Victoria 55', 28018, 'Finalizada', TO_DATE('2024-03-18', 'YYYY-MM-DD'), 'HugoRojo', 'CAT002', 'Muro de la escuela primaria grafiteado con pintas ofensivas.');

INSERT INTO denuncia (codigo, imagen, direccion, cp, estado, fecha, usuario_nick, categoria_codigo, descripcion)
VALUES ('DEN019', EMPTY_BLOB(), 'Calle de las Flores 12', 28019, 'Nueva', TO_DATE('2024-03-21', 'YYYY-MM-DD'), 'Antontito', 'CAT003', 'Árbol ardiendo violentamente que ha provocado un incendio forestal..');

INSERT INTO denuncia (codigo, imagen, direccion, cp, estado, fecha, usuario_nick, categoria_codigo, descripcion)
VALUES ('DEN020', EMPTY_BLOB(), 'Calle Sol 10', 28020, 'Finalizada', TO_DATE('2024-03-24', 'YYYY-MM-DD'), 'carlangas', 'CAT003', 'Farola caída en la esquina de la calle 5.');

INSERT INTO denuncia (codigo, imagen, direccion, cp, estado, fecha, usuario_nick, categoria_codigo, descripcion)
VALUES ('DEN021', EMPTY_BLOB(), 'Calle Robles 8', 28021, 'Nueva', TO_DATE('2024-03-27', 'YYYY-MM-DD'), 'Antontito', 'CAT004', 'Árbol caído en el parque de la colonia Vista Hermosa.');

INSERT INTO denuncia (codigo, imagen, direccion, cp, estado, fecha, usuario_nick, categoria_codigo, descripcion)
VALUES ('DEN022', EMPTY_BLOB(), 'Calle del Bosque 15', 28022, 'Finalizada', TO_DATE('2024-03-30', 'YYYY-MM-DD'), 'HugoRojo', 'CAT002', 'Muro de la escuela primaria grafiteado con pintas ofensivas.');

INSERT INTO denuncia (codigo, imagen, direccion, cp, estado, fecha, usuario_nick, categoria_codigo, descripcion)
VALUES ('DEN023', EMPTY_BLOB(), 'Calle de la Montaña 20', 28023, 'Nueva', TO_DATE('2024-04-02', 'YYYY-MM-DD'), 'Antontito', 'CAT004', 'Árbol caído en el parque de la colonia Vista Hermosa.');

INSERT INTO denuncia (codigo, imagen, direccion, cp, estado, fecha, usuario_nick, categoria_codigo, descripcion)
VALUES ('DEN024', EMPTY_BLOB(), 'Calle del Río 14', 28024, 'Finalizada', TO_DATE('2024-04-05', 'YYYY-MM-DD'), 'carlangas', 'CAT003', 'Farola caída en la esquina de la calle 5.');

INSERT INTO denuncia (codigo, imagen, direccion, cp, estado, fecha, usuario_nick, categoria_codigo, descripcion)
VALUES ('DEN025', EMPTY_BLOB(), 'Calle de la Aurora 29', 28025, 'Nueva', TO_DATE('2024-04-08', 'YYYY-MM-DD'), 'Antontito', 'CAT004', 'Árbol caído en el parque de la colonia Vista Hermosa.');

INSERT INTO denuncia (codigo, imagen, direccion, cp, estado, fecha, usuario_nick, categoria_codigo, descripcion)
VALUES ('DEN026', EMPTY_BLOB(), 'Calle Principal 18', 28026, 'Finalizada', TO_DATE('2024-04-11', 'YYYY-MM-DD'), 'HugoRojo', 'CAT002', 'Muro de la escuela primaria grafiteado con pintas ofensivas.');

    

INSERT INTO gestionar (usuario_nick, denuncia_codigo, gestion, fecha)
VALUES ('cHion', 'DEN001', 'En proceso', TO_DATE('2023-12-04', 'YYYY-MM-DD'));

INSERT INTO gestionar (usuario_nick, denuncia_codigo, gestion, fecha)
VALUES ('pedrito', 'DEN002', 'Completado', TO_DATE('2024-02-27', 'YYYY-MM-DD'));

INSERT INTO gestionar (usuario_nick, denuncia_codigo, gestion, fecha)
VALUES ('Antontito', 'DEN003', 'En proceso', TO_DATE('2023-02-22', 'YYYY-MM-DD'));

INSERT INTO gestionar (usuario_nick, denuncia_codigo, gestion, fecha)
VALUES ('juancho', 'DEN004', 'Completado', TO_DATE('2022-12-12', 'YYYY-MM-DD'));

INSERT INTO gestionar (usuario_nick, denuncia_codigo, gestion, fecha)
VALUES ('Pichon', 'DEN005', 'En proceso', TO_DATE('2023-07-18', 'YYYY-MM-DD'));



INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('yaggo', 'DEN001', 'S', 'S');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('yaggo', 'DEN002', 'S', 'S');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('yaggo', 'DEN003', 'S', 'N');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('yaguitor', 'DEN004', 'S', 'S');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('yaguitor', 'DEN005', 'N', 'S');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('yaguitor', 'DEN006', 'S', 'S');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('carlangas', 'DEN001', 'S', 'N');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('Antontito', 'DEN001', 'N', 'N');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('Yaggiotor', 'DEN001', 'N', 'S');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('pedrito', 'DEN002', 'S', 'S');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('cHion', 'DEN002', 'S', 'N');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('HugoRojo', 'DEN006', 'S', 'N');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('pedrito', 'DEN004', 'N', 'S');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('carlangas', 'DEN004', 'S', 'N');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('Antontito', 'DEN005', 'S', 'S');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('HugoRojo', 'DEN005', 'N', 'S');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('carlangas', 'DEN007', 'N', 'S');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('Antontito', 'DEN007', 'S', 'N');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('HugoRojo', 'DEN008', 'S', 'N');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('cHion', 'DEN008', 'N', 'S');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('carlangas', 'DEN009', 'S', 'N');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('Antontito', 'DEN009', 'N', 'N');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('Yaggiotor', 'DEN009', 'S', 'N');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('pedrito', 'DEN010', 'S', 'S');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('cHion', 'DEN010', 'S', 'N');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('HugoRojo', 'DEN011', 'N', 'S');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('carlangas', 'DEN011', 'S', 'N');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('Antontito', 'DEN012', 'S', 'S');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('HugoRojo', 'DEN012', 'N', 'S');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('carlangas', 'DEN013', 'N', 'S');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('Antontito', 'DEN013', 'S', 'N');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('HugoRojo', 'DEN014', 'S', 'N');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('cHion', 'DEN014', 'N', 'S');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('carlangas', 'DEN015', 'S', 'S');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('Antontito', 'DEN015', 'N', 'S');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('HugoRojo', 'DEN016', 'S', 'N');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('carlangas', 'DEN016', 'N', 'S');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('Antontito', 'DEN017', 'S', 'S');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('Yaggiotor', 'DEN017', 'N', 'S');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('pedrito', 'DEN018', 'N', 'S');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('cHion', 'DEN018', 'S', 'N');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('HugoRojo', 'DEN019', 'S', 'N');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('carlangas', 'DEN019', 'N', 'S');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('Antontito', 'DEN020', 'S', 'S');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('HugoRojo', 'DEN020', 'N', 'S');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('carlangas', 'DEN021', 'N', 'S');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('Antontito', 'DEN021', 'S', 'N');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('Yaggiotor', 'DEN022', 'S', 'S');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('pedrito', 'DEN022', 'N', 'S');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('cHion', 'DEN023', 'N', 'S');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('HugoRojo', 'DEN023', 'S', 'N');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('carlangas', 'DEN024', 'S', 'S');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('Antontito', 'DEN024', 'N', 'S');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('Yaggiotor', 'DEN025', 'S', 'S');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('pedrito', 'DEN025', 'N', 'S');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('cHion', 'DEN026', 'N', 'S');

INSERT INTO votar (usuario_nick, denuncia_codigo, upvote, favorito)
VALUES ('HugoRojo', 'DEN026', 'S', 'N');

create or replace PROCEDURE VOTACION (
    usuarioNickI IN VARCHAR2,
    denunciaCodigoI IN VARCHAR2,
    upvoteI IN CHAR,
    favoritoI IN CHAR
)
AS
    v_count INTEGER;

BEGIN

    SELECT COUNT(1)
    INTO v_count
    FROM votar
    WHERE USUARIO_NICK = usuarioNickI AND DENUNCIA_CODIGO = denunciaCodigoI;


    IF v_count > 0 THEN

        UPDATE votar
        SET UPVOTE = upvoteI
        WHERE USUARIO_NICK = usuarioNickI AND DENUNCIA_CODIGO = denunciaCodigoI;
    ELSE

        INSERT INTO votar (USUARIO_NICK, DENUNCIA_CODIGO, UPVOTE, FAVORITO)
        VALUES (usuarioNickI, denunciaCodigoI, upvoteI, favoritoI);
    END IF;
END;
/


CREATE OR REPLACE PROCEDURE VOTACION (
    usuarioNickI IN VARCHAR2,
    denunciaCodigoI IN VARCHAR2,
    upvoteI IN CHAR,
    favoritoI IN CHAR
)
AS
    v_count INTEGER;
BEGIN
    SELECT COUNT(1)
    INTO v_count
    FROM votar
    WHERE USUARIO_NICK = usuarioNickI AND DENUNCIA_CODIGO = denunciaCodigoI;

    IF v_count > 0 THEN
        UPDATE votar
        SET UPVOTE = upvoteI
        WHERE USUARIO_NICK = usuarioNickI AND DENUNCIA_CODIGO = denunciaCodigoI;
    ELSE
        INSERT INTO votar (USUARIO_NICK, DENUNCIA_CODIGO, UPVOTE, FAVORITO)
        VALUES (usuarioNickI, denunciaCodigoI, upvoteI, favoritoI);
    END IF;
END;
/


CREATE OR REPLACE PROCEDURE Favoritito(
    usuarioNickI IN VARCHAR2,
    denunciaCodigoI IN VARCHAR2,
    upvoteI IN CHAR,
    favoritoI IN CHAR
)
AS
    v_count INTEGER;
BEGIN
    SELECT COUNT(1)
    INTO v_count
    FROM votar
    WHERE USUARIO_NICK = usuarioNickI AND DENUNCIA_CODIGO = denunciaCodigoI;

    IF v_count > 0 THEN
        UPDATE votar
        SET FAVORITO = favoritoI
        WHERE USUARIO_NICK = usuarioNickI AND DENUNCIA_CODIGO = denunciaCodigoI;
    ELSE
        INSERT INTO votar (USUARIO_NICK, DENUNCIA_CODIGO, UPVOTE, FAVORITO)
        VALUES (usuarioNickI, denunciaCodigoI, upvoteI, favoritoI);
    END IF;
END;
/


CREATE OR REPLACE PROCEDURE APROBAR (P_ID_DENUNCIA IN VARCHAR2)
IS
BEGIN
    UPDATE DENUNCIA SET ESTADO = 'En proceso' WHERE CODIGO = P_ID_DENUNCIA;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
END;
/


CREATE OR REPLACE PROCEDURE BORRAR_DENUNCIA(P_ID_DENUNCIA IN VARCHAR2)
IS
BEGIN
    DELETE FROM VOTAR WHERE DENUNCIA_CODIGO = P_ID_DENUNCIA;
    DELETE FROM GESTIONAR WHERE DENUNCIA_CODIGO = P_ID_DENUNCIA;
    DELETE FROM DENUNCIA WHERE CODIGO = P_ID_DENUNCIA;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
END;
/


CREATE OR REPLACE PROCEDURE CAMBIAR_DATOS (
    P_ID_DENUNCIA IN VARCHAR2,
    P_COLUMNA IN VARCHAR2,
    P_DATO_NUEVO IN VARCHAR2
)
IS
BEGIN
    IF P_COLUMNA = 'DIRECCION' THEN
        UPDATE DENUNCIA SET DIRECCION = P_DATO_NUEVO WHERE CODIGO = P_ID_DENUNCIA;
    ELSIF P_COLUMNA = 'CATEGORIA_CODIGO' THEN
        UPDATE DENUNCIA SET CATEGORIA_CODIGO = P_DATO_NUEVO WHERE CODIGO = P_ID_DENUNCIA;
    ELSIF P_COLUMNA = 'DESCRIPCION' THEN
        UPDATE DENUNCIA SET DESCRIPCION = P_DATO_NUEVO WHERE CODIGO = P_ID_DENUNCIA;
    ELSIF P_COLUMNA = 'CP' THEN
        UPDATE DENUNCIA SET CP = P_DATO_NUEVO WHERE CODIGO = P_ID_DENUNCIA;
    ELSE
        RAISE_APPLICATION_ERROR(-20001, 'COLUMNA NO VÁLIDA');
    END IF;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
END;
/


CREATE OR REPLACE PROCEDURE QUITAR_FAVORITO (
    P_ID_DENUNCIA IN VARCHAR2,
    P_NICK IN VARCHAR2)
IS
    V_UPVOTE VOTAR.UPVOTE%TYPE;
    V_FAVORITO VOTAR.FAVORITO%TYPE;
BEGIN
    UPDATE VOTAR SET FAVORITO = 'N' WHERE DENUNCIA_CODIGO = P_ID_DENUNCIA AND USUARIO_NICK = P_NICK;
    
    SELECT UPVOTE, FAVORITO
    INTO V_UPVOTE, V_FAVORITO
    FROM VOTAR
    WHERE DENUNCIA_CODIGO = P_ID_DENUNCIA AND USUARIO_NICK = P_NICK;
    
    IF V_UPVOTE = 'N' AND V_FAVORITO = 'N' THEN
        DELETE FROM VOTAR WHERE DENUNCIA_CODIGO = P_ID_DENUNCIA AND USUARIO_NICK = P_NICK;
    END IF;
    
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
END;
/


CREATE OR REPLACE PROCEDURE QUITAR_VOTAR (
    P_ID_DENUNCIA IN VARCHAR2,
    P_NICK IN VARCHAR2)
IS
    V_UPVOTE VOTAR.UPVOTE%TYPE;
    V_FAVORITO VOTAR.FAVORITO%TYPE;
BEGIN
    UPDATE VOTAR SET UPVOTE = 'N'
    WHERE DENUNCIA_CODIGO = P_ID_DENUNCIA AND USUARIO_NICK = P_NICK;

    SELECT UPVOTE, FAVORITO
    INTO V_UPVOTE, V_FAVORITO
    FROM VOTAR
    WHERE DENUNCIA_CODIGO = P_ID_DENUNCIA AND USUARIO_NICK = P_NICK;

    IF V_UPVOTE = 'N' AND V_FAVORITO = 'N' THEN
        DELETE FROM VOTAR
        WHERE DENUNCIA_CODIGO = P_ID_DENUNCIA AND USUARIO_NICK = P_NICK;
    END IF;

    COMMIT;

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
END;
/
​