insert into account(username, password, active) values ('andres', '$2a$10$ZA.vZq53/Y861rq.uprb0uWviMc.RhdztyUJarix2phx5vOCUN5l6', true);
insert into account(username, password, active) values ('jaime', '$2a$10$ZA.vZq53/Y861rq.uprb0uWviMc.RhdztyUJarix2phx5vOCUN5l6', true);
insert into account(username, password, active) values ('pedro', '$2a$10$ZA.vZq53/Y861rq.uprb0uWviMc.RhdztyUJarix2phx5vOCUN5l6', true);
insert into account(username, password, active) values ('harvey', '$2a$10$ZA.vZq53/Y861rq.uprb0uWviMc.RhdztyUJarix2phx5vOCUN5l6', true);
insert into account(username, password, active) values ('nelson', '$2a$10$ZA.vZq53/Y861rq.uprb0uWviMc.RhdztyUJarix2phx5vOCUN5l6', true);

insert into client(client_id, secret, redirect_uri, authorized_grant_types, scopes, auto_approve_scopes, authorities) values('html5', 'password', 'http://localhost:8080/login', 'authorization_code,refresh_token,password', 'openid', '.*', 'ROLE_USER,ROLE_ADMIN');
--insert into client(client_id, secret, redirect_uri, authorized_grant_types, scopes, auto_approve_scopes, authorities) values('html5', 'password', 'http://localhost:8081/login', 'refresh_token,password', 'openid', '.*', 'ROLE_USER,ROLE_ADMIN');
insert into client(client_id, secret) values('android', 'secret');

