set foreign_key_checks = 0;

delete from customer_table;
delete from user_entity_table;
delete from group_entity_table;
delete from permission_entity_table;
delete from group_permission;
delete from user_group;
delete from oauth2_registered_client;

set foreign_key_checks = 1;

INSERT INTO customer_table (id, name, payment) VALUES (1,'Joao', 10000.00);
INSERT INTO customer_table (id, name, payment) VALUES (2,'Maria', 15000.00);
INSERT INTO customer_table (id, name, payment) VALUES (3,'Jose', 30000.00);
INSERT INTO customer_table (id, name, payment) VALUES (4,'Alex', 30000.00);
INSERT INTO customer_table (id, name, payment) VALUES (5,'Sandra', 30000.00);
INSERT INTO customer_table (id, name, payment) VALUES (6,'Joseph', 30000.00);
INSERT INTO customer_table (id, name, payment) VALUES (7,'Kakaroto', 30000.00);
INSERT INTO customer_table (id, name, payment) VALUES (9,'Andre', 30000.00);
INSERT INTO customer_table (id, name, payment) VALUES (10,'Livia', 30000.00);
INSERT INTO customer_table (id, name, payment) VALUES (11,'Ivan', 30000.00);

insert into user_entity_table (id, name, email, password, date_create) values
(1,'João da Silva', 'joao.ger@algafood.com.br', '$2y$12$NSsM4gEOR7MKogflKR7GMeYugkttjNhAJMvFdHrBLaLp2HzlggP5W', utc_timestamp),
(2,'Maria Joaquina', 'maria.vnd@algafood.com.br', '$2y$12$NSsM4gEOR7MKogflKR7GMeYugkttjNhAJMvFdHrBLaLp2HzlggP5W', utc_timestamp),
(3,'José Souza', 'jose.aux@algafood.com.br', '$2y$12$NSsM4gEOR7MKogflKR7GMeYugkttjNhAJMvFdHrBLaLp2HzlggP5W', utc_timestamp),
(4,'Sebastião Martins', 'sebastiao.cad@algafood.com.br', '$2y$12$NSsM4gEOR7MKogflKR7GMeYugkttjNhAJMvFdHrBLaLp2HzlggP5W', utc_timestamp),
(5,'Manoel Lima', 'manoel.loja@gmail.com', '$2y$12$NSsM4gEOR7MKogflKR7GMeYugkttjNhAJMvFdHrBLaLp2HzlggP5W', utc_timestamp),
(6,'Débora Mendonça', 'email.teste.aw+debora@gmail.com', '$2y$12$NSsM4gEOR7MKogflKR7GMeYugkttjNhAJMvFdHrBLaLp2HzlggP5W', utc_timestamp),
(7,'Carlos Lima', 'email.teste.aw+carlos@gmail.com', '$2y$12$NSsM4gEOR7MKogflKR7GMeYugkttjNhAJMvFdHrBLaLp2HzlggP5W', utc_timestamp);
--
insert into group_entity_table (id, name) values (1,'Gerente'), (2,'Vendedor'), (3,'Secretária'), (4,'Cadastrador');
--
insert into permission_entity_table (id, name, description) values (1,'EDITAR_CUSTOMERS', 'Permite editar customers');
insert into permission_entity_table (id, name, description) values (2,'EDITAR_FORMAS_PAGAMENTO', 'Permite criar ou editar formas de pagamento');
insert into permission_entity_table (id, name, description) values (3,'EDITAR_CIDADES', 'Permite criar ou editar cidades');
insert into permission_entity_table (id, name, description) values (4,'EDIT_CUSTOMERS', 'Allow create or edit customers');
insert into permission_entity_table (id, name, description) values (5,'CONSULTAR_USUARIOS_GRUPOS_PERMISSOES', 'Permite consultar usuários, grupos e permissões');
insert into permission_entity_table (id, name, description) values (6,'EDITAR_USUARIOS_GRUPOS_PERMISSOES', 'Permite criar ou editar usuários, grupos e permissões');
insert into permission_entity_table (id, name, description) values (7,'EDITAR_RESTAURANTES', 'Permite criar, editar ou gerenciar restaurantes');
insert into permission_entity_table (id, name, description) values (8,'CONSULTAR_PEDIDOS', 'Permite consultar pedidos');
insert into permission_entity_table (id, name, description) values (9,'GERENCIAR_PEDIDOS', 'Permite gerenciar pedidos');
insert into permission_entity_table (id, name, description) values (10,'GERAR_RELATORIOS', 'Permite gerar relatórios');

insert into group_permission (group_id, permission_id)
select 1, id from permission_entity_table;

insert into group_permission (group_id, permission_id)
select 2, id from permission_entity_table where name like 'CONSULTAR_%';

insert into group_permission (group_id, permission_id)
select 2, id from permission_entity_table where name = 'EDITAR_CUSTOMERS';

insert into group_permission (group_id, permission_id)
select 3, id from permission_entity_table where name like 'CONSULTAR_%';

insert into group_permission (group_id, permission_id)
select 4, id from permission_entity_table where name like '%_CUSTOMERS';

insert into oauth2_registered_client
(id, client_id, client_id_issued_at, client_secret, client_secret_expires_at, client_name, client_authentication_methods, authorization_grant_types, redirect_uris, scopes, client_settings, token_settings)
VALUES('1', 'customer-backend', '2022-11-29 18:58:12', '$2a$10$trk401po.Wx9JXXMs2xCFeB.eXU7qENFquETcr04a0hDJxGV3ge0.', NULL, 'Customer Backend', 'client_secret_basic', 'client_credentials', '', 'READ,WRITE', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":false}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",1800.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",3600.000000000],"settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000]}');

insert into oauth2_registered_client
(id, client_id, client_id_issued_at, client_secret, client_secret_expires_at, client_name, client_authentication_methods, authorization_grant_types, redirect_uris, scopes, client_settings, token_settings)
VALUES('2', 'customer-web', '2022-11-29 18:58:12', '$2a$10$/Lx1cVKanXiCkpYtdA369OZ78x8aHwx51RTxC.4pqEiuZRzQh0e/i', NULL, 'Customer Web', 'client_secret_basic', 'refresh_token,authorization_code', 'http://127.0.0.1:8080/swagger-ui/oauth2-redirect.html,http://127.0.0.1:8080/authorized', 'READ,WRITE', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":true}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":false,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",900.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",86400.000000000],"settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000]}');

insert into oauth2_registered_client
(id, client_id, client_id_issued_at, client_secret, client_secret_expires_at, client_name, client_authentication_methods, authorization_grant_types, redirect_uris, scopes, client_settings, token_settings)
VALUES('3', 'customeranalytics', '2022-11-29 18:58:12', '$2a$10$LQOU54Ta7zV7TxTXSk7DEeZUx/P9PwKGH5CTIOLNGWgIP29QHdq4K', NULL, 'Customer Analytics', 'client_secret_basic', 'authorization_code', 'http://www.foodanalytics.local:8082', 'READ,WRITE', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":false}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",1800.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",3600.000000000],"settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000]}');