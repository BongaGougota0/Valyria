-- Drop tables if they exist (for clean recreation)
DROP TABLE IF EXISTS user_actions CASCADE;
DROP TABLE IF EXISTS products CASCADE;
DROP TABLE IF EXISTS users CASCADE;

-- CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Create Users table
CREATE TABLE IF NOT EXISTS public.users (
                       user_id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
                       user_name VARCHAR(40) NOT NULL UNIQUE,
                       email VARCHAR(40) NOT NULL UNIQUE,
                       password VARCHAR(40) NOT NULL
);

-- Create Products table
CREATE TABLE IF NOT EXISTS public.products (
                          product_id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          description TEXT NOT NULL,
                          image_url VARCHAR(500) NOT NULL,
                          price DECIMAL(10, 2) NOT NULL,
                          view_count INTEGER DEFAULT 0,
                          favourite_count INTEGER DEFAULT 0,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create UserActions table
CREATE TABLE IF NOT EXISTS public.user_actions (
                              id BIGSERIAL PRIMARY KEY,
                              product_id VARCHAR(255) NOT NULL,
                              user_id VARCHAR(255) NULL,
                              action_type VARCHAR(50) NOT NULL CHECK (action_type IN ('view', 'favourite')),
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes for better query performance
CREATE INDEX idx_user_actions_product ON public.user_actions(product_id);
CREATE INDEX idx_user_actions_user ON public.user_actions(user_id);
CREATE INDEX idx_user_actions_type ON public.user_actions(action_type);
CREATE INDEX idx_users_email ON public.users(email);
CREATE INDEX idx_users_username ON public.users(user_name);

-- Insert some sample data for testing
INSERT INTO public.users (user_name, email, password) VALUES
                                                   ('john_doe', 'john@example.com', 'some_hshed_password_comes_here123'),
                                                   ('jane_smith', 'jane@example.com', 'some_hshed_password_comes_here456');

INSERT INTO public.products (name, description, image_url, price, view_count, favourite_count) VALUES
                                                                                            ('Laptop', 'High-performance laptop for professionals', 'https://fastly.picsum.photos/id/2/5000/3333.jpg?hmac=_KDkqQVttXw_nM-RyJfLImIbafFrqLsuGO5YuHqD-qQ', 999.99, 150, 25),
                                                                                            ('Smartphone', 'Latest model with advanced features', 'https://fastly.picsum.photos/id/160/3200/2119.jpg?hmac=cz68HnnDt3XttIwIFu5ymcvkCp-YbkEBAM-Zgq-4DHE', 699.99, 320, 45),
                                                                                            ('Console', 'Wireless PS5 console', 'https://fastly.picsum.photos/id/96/4752/3168.jpg?hmac=KNXudB1q84CHl2opIFEY4ph12da5JD5GzKzH5SeuRVM', 199.99, 89, 12);

INSERT INTO public.user_actions (product_id, user_id, action_type) VALUES
                                                                ('550e8400-e29b-41d4-a716-446655440000', '550e8400-e29b-41d4-a716-446655440001', 'view'),
                                                                ('550e8400-e29b-41d4-a716-446655440000', '550e8400-e29b-41d4-a716-446655440001', 'favourite'),
                                                                ('550e8400-e29b-41d4-a716-446655440002', '550e8400-e29b-41d4-a716-446655440003', 'view'),
                                                                ('550e8400-e29b-41d4-a716-446655440004', NULL, 'view'),
                                                                ('550e8400-e29b-41d4-a716-446655440002', NULL, 'view');