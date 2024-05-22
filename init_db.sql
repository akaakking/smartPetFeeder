CREATE DATABASE IF NOT EXISTS smartPetFeeder;
USE smartPetFeeder;
START TRANSACTION;
CREATE TABLE blog
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    user_id     INT,
    title VARCHAR(255),
    title_src   VARCHAR(255),
    content   TEXT
) ENGINE = InnoDB
  CHARSET = utf8;

CREATE TABLE user
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    `name`        VARCHAR(255),
    `account`     VARCHAR(255),
    `password`    VARCHAR(255),
    wechat_id VARCHAR(255),
    avatar        VARCHAR(255)
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
    weight        INT,
    birthdate     DATE,
    pet_type      VARCHAR(255),
    avatar        VARCHAR(255),
    feed_time_gap  VARCHAR(255),
    breakfast VARCHAR(255),
    lunch VARCHAR(255),
    dinner VARCHAR(255),
    `description` TEXT,
    `device_id`    INT
) ENGINE = InnoDB
  CHARSET = utf8;
CREATE TABLE device
(
    id            INT PRIMARY KEY,
    user_id       INT,
    `name`        VARCHAR(255),
    `description` TEXT,
    `status`      TINYINT(1)
) ENGINE = InnoDB
  CHARSET = utf8;
COMMIT