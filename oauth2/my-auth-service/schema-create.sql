create table account (id bigint generated by default as identity, active boolean not null, password varchar(255), username varchar(255), primary key (id))
create table client (id bigint generated by default as identity, authorities varchar(255), authorized_grant_types varchar(255), auto_approve_scopes varchar(255), client_id varchar(255), redirect_uri varchar(255), scopes varchar(255), secret varchar(255), primary key (id))
