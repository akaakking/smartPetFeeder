CREATE DATABASE IF NOT EXISTS smartPetFeeder;
USE smartPetFeeder;
START TRANSACTION;
CREATE TABLE blog
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    user_id     INT,
    title VARCHAR(255),
    title_src   VARCHAR(255),
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
create TABLE user_blog_likes
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    user_id     INT,
    blog_id     INT,
    like_status  TINYINT(1)
) ENGINE = InnoDB
  CHARSET = utf8;
CREATE TABLE pet
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    user_id       INT,
    `name`        VARCHAR(255),
    gender        TINYINT(1),
    age           INT,
    weight        DECIMAL(5, 2),
    birthdate     DATE,
    pet_type      INT,
    avatar        VARCHAR(255),
    feeding_plan_json  VARCHAR(255),
    `description` TEXT,
    `deviceId`    VARCHAR(255)
) ENGINE = InnoDB
  CHARSET = utf8;
CREATE TABLE pet_type
(
    id             INT AUTO_INCREMENT PRIMARY KEY,
    type_name      VARCHAR(255),
    breed_category VARCHAR(255)
) ENGINE = InnoDB
  CHARSET = utf8;
COMMIT