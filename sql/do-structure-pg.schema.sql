CREATE DATABASE do
    WITH 
    OWNER = dongkap
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

CREATE SCHEMA security
    AUTHORIZATION dongkap;
CREATE SCHEMA master
    AUTHORIZATION dongkap;
CREATE SCHEMA workflow
    AUTHORIZATION dongkap;
CREATE SCHEMA "file"
    AUTHORIZATION dongkap;
CREATE SCHEMA notification
    AUTHORIZATION dongkap;
CREATE SCHEMA finance
    AUTHORIZATION dongkap;