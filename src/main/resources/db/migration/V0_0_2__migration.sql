/*
  Schema
  Engine: PostGreSQL
  Version: 0.0.1
  Description: Initial database structure
 */

 /*
  Structure
  */
ALTER TABLE users
DROP COLUMN city;
/*
  Data
 */
-- insert into users (firstname, lastname, email, age, gender, country, city, password, role) values ('Admin123', '', 'migrationworks@gmail.com', 0, 'Robot', 'No Where', 'Onett', 'password', 'ADMIN');