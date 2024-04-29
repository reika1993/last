DROP TABLE IF EXISTS cats;

CREATE TABLE cats(
  id int unsigned AUTO_INCREMENT,
  name VARCHAR(10) NOT NULL,
  sex VARCHAR(10) NOT NULL,
  age INTEGER NOT NULL,
  PRIMARY KEY(id)
);

INSERT INTO cats(name,sex,age) VALUES("Omochi","female",2);
INSERT INTO cats(name,sex,age) VALUES("Coa","male",3);
INSERT INTO cats(name,sex,age) VALUES("Gonchi","male",5);
