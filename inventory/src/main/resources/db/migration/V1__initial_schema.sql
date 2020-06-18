CREATE TABLE product_stock_and_price
(
    id bigint NOT NULL PRIMARY KEY,
    product_code varchar(255),
    price decimal(10, 2),
    stock integer
)
