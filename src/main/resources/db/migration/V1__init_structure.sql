CREATE SCHEMA conference_challenge;

CREATE TABLE conference (
    id IDENTITY PRIMARY KEY,
    name VARCHAR(500),
    start_date DATE,
    end_date DATE,
    seats INT,
    cancelled BOOLEAN DEFAULT false);

ALTER TABLE conference ADD CONSTRAINT CONFERENCE_UNIQUE UNIQUE(name);

CREATE TABLE participant (
      id IDENTITY PRIMARY KEY,
      username VARCHAR(500),
      password VARCHAR(100),
      active BOOLEAN DEFAULT true);

ALTER TABLE participant ADD CONSTRAINT USER_UNIQUE UNIQUE(username);

CREATE TABLE conference_participant (
     conference_id BIGINT NOT NULL,
     participant_id BIGINT NOT NULL,
     PRIMARY KEY(conference_id, participant_id));

ALTER TABLE conference_participant
    ADD FOREIGN KEY (conference_id)
        REFERENCES conference(id);

ALTER TABLE conference_participant
    ADD FOREIGN KEY (participant_id)
        REFERENCES participant(id);