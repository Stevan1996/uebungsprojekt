INSERT INTO games (title, release_date, developer)
VALUES ('Fire Emblem: Three Houses', '2019-07-26T00:00:00.000', 'Intelligent Systems');

INSERT INTO ratings (rating, comment, game_id)
VALUES (5, 'Schönes Strategiespiel', LAST_INSERT_ID());

INSERT INTO games (title, release_date, developer)
VALUES ('Pokemon Shield', '2019-11-15T00:00:00.000', 'Game Freak');

INSERT INTO ratings (rating, comment, game_id)
VALUES (3, 'mittelmäßiges Spiel, bei dem viel Inhalt entfernt wurde', LAST_INSERT_ID());

INSERT INTO ratings (rating, comment, game_id)
VALUES (2, 'enttäuschendes Hauptreihenspiel', LAST_INSERT_ID());

INSERT INTO games (title, release_date, developer)
VALUES ('Pokemon Sword', '2019-11-15T00:00:00.000', 'Game Freak');

INSERT INTO games (title, release_date, developer)
VALUES ('Pokemon Brilliant Diamond', '2021-11-19T00:00:00.000', 'ILCA');

INSERT INTO games (title, release_date, developer)
VALUES ('Pokemon Shining Pearl', '2021-11-19T00:00:00.000', 'ILCA');

INSERT INTO ratings (rating, comment, game_id)
VALUES (1, 'sehr verbuggtes Remake', LAST_INSERT_ID());

INSERT INTO ratings (rating, comment, game_id)
VALUES (1, 'billiges Remake, bei dem der Backend-Code 1:1 übernommen wurde', LAST_INSERT_ID());