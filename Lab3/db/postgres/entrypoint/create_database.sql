CREATE SCHEMA exchange_rate_db AUTHORIZATION exchange_rate_db_user;

DROP TABLE if EXISTS exchange_rate CASCADE;
create table exchange_rate
(
    date  date primary key,
    rates jsonb
);


