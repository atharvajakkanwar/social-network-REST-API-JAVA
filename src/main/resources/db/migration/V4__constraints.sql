/* Schema
Engine: PostGreSQL
Version: 0.0.4
Description: Adding constraints

Author: Atharva
*/

/*
Structure
*/

ALTER TABLE users ADD CONSTRAINT uniqueemail UNIQUE (email);

ALTER TABLE membership
ADD CONSTRAINT fk
FOREIGN KEY (memberid)
REFERENCES users (userid)
ON DELETE CASCADE;
-- ALTER TABLE friends ALTER COLUMN useroneid