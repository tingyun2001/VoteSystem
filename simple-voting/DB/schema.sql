CREATE DATABASE IF NOT EXISTS voting_db;
USE voting_db;

CREATE TABLE vote_item (
    item_id   INT PRIMARY KEY AUTO_INCREMENT,
    item_name VARCHAR(100) NOT NULL
);

CREATE TABLE vote_record (
    record_id  INT PRIMARY KEY AUTO_INCREMENT,
    voter_name VARCHAR(50) NOT NULL,
    item_id    INT NOT NULL,
    FOREIGN KEY (item_id) REFERENCES vote_item(item_id)
);
