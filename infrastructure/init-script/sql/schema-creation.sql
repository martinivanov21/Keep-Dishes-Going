CREATE USER kdg WITH PASSWORD 'kdg';
CREATE DATABASE kdg;
GRANT ALL PRIVILEGES ON DATABASE kdg TO kdg;

CREATE SCHEMA kdg_restaurant;
CREATE SCHEMA kdg_customerOrder;

DROP TABLE IF EXISTS kdg_restaurant.restaurant_events;
DROP TABLE IF EXISTS kdg_restaurant.restaurant;

