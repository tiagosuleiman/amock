drop table if exists oauth_client_details;
create table oauth_client_details (
    client_id VARCHAR(256) PRIMARY KEY,
    resource_ids VARCHAR(256),
    client_secret VARCHAR(256),
    scope VARCHAR(256),
    authorized_grant_types VARCHAR(256),
    web_server_redirect_uri VARCHAR(256),
    authorities VARCHAR(256),
    access_token_validity INTEGER,
    refresh_token_validity INTEGER,
    additional_information VARCHAR(4096),
    autoapprove VARCHAR(256)
);

drop table if exists oauth_client_token;
create table oauth_client_token (
  token_id VARCHAR(255),
  token BYTEA,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255)
);
 
drop table if exists oauth_access_token;
create table oauth_access_token (
  token_id VARCHAR(255),
  token BYTEA,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication BYTEA,
  refresh_token VARCHAR(255)
);
 
drop table if exists oauth_refresh_token;
create table oauth_refresh_token (
  token_id VARCHAR(255),
  token BYTEA,
  authentication BYTEA
);
 
drop table if exists oauth_code;
create table oauth_code (
  code VARCHAR(255), authentication BYTEA
);
 
drop table if exists oauth_approvals;
create table oauth_approvals (
    userId VARCHAR(255),
    clientId VARCHAR(255),
    scope VARCHAR(255),
    status VARCHAR(10),
    expiresAt TIMESTAMP,
    lastModifiedAt TIMESTAMP
);
 
drop table if exists ClientDetails;
create table ClientDetails (
  appId VARCHAR(255) PRIMARY KEY,
  resourceIds VARCHAR(255),
  appSecret VARCHAR(255),
  scope VARCHAR(255),
  grantTypes VARCHAR(255),
  redirectUrl VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4096),
  autoApproveScopes VARCHAR(255)
);

INSERT INTO oauth_client_details
      ( client_id
      , resource_ids
      , client_secret
      , scope
      , authorized_grant_types
      , web_server_redirect_uri
      , authorities
      , access_token_validity
      , refresh_token_validity
      , additional_information
      , autoapprove)
VALUES
    ( 'myRestClient'
    , 'todo-services'
    , 'P@ssw0rd'
    , 'read,write'
    , 'client_credentials'
    , ''
    , 'role_admin'
    , 7200
    , 0
    , NULL
    , 'false');


select * from oauth_client_details;
select * from oauth_client_token;
select * from oauth_access_token;
select * from oauth_refresh_token;
select * from oauth_code;
select * from oauth_approvals;
select * from ClientDetails;


--=======================================

CREATE  TABLE users (
  username VARCHAR(45) NOT NULL ,
  password VARCHAR(45) NOT NULL ,
  enabled TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (username));
   
CREATE TABLE user_roles (
  user_role_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  UNIQUE KEY uni_username_role (role,username),
  KEY fk_username_idx (username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username));
 
INSERT INTO users(username,password,enabled) VALUES ('tiago','tiago', true);
INSERT INTO users(username,password,enabled) VALUES ('suleiman','suleiman', true);
 
INSERT INTO user_roles (username, role) VALUES ('tiago', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('tiago', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role) VALUES ('suleiman', 'ROLE_USER');