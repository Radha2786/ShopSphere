
A Springbbot Project
DB Queries

-- CREATE TABLE users(
-- id SERIAL PRIMARY KEY,
-- name VARCHAR(100) NOT NULL,
-- email VARCHAR(150) UNIQUE NOT NULL,
-- role VARCHAR(50) NOT NULL,
-- password VARCHAR(255) NOT NULL
-- ) 

CREATE TABLE categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price NUMERIC(10,2) NOT NULL,
    -- stock INTEGER DEFAULT 0,
    category_id INT REFERENCES categories(id) ON DELETE SET NULL
);

CREATE TABLE cart (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id) ON DELETE CASCADE
);

-- cart_items (Many-to-Many between cart and products)
CREATE TABLE cart_items (
    id SERIAL PRIMARY KEY,
    cart_id INT REFERENCES cart(id) ON DELETE CASCADE,
    product_id INT REFERENCES products(id),
    quantity INT DEFAULT 1
);

CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    total NUMERIC(10,2),
    status VARCHAR(50) DEFAULT 'pending',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE order_items (
    id SERIAL PRIMARY KEY,
    order_id INT REFERENCES orders(id) ON DELETE CASCADE,
    product_id INT REFERENCES products(id),
    quantity INT,
    price NUMERIC(10,2)  -- snapshot of price at time of order
);

CREATE TABLE reviews (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id) ON DELETE CASCADE,
    product_id INT REFERENCES products(id) ON DELETE CASCADE,
    rating INT CHECK (rating >= 1 AND rating <= 5),
    comment TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);




