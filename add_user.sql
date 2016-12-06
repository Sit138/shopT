INSERT INTO role (name_role) VALUES ('ROLE_ADMIN');
INSERT INTO user_account (user_name, user_password, user_enabled, role_id) VALUES ('admin', '123456', 'true', '1');
INSERT INTO role (name_role) VALUES ('ROLE_USER');
INSERT INTO user_account (user_name, user_password, user_enabled, role_id) VALUES ('user', '123456', 'true', '2');