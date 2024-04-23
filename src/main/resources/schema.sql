CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name varchar(50) NOT NULL,
    email varchar(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS tasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title varchar(50) NOT NULL,
    text varchar(50) NOT NULL,
    task_status varchar(50) NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

INSERT INTO users VALUES (DEFAULT, 'Roman', 'sup.makulin@mail.ru');
INSERT INTO users VALUES (DEFAULT, 'Olga', 'sadasdad@mail.ru');
INSERT INTO users VALUES (DEFAULT, 'Ann', '123gfg@mail.ru');

INSERT INTO tasks VALUES (DEFAULT, 'Молоко', 'Купить 2 булытки', 'NOT_STARTED', 1);
INSERT INTO tasks VALUES (DEFAULT, 'ДЗ', 'Сделать школьное', 'NOT_STARTED', 1);
INSERT INTO tasks VALUES (DEFAULT, 'Кофе', 'Сделать кофе', 'IN_PROGRESS', 1);
INSERT INTO tasks VALUES (DEFAULT, 'Машина', 'Обновить ТО', 'COMPLETED', 1);
INSERT INTO tasks VALUES (DEFAULT, 'Кот', 'Погладлить кота', 'NOT_STARTED', 2);

