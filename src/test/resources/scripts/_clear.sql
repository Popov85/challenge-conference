delete from conference_participant;
delete from conference;
delete from participant;
delete from department;


ALTER TABLE conference ALTER COLUMN id RESTART WITH 1;
ALTER TABLE participant ALTER COLUMN id RESTART WITH 1;
ALTER TABLE department ALTER COLUMN id RESTART WITH 1;