drop table if exists oauth_client_details;
create table oauth_client_details (client_id VARCHAR(256) PRIMARY KEY, resource_ids VARCHAR(256), client_secret VARCHAR(256), scope VARCHAR(256), authorized_grant_types VARCHAR(256), web_server_redirect_uri VARCHAR(256), authorities VARCHAR(256), access_token_validity INTEGER, refresh_token_validity INTEGER, additional_information VARCHAR(4096), autoapprove VARCHAR(256) );

drop table if exists oauth_client_token;
--create table oauth_client_token (token_id VARCHAR(255), token BYTEA, authentication_id VARCHAR(255) PRIMARY KEY, user_name VARCHAR(255), client_id VARCHAR(255) );

drop table if exists oauth_access_token; 
--create table oauth_access_token (token_id VARCHAR(255), token BYTEA, authentication_id VARCHAR(255) PRIMARY KEY, user_name VARCHAR(255), client_id VARCHAR(255), authentication BYTEA, refresh_token VARCHAR(255) );

drop table if exists oauth_refresh_token; 
--create table oauth_refresh_token (token_id VARCHAR(255), token BYTEA, authentication BYTEA );

drop table if exists oauth_code; 
--create table oauth_code (code VARCHAR(255), authentication BYTEA );

drop table if exists oauth_approvals;
--create table oauth_approvals (userId VARCHAR(255), clientId VARCHAR(255), scope VARCHAR(255), status VARCHAR(10), expiresAt TIMESTAMP, lastModifiedAt TIMESTAMP ); 

drop table if exists ClientDetails;
--create table ClientDetails (appId VARCHAR(255) PRIMARY KEY, resourceIds VARCHAR(255), appSecret VARCHAR(255), scope VARCHAR(255), grantTypes VARCHAR(255), redirectUrl VARCHAR(255), authorities VARCHAR(255), access_token_validity INTEGER, refresh_token_validity INTEGER, additionalInformation VARCHAR(4096), autoApproveScopes VARCHAR(255) );

--INSERT INTO oauth_client_details ( client_id , resource_ids , client_secret , scope , authorized_grant_types , web_server_redirect_uri , authorities , access_token_validity , refresh_token_validity , additional_information , autoapprove) VALUES ( 'myRestClient', 'todo-services', 'P@ssw0rd', 'read,write', 'client_credentials', '', 'role_admin', 120 , 600 , NULL , 'false');

--drop table if exists USERS_ROLES cascade;


--drop table if exists ROLES cascade;
--drop table if exists USERS cascade;
--CREATE TABLE USERS(	USERNAME VARCHAR(50) NOT NULL, PASSWORD VARCHAR(90) NOT NULL, ENABLED BOOLEAN NOT NULL DEFAULT TRUE, PRIMARY KEY(USERNAME));
--CREATE TABLE ROLES(USERNAME VARCHAR(50) NOT NULL, ROLE VARCHAR(50) NOT NULL, CONSTRAINT FK_ROLES_USERS FOREIGN KEY(USERNAME) REFERENCES USERS(USERNAME));
--INSERT INTO USERS(USERNAME,PASSWORD) VALUES('admin','admin');
--INSERT INTO ROLES(USERNAME,ROLE) VALUES('admin','ADMIN');


--INSERT INTO users(id, enabled, password, username)VALUES (nextval('users_seq'), true, 'admin', 'admin');
--INSERT INTO roles(id, username, role) VALUES (nextval('ROLES_SEQ'),'admin','ADMIN');
--INSERT INTO users_roles(user_id, role_id) VALUES ((select max(id) from users), (select max(id) from roles));