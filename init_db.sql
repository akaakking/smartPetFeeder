CREATE DATABASE IF NOT EXISTS smartPetFeeder;
USE smartPetFeeder;
START TRANSACTION;
CREATE TABLE blog
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id     BIGINT,
    title VARCHAR(255),
    title_src   VARCHAR(255),
    content   VARCHAR(255)
) ENGINE = InnoDB
  CHARSET = utf8;

CREATE TABLE user
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name`        VARCHAR(255),
    `account`     VARCHAR(255),
    `password`    VARCHAR(255),
    wechat_id VARCHAR(255),
    avatar        VARCHAR(255)
) ENGINE = InnoDB
  CHARSET = utf8;
create TABLE user_blog_likes
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id     BIGINT,
    blog_id     BIGINT,
    like_status  TINYINT(1)
) ENGINE = InnoDB
  CHARSET = utf8;
CREATE TABLE pet
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id       BIGINT,
    `name`        VARCHAR(255),
    gender        TINYINT(1),
    age           INT,
    weight        DECIMAL(5, 2),
    birthdate     DATE,
    pet_type      VARCHAR(255),
    avatar        VARCHAR(255),
    feed_time_gap  VARCHAR(255),
    breakfast VARCHAR(255),
    lunch VARCHAR(255),
    dinner VARCHAR(255),
    `description` TEXT,
    `deviceId`    VARCHAR(255)
) ENGINE = InnoDB
  CHARSET = utf8;
CREATE TABLE device
(
    id            BIGINT PRIMARY KEY,
    user_id       BIGINT,
    `name`        VARCHAR(255),
    `description` TEXT,
    `status`      TINYINT(1)
) ENGINE = InnoDB
  CHARSET = utf8;
COMMIT