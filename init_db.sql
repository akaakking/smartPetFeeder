CREATE DATABASE IF NOT EXISTS smartPetFeeder;
USE smartPetFeeder;
START TRANSACTION;
CREATE TABLE slider_pic
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    pic_url text
) ENGINE = InnoDB
  CHARSET = utf8;
CREATE TABLE today_topic
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    title_src   VARCHAR(255),
    content   VARCHAR(255)
) ENGINE = InnoDB
  CHARSET = utf8;

CREATE TABLE hand_book
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    hand_book_title VARCHAR(255),
    hand_book_src   VARCHAR(255),
    content   VARCHAR(255)
) ENGINE = InnoDB
  CHARSET = utf8;

CREATE TABLE user
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    `name`        VARCHAR(255),
    `account`     VARCHAR(255),
    `password`    VARCHAR(255),
    third_account VARCHAR(255)
) ENGINE = InnoDB
  CHARSET = utf8;
CREATE TABLE pet
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    `name`        VARCHAR(255),
    gender        TINYINT(1),
    age           INT,
    weight        DECIMAL(5, 2),
    birthdate     DATE,
    pet_type      INT,
    avatar        VARCHAR(255),
    `description` TEXT
) ENGINE = InnoDB
  CHARSET = utf8;
CREATE TABLE pet_type
(
    id             INT AUTO_INCREMENT PRIMARY KEY,
    type_name      VARCHAR(255),
    breed_category VARCHAR(255)
) ENGINE = InnoDB
  CHARSET = utf8;
CREATE TABLE feeding_schedule
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    pet       INT,
    frequency INT,
    duration  INT
) ENGINE = InnoDB
  CHARSET = utf8;
COMMIT