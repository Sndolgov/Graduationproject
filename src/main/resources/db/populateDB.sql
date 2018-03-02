DELETE FROM user_roles;
DELETE FROM menuconsist;
DELETE FROM voting;
DELETE FROM menu;
DELETE FROM dishes;
DELETE FROM restaurants;
DELETE FROM users;

ALTER SEQUENCE global_seq RESTART WITH 100000;


INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', '{noop}password'),
  ('Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001),
  ('ROLE_USER', 100001);

INSERT INTO restaurants (name) VALUES
  ('Restaurant1'),
  ('Restaurant2');

INSERT INTO dishes (restaurant_id, description, price) VALUES
  (100002, 'Soup', 200),
  (100002, 'Meat', 300),
  (100002, 'Juice',100),
  (100002, 'Fish', 250),
  (100002, 'Eggs', 110),
  (100002, 'Pancakes', 150),
  (100003, 'Tea', 70),
  (100003, 'Cofee', 160),
  (100003, 'Salad', 210),
  (100003, 'Meat', 290),
  (100003, 'Fish', 280);


INSERT INTO menu (restaurant_id, description, date) VALUES

    (100002, 'Menu of First restaurant', '2017-12-26'),
    (100002, 'Menu of First restaurant', '2017-12-27'),
    (100002, 'Menu of First restaurant', now()),
    (100003, 'Menu of Second restaurant', '2017-12-26'),
    (100003, 'Menu of Second restaurant', '2017-12-27'),
    (100003, 'Menu of Second restaurant', now());




INSERT INTO menuconsist (menu_id, restaurant_id, dish_id) VALUES
  (100015, 100002, 100004),
  (100015, 100002, 100005),
  (100015, 100002, 100006),
  (100015, 100002, 100007),
  (100016, 100002, 100008),
  (100016, 100002, 100009),
  (100016, 100002, 100004),
  (100017, 100002, 100005),
  (100017, 100002, 100007),
  (100018, 100003, 100010),
  (100018, 100003, 100011),
  (100018, 100003, 100012),
  (100019, 100003, 100013),
  (100019, 100003, 100014),
  (100019, 100003, 100012),
  (100020, 100003, 100011),
  (100020, 100003, 100014);



INSERT INTO voting (menu_id, restaurant_id  , user_id, date) VALUES
  (100015, 100002, 100000, '2017-12-26'),
  (100015, 100002, 100001, '2017-12-26'),
--   (100016, 100002, 100000, '2017-12-27'),
--   (100016, 100002, 100001, '2017-12-27'),
--   (100017, 100002, 100000, now()),
--   (100017, 100002, 100001, now()),
--   (100018, 100003, 100000, '2017-12-26'),
--   (100018, 100003, 100001, '2017-12-26'),
  (100019, 100003, 100000, '2017-12-27'),
  (100019, 100003, 100001, '2017-12-27');
--   (100020, 100003, 100000),
--   (100020, 100003, 100001, now());

/*
-- id 100002
INSERT INTO restaurants (description)
VALUES ('First Restaurant');

-- id 100003
INSERT INTO restaurants (description)
VALUES ('Second Restaurant');

-- id 100004
INSERT INTO lunchmenus (restaurant_id, description)
VALUES (100002, 'Menu of First restaurant');

-- id 100005
INSERT INTO lunchmenus (restaurant_id, description)
VALUES (100003, 'Menu of Second restaurant');

-- id 100006
INSERT INTO dishes (restaurant_id, description, price)
VALUES (100002, 'Dish 1 of First restaurant', 100);

-- id 100007
INSERT INTO dishes (restaurant_id, description, price)
VALUES (100002, 'Dish 2 of First restaurant', 200);

-- id 100008
INSERT INTO dishes (restaurant_id, description, price)
VALUES (100003, 'Dish 1 of Second restaurant', 300);

-- id 100009
INSERT INTO menuconsist (date, restaurant_id, menu_id, dish_id)
VALUES ('2017-10-16', 100002, 100004, 100006);

-- id 100010
INSERT INTO menuconsist (date, restaurant_id, menu_id, dish_id)
VALUES ('2017-10-16', 100002, 100004, 100007);

-- id 100011
INSERT INTO menuconsist (date, restaurant_id, menu_id, dish_id)
VALUES ('2017-10-16', 100003, 100005, 100008);

-- id 100012
INSERT INTO menuconsist (date, restaurant_id, menu_id, dish_id)
VALUES ('2017-10-17', 100002, 100004, 100006);

-- id 100013
INSERT INTO voting (date, user_id, restaurant_id)
VALUES ('2017-10-16', 100000, 100002);

-- id 100014
INSERT INTO voting (date, user_id, restaurant_id)
VALUES ('2017-10-17', 100000, 100003);

-- id 100015
INSERT INTO voting (date, user_id, restaurant_id)
VALUES ('2017-10-16', 100001, 100002);

-- users -- id 100016
INSERT INTO users (description, email, password)
VALUES ('User 2', 'user2@yandex.ru', '$2a$10$Sh0ZD2NFrzRRJJEKEWn8l.92ROEuzlVyzB9SV1AM8fdluPR0aC1ni');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100016);*/
