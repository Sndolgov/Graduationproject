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


INSERT INTO menuconsist (menu_id,  dish_id) VALUES
  (100015,  100004),
  (100015,  100005),
  (100015,  100006),
  (100015,  100007),
  (100016,  100008),
  (100016,  100009),
  (100016,  100004),
  (100017,  100005),
  (100017,  100007),
  (100018,  100010),
  (100018,  100011),
  (100018,  100012),
  (100019,  100013),
  (100019,  100014),
  (100019,  100012),
  (100020,  100011),
  (100020,  100014);



INSERT INTO voting (menu_id, restaurant_id  , user_id, date) VALUES
  (100015, 100002, 100000, '2017-12-26'),
  (100015, 100002, 100001, '2017-12-26'),
  (100019, 100003, 100000, '2017-12-27'),
  (100019, 100003, 100001, '2017-12-27');


