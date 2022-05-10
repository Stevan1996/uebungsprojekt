CREATE OR REPLACE TABLE games (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title varchar(255),
    release_date date,
    description varchar(255),
    trailer varchar(255)
);

CREATE OR REPLACE TABLE developers (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    developer varchar(255)
);

CREATE OR REPLACE TABLE platforms (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    platform varchar(255)
);

CREATE OR REPLACE TABLE ratings (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    comment varchar(255),
    rating INT,
    game_id BIGINT NOT NULL,
    CONSTRAINT `game_ratings`
        FOREIGN KEY (game_id) REFERENCES games (id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT
);

CREATE OR REPLACE TABLE game_developer (
    game_id BIGINT NOT NULL,
    developer_id INT NOT NULL,
    PRIMARY KEY (game_id, developer_id),
    CONSTRAINT `games_to_developer`
        FOREIGN KEY (game_id) REFERENCES games (id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT,
    CONSTRAINT `developer_to_games`
        FOREIGN KEY (developer_id) REFERENCES developers (id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT
);

CREATE OR REPLACE TABLE game_developer (
    game_id BIGINT NOT NULL,
    platform_id INT NOT NULL,
    PRIMARY KEY (game_id, platform_id),
    CONSTRAINT `games_to_platform`
        FOREIGN KEY (game_id) REFERENCES games (id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT,
    CONSTRAINT `platform_to_games`
        FOREIGN KEY (platform_id) REFERENCES platforms (id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT
);