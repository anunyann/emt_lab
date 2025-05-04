-- Country table
CREATE TABLE IF NOT EXISTS country (
                                       id BIGSERIAL PRIMARY KEY,
                                       name VARCHAR(255) NOT NULL,
    continent VARCHAR(255) NOT NULL
    );

-- Host table
CREATE TABLE IF NOT EXISTS host (
                                    id BIGSERIAL PRIMARY KEY,
                                    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    country_id BIGINT REFERENCES country(id)
    );

-- Accommodation category enum
CREATE TYPE accommodation_category AS ENUM (
    'ROOM', 'HOUSE', 'FLAT', 'APARTMENT', 'HOTEL', 'MOTEL'
);

-- Accommodation table
CREATE TABLE IF NOT EXISTS accommodation (
                                             id BIGSERIAL PRIMARY KEY,
                                             name VARCHAR(255) NOT NULL,
    category VARCHAR(50) NOT NULL,
    host_id BIGINT REFERENCES host(id),
    num_rooms INTEGER NOT NULL,
    is_rented BOOLEAN DEFAULT false
    );

-- User role enum
CREATE TYPE user_role AS ENUM (
    'ROLE_USER', 'ROLE_HOST'
);

-- User table
CREATE TABLE IF NOT EXISTS airbnb_users (
                                            username VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    is_account_non_expired BOOLEAN DEFAULT true,
    is_account_non_locked BOOLEAN DEFAULT true,
    is_credentials_non_expired BOOLEAN DEFAULT true,
    is_enabled BOOLEAN DEFAULT true,
    role VARCHAR(50) NOT NULL
    );

-- Reservation status enum
CREATE TYPE reservation_status AS ENUM (
    'PENDING', 'CONFIRMED', 'CANCELED'
);

-- Reservation list table
CREATE TABLE IF NOT EXISTS reservation_list (
                                                id BIGSERIAL PRIMARY KEY,
                                                date_created TIMESTAMP NOT NULL,
                                                user_username VARCHAR(255) REFERENCES airbnb_users(username),
    status VARCHAR(50) NOT NULL
    );

-- Junction table for reservation_list and accommodation (many-to-many)
CREATE TABLE IF NOT EXISTS reservation_list_accommodations (
                                                               reservation_list_id BIGINT REFERENCES reservation_list(id) ON DELETE CASCADE,
    accommodations_id BIGINT REFERENCES accommodation(id) ON DELETE CASCADE,
    PRIMARY KEY (reservation_list_id, accommodations_id)
    );

-- Create materialized view for accommodations per host
CREATE MATERIALIZED VIEW accommodations_per_host AS
SELECT h.id AS host_id,
       h.name AS host_name,
       h.surname AS host_surname,
       COUNT(a.id) AS accommodation_count
FROM host h
         LEFT JOIN accommodation a ON a.host_id = h.id
GROUP BY h.id, h.name, h.surname;

-- Create materialized views for hosts per country
CREATE MATERIALIZED VIEW hosts_per_country AS
SELECT c.id AS country_id,
       c.name AS country_name,
       c.continent,
       COUNT(h.id) AS host_count
FROM country c
         LEFT JOIN host h ON h.country_id = c.id
GROUP BY c.id, c.name, c.continent;

-- Create indexes for better performance
CREATE INDEX idx_accommodation_host ON accommodation(host_id);
CREATE INDEX idx_host_country ON host(country_id);
CREATE INDEX idx_reservation_user ON reservation_list(user_username);
CREATE INDEX idx_reservation_status ON reservation_list(status);