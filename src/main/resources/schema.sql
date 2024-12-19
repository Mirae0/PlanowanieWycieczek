CREATE TABLE IF NOT EXISTS trips_test(
    id INT NOT NULL,
    title VARCHAR(250) NOT NULL,
    trip_from VARCHAR(250) NOT NULL,
    trip_to VARCHAR(250),
    trip_length DECFLOAT,
    PRIMARY KEY(id)
);