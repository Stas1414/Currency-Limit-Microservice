
CREATE TABLE account_limit (
                               id SERIAL PRIMARY KEY,
                               category VARCHAR(255) NOT NULL,
                               limit_amount DECIMAL(19,2) NOT NULL DEFAULT 1000,
                               currency VARCHAR(10) NOT NULL DEFAULT 'USD',
                               set_date TIMESTAMP NOT NULL
);

CREATE TABLE transaction (
                             id SERIAL PRIMARY KEY,
                             account_from BIGINT NOT NULL,
                             account_to BIGINT NOT NULL,
                             date TIMESTAMP NOT NULL,
                             amount DECIMAL(19,2) NOT NULL,
                             currency VARCHAR(10) NOT NULL,
                             category VARCHAR(255) NOT NULL,
                             limit_exceeded BOOLEAN NOT NULL DEFAULT FALSE
);

INSERT INTO account_limit (category, limit_amount, currency, set_date)
VALUES
    ('service', 1500, 'USD', NOW()),
    ('products', 500, 'USD', NOW()),
    ('products', 300, 'USD', NOW());

INSERT INTO transaction (account_from, account_to, date, amount, currency, category, limit_exceeded)
VALUES
    (1, 2, NOW(), 200, 'USD', 'service', FALSE),
    (2, 3, NOW(), 600, 'USD', 'products', TRUE),
    (3, 1, NOW(), 50, 'USD', 'service', FALSE);
