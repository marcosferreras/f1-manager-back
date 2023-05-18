
-- ROL TABLE ----
insert into rol (id, rol_name) values (1, 'ROL_ADMIN');
insert into rol (id, rol_name) values (2, 'ROL_MANAGER');

-- TEAM TABLE ----
insert into team (id, logo, name, twitter) values (1, '/uploads/logoFerrari.png', 'Ferrari', 'ScuderiaFerrari');
insert into team (id, logo, name, twitter) values (2, '/uploads/logoMercedes.png', 'Mercedes', 'MercedesAMGF1');
insert into team (id, logo, name, twitter) values (3, '/uploads/logoRedBull.png', 'RedBull', 'redbullracing');

-- USER TABLE ----
insert into user(id, email, name, password, user_name, role_id, team_id) values (1,'admin@admin.com','admin', '$2a$12$ACASczZXZ30ox4YbpLHoNet6JvRq7YXOsUPR94LHuSLt2X/hdr3ti', 'adminUser', 1, null);
insert into user(id, email, name, password, user_name, role_id, team_id) values (2,'fredericvasseur@ferrari.com','Frederic', '$2a$12$G3lDAU4HgAx1COGagknwge2slblQW/jGsDAQ.bez8b7JcwftOGPJm', 'FredericFerrari', 2, 1);
insert into user(id, email, name, password, user_name, role_id, team_id) values (3,'totowolf@mercedes.com','Toto', '$2a$12$G3lDAU4HgAx1COGagknwge2slblQW/jGsDAQ.bez8b7JcwftOGPJm', 'TotoMercedes', 2, 2);
insert into user(id, email, name, password, user_name, role_id, team_id) values (4,'christianhorner@redbull.com','Christian', '$2a$12$G3lDAU4HgAx1COGagknwge2slblQW/jGsDAQ.bez8b7JcwftOGPJm', 'ChristianRedbull', 2, 3);

-- DRIVER TABLE ----
insert into driver(id, acronym, country, image, last_name, name, number, twitter, team_id) values (1, 'ALO', 'Spain', '/uploads/imageALO.jpg', 'Alonso', 'Fernando', 14, 'aloOfficial', 1);
insert into driver(id, acronym, country, image, last_name, name, number, twitter, team_id) values (2, 'SAI', 'Spain', '/uploads/imageSAI.jpg', 'Sainz', 'Carlos', 55, 'saiOfficial', 1);
insert into driver(id, acronym, country, image, last_name, name, number, twitter, team_id) values (3, 'HAM', 'United Kingdom', '/uploads/imageHAM.jpg', 'Hamilton', 'Lewis', 44, 'hamOfficial', 2);
insert into driver(id, acronym, country, image, last_name, name, number, twitter, team_id) values (4, 'VER', 'Holand', '/uploads/imageVER.jpg', 'Verstappen', 'Max', 1, 'verOfficial', 3);

-- CIRCUIT TABLE ----
insert into circuit(id, city, country, fast_curves, image, laps, medium_curves, meters, name, slow_curves) values (1, 'Monaco', 'Monaco', 4, '/uploads/monacoIMG.png', 58, 7, 3337, 'Monaco Circuit', 8);
insert into circuit(id, city, country, fast_curves, image, laps, medium_curves, meters, name, slow_curves) values (2, 'Silverstone', 'United Kingdom', 5, '/uploads/silverstoneIMG.png', 45, 8, 5901, 'Silverstone Circuit', 5);
insert into circuit(id, city, country, fast_curves, image, laps, medium_curves, meters, name, slow_curves) values (3, 'Monza', 'Italy', 8, '/uploads/monzaIMG.png', 51, 2, 5793, 'Monza Circuit', 1);

-- CARS TABLE ----
insert into car(id, ers_fast_curve, ers_medium_curve, ers_slow_curve, code, consumption, name, team_id) values (1, 0.05, 0.04, 0.03, 'F1R', 38, 'FerrariXP4', 1);
insert into car(id, ers_fast_curve, ers_medium_curve, ers_slow_curve, code, consumption, name, team_id) values (2, 0.04, 0.03, 0.02, 'M1C', 29, 'McClaren12Rs4', 2);
insert into car(id, ers_fast_curve, ers_medium_curve, ers_slow_curve, code, consumption, name, team_id) values (3, 0.03, 0.02, 0.01, 'R1B', 34, 'RedBullRB18', 3);

-- NEW TABLE ----
insert into new(id, image, link, text, title) values (1, '/uploads/notice1.jpg', 'https://www.formula1.com/en/latest/article.hamilton-says-engineer-pete-bonnington-is-like-a-brother-to-me-as-they-gear.6eGZFelycJknZEfJzCOvmS.html', 'Lewis Hamilton and race engineer Pete ‘Bono’ Bonnington are preparing for their 11th campaign together at Mercedes, where Hamilton will equal Michael Schumacher’s record for most seasons spent with one team. Ahead of the new campaign, the seven-time F1 champion looked back on the “amazing journey” he’s shared with Bono and explained the origins of the famous ''Hammer Time'' radio call…', 'Hamilton says engineer Pete Bonnington is ‘like a brother to me’ as they gear up for 11th season together');
insert into new(id, image, link, text, title) values (2, '/uploads/notice2.jpg', 'https://www.formula1.com/en/latest/article.red-bull-to-launch-their-2023-season-in-new-york-as-double-title-defence.52rPlOo5RLy180m1F4qrzT.html', 'F1 world champions Red Bull have announced that they will hold a ‘season launch’ in New York early in February, as the team look to continue their recent run of success. After winning the 2021 drivers’ championship with Max Verstappen, 2022 saw the Dutchman (15 wins from 22 races) and the outfit (17 wins) romp to both world titles, making the most of F1’s rules reset.', 'Red Bull to launch their 2023 season in New York as double title defence beckons');
-- SURVEY TABLE ----
insert into survey(id, description, limit_date, link, title) values (1, 'Vote the driver you think is the best one of the day', '2024-01-13', 'http://www.xxxx.com/enlace-permanente', 'Best Driver of the Day');
insert into survey(id, description, limit_date, link, title) values (2, 'Vote the driver you think is the worst one of the day', '2024-01-13', 'http://www.xxxx.com/enlace-permanente', 'Worst Driver of the Day');

-- VOTE TABLE ----
insert into vote (id, driver_id, email, name, survey_id) values (1, 1, 'santi@gmail.com', 'santi', 1);
insert into vote (id, driver_id, email, name, survey_id) values (2, 1, 'edu@gmail.com', 'edu', 1);
insert into vote (id, driver_id, email, name, survey_id) values (3, 2, 'ivan@gmail.com', 'ivan', 1);
insert into vote (id, driver_id, email, name, survey_id) values (4, 2, 'santi@gmail.com', 'santi', 2);
insert into vote (id, driver_id, email, name, survey_id) values (5, 2, 'edu@gmail.com', 'edu', 2);
insert into vote (id, driver_id, email, name, survey_id) values (6, 3, 'ivan@gmail.com', 'ivan', 2);