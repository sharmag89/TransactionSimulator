create extension if not exists "uuid-ossp";

create database transaction_db;

\c transaction_db;

create schema if not exists master;

create role read_only_role;

grant connect on database transaction_db to read_only_role;

grant usage on schema master to read_only_role;

grant select on all tables in schema master to read_only_role;

alter default privileges in schema master grant select on tables to read_only_role;

create role read_write_role;

grant connect on database transaction_db to read_write_role;

grant usage, create on schema master to read_write_role;

grant select, insert, update, delete on all tables in schema master to read_write_role;

alter default privileges in schema master grant select,insert,update,delete on tables to read_write_role;

grant usage on all sequences in schema master to read_write_role;

alter default privileges in schema master grant usage on sequences to read_write_role;

create role admin_role;

grant all privileges on database transaction_db to admin_role;

create user transaction_admin with password 'password';

create user read_write_user with password 'password';

create user read_only_user with password 'password';

grant read_only_role to read_only_user;

grant read_write_role to read_write_user;

grant admin_role to transaction_admin;

