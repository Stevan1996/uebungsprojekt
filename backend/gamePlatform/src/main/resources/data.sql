INSERT INTO developers (developer)
VALUES ('Intelligent Systems');
SET @Intelligent_Systems_id = LAST_INSERT_ID();

INSERT INTO developers (developer)
VALUES ('Game Freak');
SET @Game_Freak_id = LAST_INSERT_ID();

INSERT INTO developers (developer)
VALUES ('ILCA');
SET @ILCA_id = LAST_INSERT_ID();

INSERT INTO developers (developer)
VALUES ('MOJANG Studios');
SET @MOJANG_id = LAST_INSERT_ID();

INSERT INTO developers (developer)
VALUES ('Xbox Game Studios');
SET @XBOX_Game_Studio_id = LAST_INSERT_ID();

INSERT INTO developers (developer)
VALUES ('Platinum Games');
SET @Platinum_Games_id = LAST_INSERT_ID();

INSERT INTO games (title, release_date, description, trailer)
VALUES ('Fire Emblem: Three Houses', '2019-07-26T00:00:00.000',
'15. Teil der Fire Emblem Hauptserie', 'https://www.youtube.com/watch?v=rkux5h0PeXo');
SET @FE_id = LAST_INSERT_ID();

INSERT INTO game_developer (developer_id, game_id)
VALUES (@Intelligent_Systems_id, @FE_id);

INSERT INTO ratings (rating, comment, game_id)
VALUES (5, 'Schönes Strategiespiel', @FE_id);

INSERT INTO games (title, release_date, description, trailer)
VALUES ('Nier: Automata', '2017-02-23T00:00:00.000',
'Spielt in selben Universum wie Drakengard', 'https://www.youtube.com/watch?v=wJxNhJ8fjFk');
SET @Nier_id = LAST_INSERT_ID();

INSERT INTO game_developer(developer_id, game_id)
VALUES (@Platinum_Games_id, @Nier_id);

INSERT INTO games (title, release_date, description, trailer)
VALUES ('Pokemon Shield', '2019-11-15T00:00:00.000',
'8. Generation der Hauptspielreihe', 'https://www.youtube.com/watch?v=fPO2ouGn0lA');
SET @Poke_Shield_id = LAST_INSERT_ID();

INSERT INTO game_developer (developer_id, game_id)
VALUES (@Game_Freak_id ,@Poke_Shield_id);

INSERT INTO ratings (rating, comment, game_id)
VALUES (3, 'mittelmäßiges Spiel, bei dem viel Inhalt entfernt wurde', @Poke_Shield_id);

INSERT INTO ratings (rating, comment, game_id)
VALUES (2, 'enttäuschendes Hauptreihenspiel', @Poke_Shield_id);

INSERT INTO games (title, release_date, description, trailer)
VALUES ('Pokemon Sword', '2019-11-15T00:00:00.000',
'Achte Generation der Hauptspielreihe', 'https://www.youtube.com/watch?v=fPO2ouGn0lA');
SET @Poke_Sword_id = LAST_INSERT_ID();

INSERT INTO game_developer (developer_id, game_id)
VALUES (@Game_Freak_id, @Poke_Sword_id);

INSERT INTO games (title, release_date, description, trailer)
VALUES ('Pokemon Brilliant Diamond', '2021-11-19T00:00:00.000',
'Remake der 4. Generation', 'https://www.youtube.com/watch?v=1pDI8Scph44');
SET @Poke_BD_id = LAST_INSERT_ID();

INSERT INTO game_developer (developer_id, game_id)
VALUES (@ILCA_id, @Poke_BD_id);

INSERT INTO games (title, release_date, description, trailer)
VALUES ('Pokemon Shining Pearl', '2021-11-19T00:00:00.000',
'Remake der 4. Generation', 'https://www.youtube.com/watch?v=1pDI8Scph44');
SET @Poke_SP_id = LAST_INSERT_ID();

INSERT INTO game_developer (developer_id, game_id)
VALUES (@ILCA_id, @Poke_SP_id);

INSERT INTO ratings (rating, comment, game_id)
VALUES (1, 'sehr verbuggtes Remake', @Poke_SP_id);

INSERT INTO ratings (rating, comment, game_id)
VALUES (1, 'billiges Remake, bei dem der Backend-Code 1:1 übernommen wurde', @Poke_SP_id);

INSERT INTO platforms (platform)
VALUES ('PC');
SET @PC_id = LAST_INSERT_ID();

INSERT INTO platforms (platform)
VALUES ('PS5');
SET @PS5_id = LAST_INSERT_ID();

INSERT INTO platforms (platform)
VALUES ('PS4');
SET @PS4_id = LAST_INSERT_ID();

INSERT INTO platforms (platform)
VALUES ('XBOX');
SET @XBOX_id = LAST_INSERT_ID();

INSERT INTO platforms (platform)
VALUES ('Nintendo Switch');
SET @NINTENDO_SWITCH_id = LAST_INSERT_ID();

INSERT INTO game_platform (game_id, platform_id)
VALUES (@FE_id, @NINTENDO_SWITCH_id);

INSERT INTO game_platform (game_id, platform_id)
VALUES (@Poke_Sword_id, @NINTENDO_SWITCH_id);

INSERT INTO game_platform (game_id, platform_id)
VALUES (@Poke_Shield_id, @NINTENDO_SWITCH_id);

INSERT INTO game_platform (game_id, platform_id)
VALUES (@Poke_SP_id, @NINTENDO_SWITCH_id);

INSERT INTO game_platform (game_id, platform_id)
VALUES (@Poke_BD_id, @NINTENDO_SWITCH_id);

INSERT INTO games (title, release_date, description, trailer)
VALUES ('Minecraft', '2011-11-18T00:00:00.000',
'Action-Adventure Spiel', 'https://www.youtube.com/watch?v=MmB9b5njVbA');
SET @Minecraft_id = LAST_INSERT_ID();

INSERT INTO game_developer (developer_id, game_id)
VALUES (@MOJANG_id, @Minecraft_id);

INSERT INTO game_developer (developer_id, game_id)
VALUES (@XBOX_Game_Studio_id, @Minecraft_id);

INSERT INTO game_platform (game_id, platform_id)
VALUES (@Minecraft_id, @PC_id);

INSERT INTO game_platform (game_id, platform_id)
VALUES (@Minecraft_id, @XBOX_id);

INSERT INTO game_platform (game_id, platform_id)
VALUES (@Nier_id, @PC_id);

INSERT INTO game_platform (game_id, platform_id)
VALUES (@Nier_id, @XBOX_id);

INSERT INTO game_platform (game_id, platform_id)
VALUES (@Nier_id, @PS4_id);