DROP DATABASE IF EXISTS chess;
CREATE DATABASE chess;
USE chess;

CREATE TABLE IF NOT EXISTS chess_board
(
    id_chess_board INT PRIMARY KEY NOT NULL AUTO_INCREMENT
);

CREATE TABLE IF NOT EXISTS history_move
(
    id_history_move INT PRIMARY KEY NOT NULL AUTO_INCREMENT
);

CREATE TABLE IF NOT EXISTS game_controller
(
    id_game_controller INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_chess_board integer REFERENCES chess_board,
    id_history_move integer REFERENCES history_move
);

CREATE TABLE IF NOT EXISTS roles
(
    id_roles INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    role VARCHAR(10) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS users
(
    id_users INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    login VARCHAR(15) NOT NULL UNIQUE,
    email VARCHAR(30) NOT NULL UNIQUE,
    password VARCHAR(16) NOT NULL,
    name_user VARCHAR(12) NOT NULL,
    id_roles integer REFERENCES roles,
    id_game_controller integer REFERENCES game_controller
);

CREATE TABLE IF NOT EXISTS color
(
    name_color VARCHAR(5) PRIMARY KEY NOT NULL
);

CREATE TABLE IF NOT EXISTS position
(
    id_position INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_history_move integer REFERENCES id_history_move
);

CREATE TABLE IF NOT EXISTS piece
(
    id_piece INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name_piece VARCHAR(14) NOT NULL,
    id_chess_board integer REFERENCES chess_board,
    name_color integer REFERENCES color,
    id_position integer REFERENCES position
);

INSERT INTO roles (role)
VALUES
    ('Guest'),
    ('Player'),
    ('Admin');

INSERT INTO users (login, email, password, name_user)
VALUES
    ('genius', 'genius@genius.com', 'cipher', 'Alex'),
    ('smart', 'smart@smart.com', 'cipher', 'Jo');



