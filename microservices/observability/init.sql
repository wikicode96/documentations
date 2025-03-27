CREATE TABLE orders (
    id UUID PRIMARY KEY,
    product VARCHAR(255) NOT NULL,
    quantity INTEGER NOT NULL
);