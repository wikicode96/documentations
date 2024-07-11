INSERT INTO users (id, username, password, is_enable, account_no_expired, account_no_locked, credential_no_expired) VALUES (1, 'usuario-ejemplo', '$2a$12$2KodIfbqbTypNdx2VMpoze9vLavz/USqhYmtflQuxwqx9rDnVH7NW', true, true, true, true);
INSERT INTO roles (id, role_name) VALUES (1, 'ADMIN');
INSERT INTO permissions (id, name) VALUES (1, 'ALL');

INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO role_permission (role_id, permission_id) VALUES (1, 1);