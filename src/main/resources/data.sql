INSERT INTO user (id, e_mail, user_name, password, name, surname, role)
              VALUES (1,'miroslav@maildrop.cc','miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','Miroslav','Simic','ADMIN');
INSERT INTO user (id, e_mail, user_name, password, name, surname, role)
              VALUES (2,'tamara@maildrop.cc','tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','Tamara','Milosavljevic','USER');
INSERT INTO user (id, e_mail, user_name, password, name, surname, role)
              VALUES (3,'petar@maildrop.cc','petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','Petar','Jovic','USER');
INSERT INTO user (id, e_mail, user_name, password, name, surname, role)
              VALUES (4,'zokilj@gmail.com','ljuboja87','$2a$04$e4yrEWwPQxvPkGfqq6MMW.depVXrAeZQ5xxhEzoRUYgma4BbZRtUK','Zoran','Ljubojevic','ADMIN');
              
INSERT INTO venue (id, city, country) VALUES (1, 'Novi Sad', 'SRB');
INSERT INTO venue (id, city, country) VALUES (2, 'Beograd', 'SRB');
INSERT INTO venue (id, city, country) VALUES (3, 'Sremska Mitrovica', 'SRB');
INSERT INTO venue (id, city, country) VALUES (4, 'Budva', 'MNE');
INSERT INTO venue (id, city, country) VALUES (5, 'Šid', 'SRB');

INSERT INTO cart (id, user_id) VALUES (1, 3);
INSERT INTO cart (id, user_id) VALUES (2, 2);

INSERT INTO festival (id, name, start_date, end_date, price, available_tickets, description, venue_id) VALUES (1, 'Beer fest', '2023-08-15', '2023-08-20', 400, 2, 'Belgrade Beer Fest with a 20-year history in offering unforgettable experiences to beer and music lovers from the entire region.', 2);
INSERT INTO festival (id, name, start_date, end_date, price, available_tickets, description, venue_id) VALUES (2, 'Folk fest', '2023-02-13', '2023-02-15', 500, 550, '„Srem Folk Fest“ is an international folk dancing festival held every year between 11th and 14th of August, in Sremska Mitrovica (Serbia) under the patronage of the City of Sremska Mitrovica.', 3);
INSERT INTO festival (id, name, start_date, end_date, price, available_tickets, description, venue_id) VALUES (3, 'Tamburitza fest', '2023-04-01', '2023-04-04', 800, 300,'International tamburitza festival “Tamburica Fest” is held at Petrovaradin fortress in Novi Sad as a 3 day festival with unique concept and cultural mission to promote tamburitza music.', 1);
INSERT INTO festival (id, name, start_date, end_date, price, available_tickets, description, venue_id) VALUES (4, 'Exit', '2023-07-11', '2023-07-14', 900, 1000,'EXIT is an award-winning summer music festival that takes place at the Petrovaradin Fortress in Novi Sad, Serbia, with more than 1000 artists who play at over 40 stages and festival zones.', 1);
INSERT INTO festival (id, name, start_date, end_date, price, available_tickets, description, venue_id) VALUES (5, 'Rakijada', '2023-07-15', '2023-07-20', 500, 1000,'-', 1);
INSERT INTO festival (id, name, start_date, end_date, price, available_tickets, description, venue_id) VALUES (6, 'Kupusijada', '2023-10-15', '2023-10-18', 500, 1000,'-', 1);
INSERT INTO festival (id, name, start_date, end_date, price, available_tickets, description, venue_id) VALUES (7, 'Tucijada', '2023-12-12', '2023-12-20', 300, 1000,'-', 5);
INSERT INTO festival (id, name, start_date, end_date, price, available_tickets, description, venue_id) VALUES (8, 'Sea dance', '2023-08-27', '2023-08-29', 250, 4,'-', 4);

INSERT INTO cart_festival (cart_id, festival_id, no_tickets) VALUES (1, 1, 1);
INSERT INTO cart_festival (cart_id, festival_id, no_tickets) VALUES (1, 3, 5);
INSERT INTO cart_festival (cart_id, festival_id, no_tickets) VALUES (1, 6, 10);
INSERT INTO cart_festival (cart_id, festival_id, no_tickets) VALUES (2, 7, 20);
INSERT INTO cart_festival (cart_id, festival_id, no_tickets) VALUES (2, 8, 30);
