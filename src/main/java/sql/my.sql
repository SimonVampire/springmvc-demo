-- auto-generated definition
CREATE TABLE userlogin
(
  userID   INT AUTO_INCREMENT
    PRIMARY KEY,
  userName VARCHAR(200)    NOT NULL,
  password VARCHAR(200)    NOT NULL,
  role     INT DEFAULT '2' NOT NULL
);

