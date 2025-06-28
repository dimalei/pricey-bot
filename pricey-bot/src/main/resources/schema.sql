CREATE TABLE IF NOT EXISTS jobs (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    title VARCHAR(255),
    url VARCHAR(255),
    target_property VARCHAR(255)
);
CREATE TABLE IF NOT EXISTS intermediate_actions (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    job_id BIGINT,
    position INT,
    instruction VARCHAR(255),
    FOREIGN KEY (job_id) REFERENCES jobs(id)
);