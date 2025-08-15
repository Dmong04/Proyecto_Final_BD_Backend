USE coco_tours_db;

-- Eliminar primero las tablas dependientes (hijas)
DROP TABLE IF EXISTS passengers;
DROP TABLE IF EXISTS reservations;
DROP TABLE IF EXISTS client_phones;
DROP TABLE IF EXISTS supplier_phones;
DROP TABLE IF EXISTS extra_detail;
DROP TABLE IF EXISTS tour_detail;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS administrator;
DROP TABLE IF EXISTS client;
DROP TABLE IF EXISTS extra;
DROP TABLE IF EXISTS tour;
DROP TABLE IF EXISTS supplier;