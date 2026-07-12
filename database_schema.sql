-- Database Schema for Event Registration Portal
-- Note: The 'User' table is intentionally omitted as it belongs to the Central Auth Service.

CREATE TABLE events (
    event_id INT AUTO_INCREMENT PRIMARY KEY,
    event_name VARCHAR(255) NOT NULL,
    description TEXT,
    event_date DATE NOT NULL,
    event_time TIME NOT NULL,
    venue VARCHAR(255),
    capacity INT NOT NULL,
    created_by_user_id INT
);

CREATE TABLE registrations (
    registration_id INT AUTO_INCREMENT PRIMARY KEY,
    participant_user_id INT,
    event_id INT NOT NULL,
    registration_date DATETIME,
    status VARCHAR(50),
    FOREIGN KEY (event_id) REFERENCES events(event_id) ON DELETE CASCADE
);
