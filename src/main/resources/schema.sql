
CREATE TABLE IF NOT EXISTS member (
    id bigserial,
	member_name varchar(255) NOT NULL,
	PRIMARY KEY (id),
    UNIQUE (member_name)
);

CREATE TABLE IF NOT EXISTS pet (
    id bigserial,
	kind varchar(255),
	pet_name varchar(255),
	member_id bigint,
	PRIMARY KEY (id),
	UNIQUE (kind, pet_name),
    FOREIGN KEY (member_id) REFERENCES  member(id)
);
