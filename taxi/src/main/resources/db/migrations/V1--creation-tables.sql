CREATE TABLE IF NOT EXISTS aggregator
(
    id     SERIAL NOT NULL,
    name   TEXT   NOT NULL,
    car_id INT    NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (car_id) REFERENCES car (id) ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS car
(
    id             SERIAL NOT NULL,
    mark           TEXT   NOT NULL,
    number         TEXT   NOT NULL,
    prise_multiply REAL   NOT NULL,
    PRIMARY KEY (id)
);