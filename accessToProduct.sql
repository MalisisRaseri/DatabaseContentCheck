CREATE DATABASE if NOT EXISTS accessToProduct;
CREATE TABLE products (
    item CHAR(7) PRIMARY KEY,
    item_name VARCHAR(50) NOT NULL,
    item_color VARCHAR(20),
    item_price INTEGER NOT NULL,
    quantity_left INTEGER NOT NULL,
    CHECK(item_price > 0 ),
    CHECK(quantity_left >= 0)
    );

CREATE TABLE orders (
    id INTEGER PRIMARY KEY,
    registration_date DATE NOT NULL,
    customer_name VARCHAR(100) NOT NULL,
    phone_num VARCHAR(50),
    email VARCHAR(50),
    delivery_address VARCHAR(200) NOT NULL,
    order_status VARCHAR(1) NOT NULL,
    shipment_date DATE,
    CHECK (order_status = 'P' or order_status = 'C' or order_status = 'S' ),
    CHECK ( order_status = 'S' and shipment_date is NOT NULL
            or order_status <> 'S' and shipment_date is NULL )
);
CREATE TABLE order_lines (
    order_id INTEGER NOT NULL REFERENCES orders,
    item CHAR(7) NOT NULL REFERENCES products,
    order_price INTEGER NOT NULL,
    order_quantity INTEGER NOT NULL,
    PRIMARY KEY (order_id, item),
    CHECK (order_quantity > 0 ),
    CHECK (order_price > 0 )
);
INSERT INTO products(item, item_name, item_color, item_price, quantity_left) value
('3251615', 'Стол кухонный', 'белый', 8000, 12);
INSERT INTO products(item, item_name, item_price, quantity_left) value
('3251616', 'Стол кухонный', 8000, 15);
INSERT INTO products(item, item_name, item_color, item_price, quantity_left) values
('3251617', 'Стул столовый "гусарский"', 'орех', 4000, 0),
('3251619', 'Стул столовый с высокой спинкой', 'белый', 3500, 37),
('3251620', 'Стул столовый с высокой спинкой', 'коричневый', 3500, 52);
INSERT INTO orders(id, registration_date, customer_name, phone_num, email, delivery_address, order_status, shipment_date) value
(1, '2020-11-20', 'Сергей Иванов', '(981)123-45-67', default, 'ул. Веденеева, 20-1-41', 'S', '2020-11-29');
INSERT INTO order_lines(order_id, item, order_price, order_quantity) value
(1, '3251616', 7500, 1);
INSERT INTO orders(id, registration_date, customer_name, phone_num, email, delivery_address, order_status, shipment_date) value
(2, '2020-11-22', 'Алексей Комаров', '(921)001-22-33', default, 'пр. Пархоменко 51-2-123', 'S', '2020-11-29');
INSERT INTO order_lines(order_id, item, order_price, order_quantity) value
(2, '3251615', 7500, 1);
INSERT INTO orders(id, registration_date, customer_name, phone_num, email, delivery_address, order_status, shipment_date) value
(3, '2020-11-28', 'Ирина Викторова', '(911)009-88-77', default, 'Тихорецкий пр. 21-21', 'P', null);
INSERT INTO order_lines(order_id, item, order_price, order_quantity) values
(3, '3251615', 8000, 1),
(3, '3251617', 4000, 4);
INSERT INTO orders(id, registration_date, customer_name, phone_num, email, delivery_address, order_status, shipment_date) value
(4, '2020-12-03', 'Павел Николаев', default, 'pasha_nick@mail.ru', 'ул. Хлопина 3-88', 'P', null);
INSERT INTO order_lines(order_id, item, order_price, order_quantity) value
(4, '3251619', 3500, 2);
INSERT INTO orders(id, registration_date, customer_name, phone_num, email, delivery_address, order_status, shipment_date) value
(5, '2020-12-03', 'Антонина Васильева', '(931)777-66-55', 'antvas66@gmail.com', 'пр. Науки, 11-3-9', 'P', null);
INSERT INTO order_lines(order_id, item, order_price, order_quantity) values
(5, '3251615', 8000, 1),
(5, '3251617', 4000, 4);
INSERT INTO orders(id, registration_date, customer_name, phone_num, email, delivery_address, order_status, shipment_date) value
(6, '2020-12-10', 'Ирина Викторова', '(911)009-88-77', default, 'Тихорецкий пр. 21-21', 'P', null);
INSERT INTO order_lines(order_id, item, order_price, order_quantity) value
(6, '3251617', 4000, 2);

SELECT * FROM accessToProduct.orders;
/*
список заказов, созданных: в ноябре, в декабре;
*/
SELECT * FROM orders WHERE registration_date BETWEEN '2020-11-01' AND '2020-11-30';
SELECT * FROM orders WHERE registration_date BETWEEN '2020-12-01' AND '2020-12-31';
/*
список заказов, отгруженных: в ноябре, в декабре;
*/
SELECT * FROM orders WHERE shipment_date BETWEEN '2020-11-01' AND '2020-11-30';
SELECT * FROM orders WHERE shipment_date BETWEEN '2020-12-01' AND '2020-12-31';
/*
список клиентов: для каждого клиента должны быть выведены его ФИО, телефон и
адрес электронной почты;
*/
SELECT DISTINCT customer_name, phone_num, email  FROM  orders;
/*
список позиций заказа с id=3;
*/
SELECT * FROM order_lines WHERE order_id = 3;
/*
названия товаров, включённых в заказ с id=3;
*/
SELECT item_name FROM  products WHERE item IN (SELECT item FROM order_lines WHERE order_id = 3);
/*
Напишите запрос, фиксирующий отгрузку заказа с id=5. Запрос должен:
• менять статус заказа и фиксировать дату отгрузки;
• уменьшать остаток товара на складе
*/
UPDATE  products
	SET  quantity_left = (quantity_left + 10)
	WHERE  item = '3251617';
	UPDATE orders  SET order_status = 'S', shipment_date = '2020-12-24' WHERE id = 5;
	UPDATE products
	SET quantity_left = quantity_left - (SELECT order_quantity FROM order_lines	WHERE order_id = 5 AND products.item = order_lines.item)
    WHERE item IN (SELECT item FROM order_lines WHERE order_id = 5);


