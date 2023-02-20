CREATE TABLE superheropower(
	hero_id int REFERENCES superhero,
	power_id int references power,
	PRIMARY KEY (hero_id,power_id)
	
)