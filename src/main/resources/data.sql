CREATE TABLE trips (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       from_location VARCHAR(255) NOT NULL,
                       to_location VARCHAR(255) NOT NULL,
                       trip_date DATE NOT NULL,
                       trip_note TEXT,
                       visibility VARCHAR(255),
                       photos TEXT
);

