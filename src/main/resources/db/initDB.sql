DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS menuconsist;
DROP TABLE IF EXISTS voting;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS dishes;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS users;


DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;


CREATE TABLE users
(
  id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name       VARCHAR                 NOT NULL,
  email      VARCHAR                 NOT NULL,
  password   VARCHAR                 NOT NULL,
  registered TIMESTAMP DEFAULT now() NOT NULL,
  enabled    BOOL DEFAULT TRUE       NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx  ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurants
(
  id      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name    VARCHAR                  NOT NULL,
  enabled BOOL DEFAULT TRUE        NOT NULL
);
CREATE UNIQUE INDEX restaurant_unique_name_idx  ON restaurants (name);


CREATE TABLE dishes
(
  id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  description   VARCHAR NOT NULL,
  price         INTEGER NOT NULL                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               DEFAULT 0,
  enabled       BOOL DEFAULT TRUE        NOT NULL,
  restaurant_id INTEGER                  NOT NULL,
  CONSTRAINT restaurant_description_idx UNIQUE (restaurant_id, description),
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE TABLE menu
(
  id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  description   VARCHAR NOT NULL,
  date          DATE    NOT NULL,
  restaurant_id INTEGER                  NOT NULL,
  CONSTRAINT restaurant_date_idx UNIQUE (restaurant_id, date),
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE TABLE menuconsist
(
  menu_id       INTEGER NOT NULL,
  dish_id INTEGER NOT NULL,
  restaurant_id INTEGER NOT NULL,
  CONSTRAINT menu_dish_idx UNIQUE (menu_id, dish_id),
  FOREIGN KEY (menu_id) REFERENCES menu (id) ON DELETE CASCADE,
  FOREIGN KEY (dish_id) REFERENCES dishes (id) ON DELETE CASCADE,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE

);

CREATE TABLE voting
(
  id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  menu_id       INTEGER NOT NULL,
  restaurant_id INTEGER NOT NULL,
  user_id INTEGER NOT NULL,
  CONSTRAINT menu_user_idx UNIQUE (menu_id, user_id),
  FOREIGN KEY (menu_id) REFERENCES menu (id) ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE

);

/*
CREATE TABLE menuconsist
(
  id INTEGER PRIMARY KEY DEFAULT nextval('global_seq') ,
  date DATE NOT NULL ,
  restaurant_id INTEGER NOT NULL ,
  menu_id INTEGER NOT NULL ,
  dish_id INTEGER NOT NULL ,
  deletionMark BOOLEAN DEFAULT FALSE ,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE ,
  FOREIGN KEY (menu_id) REFERENCES lunchmenus (id) ON DELETE CASCADE ,
  FOREIGN KEY (dish_id) REFERENCES dishes (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX menuconsist_unique_idx ON menuconsist (date, restaurant_id, menu_id, dish_id);
CLUSTER menuconsist USING menuconsist_unique_idx;
*/

/*
CREATE TABLE lunchmenus
(
  id INTEGER PRIMARY KEY DEFAULT nextval('global_seq') ,
  restaurant_id INTEGER NOT NULL ,
  description VARCHAR NOT NULL ,
  deletionMark BOOLEAN DEFAULT FALSE ,
  CONSTRAINT restaurant_description_idx UNIQUE (restaurant_id, description) ,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE TABLE voting
(
  id INTEGER PRIMARY KEY DEFAULT nextval('global_seq') ,
  date DATE NOT NULL ,
  user_id INTEGER NOT NULL ,
  restaurant_id INTEGER NOT NULL ,
  deletionMark BOOLEAN DEFAULT FALSE ,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE ,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX voting_unique_idx ON voting (date, user_id);*/
