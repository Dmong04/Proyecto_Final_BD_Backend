-- Desactivar restricciones temporales
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE,
    SQL_MODE='STRICT_TRANS_TABLES,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';

-- Usar base de datos
USE `coco_tours_db_v3`;

-- Tabla Cliente
CREATE TABLE IF NOT EXISTS Cliente (
    idCliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS TelefonosCliente (
    idTelefonoCliente INT AUTO_INCREMENT PRIMARY KEY,
    idCliente INT NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente) ON DELETE CASCADE
);

-- Tabla Administrador
CREATE TABLE IF NOT EXISTS Administrador (
    idAdministrador INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

-- Tabla Usuario
CREATE TABLE IF NOT EXISTS Usuario (
    idUsuario INT AUTO_INCREMENT PRIMARY KEY,       
    correo VARCHAR(70) UNIQUE NOT NULL,
    usuario VARCHAR(30) UNIQUE NOT NULL,
    contraseña VARCHAR(150) NOT NULL,
    idCliente INT DEFAULT NULL,
    idAdministrador INT DEFAULT NULL,
    FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente) ON DELETE SET NULL,
    FOREIGN KEY (idAdministrador) REFERENCES Administrador(idAdministrador) ON DELETE SET NULL
);

-- Tabla Extra
CREATE TABLE IF NOT EXISTS Extra (
    idExtra INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(40) NOT NULL,
    descripcion VARCHAR(100) NOT NULL,
    precioPersona DECIMAL(10,2) NOT NULL
);

-- Tabla Detalle Extra
CREATE TABLE IF NOT EXISTS DetalleExtra (
    idDetalleExtra INT AUTO_INCREMENT PRIMARY KEY,
    cantPersona INT NOT NULL,
    precioTotal DECIMAL(10,2) GENERATED ALWAYS AS (cantPersona * 1.0) VIRTUAL, 
    idExtra INT NOT NULL,
    FOREIGN KEY (idExtra) REFERENCES Extra(idExtra) ON DELETE CASCADE
);

-- Tabla Proveedor
CREATE TABLE IF NOT EXISTS Proveedor (
    idProveedor INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(100) NOT NULL,
    correo VARCHAR(70) NOT NULL
);

CREATE TABLE IF NOT EXISTS TelefonosProveedor (
    idTelefonoProveedor INT AUTO_INCREMENT PRIMARY KEY,
    telefono VARCHAR(20) NOT NULL,
    idProveedor INT NOT NULL,
    FOREIGN KEY (idProveedor) REFERENCES Proveedor(idProveedor) ON DELETE CASCADE
);

-- Tabla Viaje
CREATE TABLE IF NOT EXISTS Viaje (
    idViaje INT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(30) NOT NULL,
    descripcion VARCHAR(100) NOT NULL,
    origen VARCHAR(40) NOT NULL,
    destino VARCHAR(40) NOT NULL
);

-- Detalle Viaje
CREATE TABLE IF NOT EXISTS DetalleViaje (
    idDetalleViaje INT AUTO_INCREMENT PRIMARY KEY,
    numPasajeros INT NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    idViaje INT NOT NULL,
    idProveedor INT NOT NULL,
    FOREIGN KEY (idViaje) REFERENCES Viaje(idViaje) ON DELETE CASCADE,
    FOREIGN KEY (idProveedor) REFERENCES Proveedor(idProveedor) ON DELETE CASCADE
);

-- Tabla Reserva
CREATE TABLE IF NOT EXISTS Reserva (
    idReserva INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    descripcion VARCHAR(100) NOT NULL,
    subtotalViaje DECIMAL(10,2) GENERATED ALWAYS AS (0) VIRTUAL,
    subtotalExtra DECIMAL(10,2) GENERATED ALWAYS AS (0) VIRTUAL,
    total DECIMAL(10,2) GENERATED ALWAYS AS (subtotalViaje + subtotalExtra) VIRTUAL,
    idDetalleExtra INT DEFAULT NULL,
    idDetalleViaje INT NOT NULL,
    idUsuario INT NOT NULL,
    FOREIGN KEY (idDetalleExtra) REFERENCES DetalleExtra(idDetalleExtra) ON DELETE SET NULL,
    FOREIGN KEY (idDetalleViaje) REFERENCES DetalleViaje(idDetalleViaje) ON DELETE CASCADE,
    FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario) ON DELETE CASCADE
);

-- Tabla Pasajeros
CREATE TABLE IF NOT EXISTS pasajeros (
    idPasajero INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(60) NOT NULL,
    edad INT NOT NULL,
    idDetalleViaje INT NOT NULL,
    PRIMARY KEY (idPasajero),
    FOREIGN KEY (idDetalleViaje) REFERENCES DetalleViaje(idDetalleViaje)
        ON DELETE CASCADE ON UPDATE CASCADE
);

-- Restaurar restricciones
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;