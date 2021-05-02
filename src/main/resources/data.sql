DROP TABLE IF EXISTS USERS;
CREATE TABLE USERS (
  id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(100) NOT NULL,
  first_name VARCHAR(100) NOT NULL,
  last_name VARCHAR(100) NOT NULL,
  password CHAR(100) NOT NULL,
  date_of_birth DATE NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX (username)
);

DROP TABLE IF EXISTS ORDERS;
CREATE TABLE ORDERS (
  id INT NOT NULL AUTO_INCREMENT,
  order_id VARCHAR(100) NOT NULL,
  date_of_order DATE NOT NULL,
  user_id INT NOT NULL,
  book_id INT NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY (user_id, book_id, order_id),
  FOREIGN KEY (user_id) REFERENCES USERS(id)
);

INSERT INTO USERS (id, first_name, last_name, username, password, date_of_birth) VALUES (1, 'pansak','kangphukeaw', 'kangphukeaw','$2a$10$D02XjIOKVZOFV5kL6aezXubWwkseex9uQZAiYLUFcUCZ3CPfiGS32', '1978-01-16');
INSERT INTO USERS (id, first_name, last_name, username, password, date_of_birth)  VALUES (2, 'eikq', 'phurittapong', 'kangphukeaw', '$2a$10$D02XjIOKVZOFV5kL6aezXubWwkseex9uQZAiYLUFcUCZ3CPfiGS32', '1978-01-16');
INSERT INTO USERS (id, first_name, last_name, username, password, date_of_birth)  VALUES (3, 'fon', 'aphitchaya', 'mahapan', '$2a$10$D02XjIOKVZOFV5kL6aezXubWwkseex9uQZAiYLUFcUCZ3CPfiGS32', '1978-01-16');
INSERT INTO `orders` (`id`, `order_id`, `date_of_order`, `user_id`, `book_id`) VALUES (17, '1120215003015006', '2021-05-03', 1, 1);
INSERT INTO `orders` (`id`, `order_id`, `date_of_order`, `user_id`, `book_id`) VALUES (18, '1220215003015038', '2021-05-03', 1, 2);

