INSERT INTO pizzas (name, description, price_in_cents, image_url, created_at) VALUES ('Margherita', 'Tomato sauce, mozzarella, and oregano', 700, 'https://images.newyorkpizza.nl/Products/Original/Margherita-8046.png', '2023-11-09 11:35:00');
INSERT INTO pizzas (name, description, price_in_cents, image_url, created_at) VALUES ('Pepperoni', 'Tomato sauce, mozzarella, and pepperoni', 800, 'https://images.newyorkpizza.nl/Products/Original/double_Pepperoni-8056.png', '2023-11-09 11:35:30');
INSERT INTO pizzas (name, description, price_in_cents, image_url, created_at) VALUES ('Hawaiian', 'Tomato sauce, mozzarella, and pineapple', 900, 'https://images.newyorkpizza.nl/Products/Original/Hawaii-8059.png', '2023-11-09 11:36:00');
INSERT INTO pizzas (name, description, price_in_cents, image_url, created_at) VALUES ('Meat Lovers', 'Tomato sauce, mozzarella, and meat', 950, 'https://images.newyorkpizza.nl/Products/Original/BBQ_meatlovers-8053.png', '2023-11-09 11:36:30');

INSERT INTO roles (id, name) VALUES(1, 'ADMIN');
INSERT INTO roles (id, name) VALUES(2, 'USER');

INSERT INTO users (id, email, first_name, last_name) VALUES(1, 'test@mail.it', 'mail', 'test');
INSERT INTO users (id, email, first_name, last_name) VALUES(2, 'mariorossi@mail.it', 'mario', 'rossi');

INSERT INTO users_roles (user_id, roles_id) VALUES(1, 1);
INSERT INTO users_roles (user_id, roles_id) VALUES(1, 2);
INSERT INTO users_roles (user_id, roles_id) VALUES(2, 2);