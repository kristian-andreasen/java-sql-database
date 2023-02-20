DROP TABLE IF EXISTS Superhero;

CREATE TABLE Superhero(
	Id serial PRIMARY KEY,
	Name varChar(100),
	Alias varChar(100),
	Origin varChar(100)
);

DROP TABLE IF EXISTS Assistant;

CREATE TABLE Assistant(
	Id serial PRIMARY KEY,
	Name varChar(100)
);

DROP TABLE IF EXISTS Power;

CREATE TABLE Power(
	Id serial PRIMARY KEY,
	Name varChar(100),
	Description varChar(250)
);