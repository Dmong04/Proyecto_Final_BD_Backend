-- Usar base de datos
USE `coco_tours_db_v3`;

-- Tabla Cliente
CREATE TABLE IF NOT EXISTS cliente
(
    id     INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS telefonos_cliente
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    cliente  INT         NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    FOREIGN KEY (cliente) REFERENCES cliente (id) ON DELETE CASCADE
);

-- Tabla Administrador
CREATE TABLE IF NOT EXISTS administrador
(
    id     INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

-- Tabla Usuario
CREATE TABLE IF NOT EXISTS usuario
(
    id  INT AUTO_INCREMENT PRIMARY KEY,
    correo     VARCHAR(70) UNIQUE NOT NULL,
    usuario    VARCHAR(30) UNIQUE NOT NULL,
    contraseña VARCHAR(150)       NOT NULL,
    cliente    INT DEFAULT NULL,
    admin      INT DEFAULT NULL,
    FOREIGN KEY (cliente) REFERENCES cliente (id) ON DELETE SET NULL,
    FOREIGN KEY (admin) REFERENCES administrador (id) ON DELETE SET NULL
);

-- Tabla Extra
CREATE TABLE IF NOT EXISTS extra
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    nombre        VARCHAR(40)    NOT NULL,
    descripcion   VARCHAR(100)   NOT NULL,
    precioPersona DECIMAL(10, 2) NOT NULL
);

-- Tabla Detalle Extra
CREATE TABLE IF NOT EXISTS detalle_extra
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    cantPersona INT NOT NULL,
    precioTotal DECIMAL(10, 2) GENERATED ALWAYS AS (cantPersona * 1.0) VIRTUAL,
    idExtra     INT NOT NULL,
    FOREIGN KEY (idExtra) REFERENCES extra (id) ON DELETE CASCADE
);

-- Tabla Proveedor
CREATE TABLE IF NOT EXISTS proveedor
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    nombre      VARCHAR(50)  NOT NULL,
    descripcion VARCHAR(100) NOT NULL,
    correo      VARCHAR(70)  NOT NULL
);

CREATE TABLE IF NOT EXISTS telefonos_proveedor
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    telefono  VARCHAR(20) NOT NULL,
    proveedor INT         NOT NULL,
    FOREIGN KEY (proveedor) REFERENCES proveedor (id) ON DELETE CASCADE
);

-- Tabla Viaje
CREATE TABLE IF NOT EXISTS viaje
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    tipo        VARCHAR(30) NOT NULL,
    descripcion TEXT        NOT NULL,
    precio       DECIMAL(10, 2) NOT NULL
);

-- Detalle Viaje
CREATE TABLE IF NOT EXISTS detalle_viaje
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    numPasajeros INT            NOT NULL,
    origen      VARCHAR(40) NOT NULL,
    destino     VARCHAR(40) NOT NULL,
    idViaje      INT            NOT NULL,
    idProveedor  INT            NOT NULL,
    FOREIGN KEY (idViaje) REFERENCES viaje (id) ON DELETE CASCADE,
    FOREIGN KEY (idProveedor) REFERENCES proveedor (id) ON DELETE CASCADE
);

-- Tabla Reserva
CREATE TABLE IF NOT EXISTS reservas
(
    id             INT AUTO_INCREMENT PRIMARY KEY,
    fecha          DATE         NOT NULL,
    hora           TIME         NOT NULL,
    descripcion    VARCHAR(100) NOT NULL,
    subtotalViaje  DECIMAL(10, 2) GENERATED ALWAYS AS (0) VIRTUAL,
    subtotalExtra  DECIMAL(10, 2) GENERATED ALWAYS AS (0) VIRTUAL,
    total          DECIMAL(10, 2) GENERATED ALWAYS AS (subtotalViaje + subtotalExtra) VIRTUAL,
    idDetalleExtra INT DEFAULT NULL,
    idDetalleViaje INT          NOT NULL,
    idUsuario      INT          NOT NULL,
    FOREIGN KEY (idDetalleExtra) REFERENCES detalle_extra (id) ON DELETE SET NULL,
    FOREIGN KEY (idDetalleViaje) REFERENCES detalle_viaje (id) ON DELETE CASCADE,
    FOREIGN KEY (idUsuario) REFERENCES usuario (id) ON DELETE CASCADE
);

-- Tabla Pasajeros
CREATE TABLE IF NOT EXISTS pasajeros
(
    id             INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre         VARCHAR(60) NOT NULL,
    edad           INT         NOT NULL,
    idDetalleViaje INT         NOT NULL,
    FOREIGN KEY (idDetalleViaje) REFERENCES detalle_viaje (id)
        ON DELETE CASCADE ON UPDATE CASCADE
);
