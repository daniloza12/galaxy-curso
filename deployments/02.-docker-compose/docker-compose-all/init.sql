CREATE DATABASE IF NOT EXISTS db_ms_clientes;
USE db_ms_clientes;
CREATE TABLE IF NOT EXISTS tbl_cliente (
    cliente_id int not null auto_increment  primary key,
    razon_social varchar(260) not null unique,
    ruc char(11) not null unique,
    direccion varchar(400) not null,
    telefono varchar(20) null,
    correo varchar(60) null,
    estado char(1) default '1' not null 
    );
       
INSERT INTO tbl_cliente(razon_social,ruc,direccion,telefono,correo)
VALUES('CLARO EMPRESAS','20544987258','AV. LARCO 237-SAN ISIDRO','456-4574','contacto@claro.com.pe'),
('BITEL EMPRESAS','20544987255','AV. LARCO 238-SAN ISIDRO','456-4575','contacto@bitel.com.pe'),
('ENTEL EMPRESAS','20544987256','AV. LARCO 239-SAN ISIDRO','456-4576','cont
acto@entel.com.pe');

CREATE DATABASE IF NOT EXISTS db_ms_productos;
USE db_ms_productos;
CREATE TABLE IF NOT EXISTS tbl_producto (
      producto_id int not null auto_increment PRIMARY KEY ,
    nombre varchar(60) not null,
    precio float NULL,
    stock int not null,
    estado char(1) DEFAULT '1'
    );
       
INSERT INTO tbl_producto (nombre,precio,stock,estado) 
VALUES ('LAPTOP  ASUS VivoBook Pro',3500,4,'1'),
('ALCOHOL ISOPROPILICO 70',7.00,15,'1'),
('LAPTOP  ASUS VivoBook Pro',4500,15,'1'),
('ALCOHOL ISOPROPILICO 90',7.00,12,'1'),
('CARGADOR LENOVO ORIGINAL Modelo 2471',8.00,10,'1'),
('LAPTOP  ASUS VivoBook Plus',3800,10,'1');
    