insert into account(username, password, active) values ('andres', 'bgd', true);
insert into account(username, password, active) values ('jaime', 'bgd', true);
insert into account(username, password, active) values ('pedro', 'bgd', true);
insert into account(username, password, active) values ('harvey', 'bgd', true);
insert into account(username, password, active) values ('nelson', 'bgd', true);

insert into client(client_id, secret, redirect_uri, authorized_grant_types, scopes, auto_approve_scopes, authorities) values('html5', 'password', 'http://localhost:8080/login', 'authorization_code,refresh_token,password', 'openid', '.*', 'ROLE_USER,ROLE_ADMIN');
--insert into client(client_id, secret, redirect_uri, authorized_grant_types, scopes, auto_approve_scopes, authorities) values('html5', 'password', 'http://localhost:8081/login', 'refresh_token,password', 'openid', '.*', 'ROLE_USER,ROLE_ADMIN');
insert into client(client_id, secret) values('android', 'secret');

