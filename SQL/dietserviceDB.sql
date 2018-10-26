-- Used MySQL Database version 5.6.41.0
CREATE DATABASE dietservice;
CREATE TABLE dietservice.dish
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(255),
    calories int(11)
);
INSERT INTO dietservice.dish (name, calories) VALUES ('Borch', 100);
INSERT INTO dietservice.dish (name, calories) VALUES ('Pasta', 150);
CREATE TABLE dietservice.nutrition
(
    id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    date date,
    dishid int(11),
    weight int(11) COMMENT 'in grams',
    CONSTRAINT nutrition_dish_id_fk FOREIGN KEY (dishid) REFERENCES dietservice.dish (id)
);
CREATE INDEX nutrition_dish_id_fk ON dietservice.nutrition (dishid);
INSERT INTO dietservice.nutrition (date, dishid, weight) VALUES ('2018-10-19', 1, 150);
INSERT INTO dietservice.nutrition (date, dishid, weight) VALUES ('2018-10-19', 2, 200);