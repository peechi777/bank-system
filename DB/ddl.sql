CREATE DATABASE IF NOT EXISTS bank_system;
USE bank_system;

-- user
CREATE TABLE IF NOT EXISTS users (
    user_id VARCHAR(20) PRIMARY KEY,
    user_name VARCHAR(50) NOT NULL,
    email VARCHAR(100),
    account VARCHAR(50)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- product
CREATE TABLE IF NOT EXISTS products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(50),
    price DECIMAL(15, 2),
    fee_rate DECIMAL(5, 4),
    description TEXT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- likelist
CREATE TABLE IF NOT EXISTS like_list (
    sn INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(20),
    product_id INT,
    order_qty INT,
    account VARCHAR(50),
    total_fee DECIMAL(15, 2),
    total_amount DECIMAL(15, 2),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (product_id) REFERENCES products(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;