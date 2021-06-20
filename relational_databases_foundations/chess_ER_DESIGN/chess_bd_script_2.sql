CREATE DATABASE chess; --create database for chess

CREATE TABLE IF NOT EXISTS chess_board(
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`))
ENGINE = InnoDB; -- use engine which is InnoDB


CREATE TABLE IF NOT EXISTS history_move (
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS game_controller (
  `id` INT NOT NULL AUTO_INCREMENT,
  `chess_board_id` INT NOT NULL,
  `history_move_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_game_controller_chess_board1_idx` (`chess_board_id` ASC) VISIBLE,
  INDEX `fk_game_controller_history_move1_idx` (`history_move_id` ASC) VISIBLE,
  CONSTRAINT `fk_game_controller_chess_board1`
    FOREIGN KEY (`chess_board_id`)
    REFERENCES chess_board (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_game_controller_history_move1`
    FOREIGN KEY (`history_move_id`)
    REFERENCES history_move (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS roles (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `role_UNIQUE` (`role` ASC) VISIBLE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS users (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(15) NOT NULL,
  `email` VARCHAR(15) NULL DEFAULT '',
  `password` VARCHAR(16) NOT NULL,
  `name` VARCHAR(12) NOT NULL,
  `roles_id` INT NOT NULL,
  `game_controller_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
  UNIQUE INDEX `password_UNIQUE` (`password` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  INDEX `fk_users_roles_idx` (`roles_id` ASC) VISIBLE,
  INDEX `fk_users_game_controller1_idx` (`game_controller_id` ASC) VISIBLE,
  CONSTRAINT `fk_users_roles`
    FOREIGN KEY (`roles_id`)
    REFERENCES `roles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_game_controller1`
    FOREIGN KEY (`game_controller_id`)
    REFERENCES `game_controller` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `color` (
  `name_color` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`name_color`))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `position` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `history_move_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_position_history_move1_idx` (`history_move_id` ASC) VISIBLE,
  CONSTRAINT `fk_position_history_move1`
    FOREIGN KEY (`history_move_id`)
    REFERENCES `history_move` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `piece` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(10) NOT NULL,
  `color` VARCHAR(5) NOT NULL,
  `chess_board_id` INT NOT NULL,
  `name_color` VARCHAR(5) NOT NULL,
  `position_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  INDEX `fk_pieces_chess_board1_idx` (`chess_board_id` ASC) VISIBLE,
  INDEX `fk_piece_color1_idx` (`name_color` ASC) VISIBLE,
  INDEX `fk_piece_position1_idx` (`position_id` ASC) VISIBLE,
  CONSTRAINT `fk_pieces_chess_board1`
    FOREIGN KEY (`chess_board_id`)
    REFERENCES `chess_board` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_piece_color1`
    FOREIGN KEY (`name_color`)
    REFERENCES `color` (`name_color`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_piece_position1`
    FOREIGN KEY (`position_id`)
    REFERENCES `position` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
