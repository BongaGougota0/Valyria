-- Drop tables if they exist (for clean recreation)
DROP TABLE IF EXISTS user_action CASCADE;
DROP TABLE IF EXISTS product CASCADE;
DROP TABLE IF EXISTS "user" CASCADE;

-- Create User table
CREATE TABLE "user" (
    user_id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Product table
CREATE TABLE product (
     product_id SERIAL PRIMARY KEY,
     name VARCHAR(255) NOT NULL,
     description TEXT,
     price DECIMAL(10, 2) NOT NULL,
     view_count INTEGER DEFAULT 0,
     favourite_count INTEGER DEFAULT 0,
     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create UserAction table
CREATE TABLE user_action (
     id SERIAL PRIMARY KEY,
     product_id INTEGER NOT NULL,
     user_id INTEGER NULL,
     action_type VARCHAR(50) NOT NULL CHECK (action_type IN ('view', 'favourite')),
     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE,
     FOREIGN KEY (user_id) REFERENCES "user"(user_id) ON DELETE SET NULL
    );

-- Create indexes for better query performance
CREATE INDEX idx_user_action_product ON user_action(product_id);
CREATE INDEX idx_user_action_user ON user_action(user_id);
CREATE INDEX idx_user_action_type ON user_action(action_type);
CREATE INDEX idx_user_email ON "user"(email);

-- Insert some sample data for testing
INSERT INTO "user" (email, username, password) VALUES
('john@example.com', 'john_doe', 'hashed_password_123'),
('jane@example.com', 'jane_smith', 'hashed_password_456');

INSERT INTO product (name, description, price, view_count, favourite_count) VALUES
('Laptop', 'High-performance laptop for professionals', 999.99, 150, 25),
('Smartphone', 'Latest model with advanced features', 699.99, 320, 45),
('Headphones', 'Wireless noise-cancelling headphones', 199.99, 89, 12);

INSERT INTO user_action (product_id, user_id, action_type) VALUES
(1, 1, 'view'),
(1, 1, 'favourite'),
(2, 2, 'view'),
(3, NULL, 'view'),  -- Anonymous user viewing
(2, NULL, 'view');  -- Anonymous user viewing