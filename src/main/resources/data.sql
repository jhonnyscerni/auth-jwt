insert into tb_users (id, username, email, password, full_name, user_type, user_status, phone_number, cpf)
values (1, 'jhonnyscerni', 'jhonnyscerni@gmail.com', '$2a$10$5SAcyRvZdMGYnWu5pTriR.yS9GC6i2ff6r/dR3WU1cJBJbksXQ5/W',
        'Jhonny Scerni Gondim Costa', 'ADMIN', 'ACTIVE', '9999999999', '99999999999');
-- Modify initial value and increment
ALTER SEQUENCE seq_usuario RESTART WITH 2;

insert into tb_roles (id, name)
values (1, 'ROLE_ADMIN'),  (2, 'ROLE_USER');

-- Modify initial value and increment
ALTER SEQUENCE seq_role RESTART WITH 2;

insert into tb_users_roles(user_id, role_id)
values (1, 1);

insert into tb_permissions (id, name ,description) values (1, 'CONSULTAR_DASHBOARD', 'Permite consultar dashboard');
insert into tb_permissions (id, name, description) values (2, 'SEG_CONSULTAR_USUARIOS', 'Permite consultar usuarios');
insert into tb_permissions (id, name, description) values (3, 'SEG_CADASTRAR_USUARIOS', 'Permite cadastrar usuarios');
insert into tb_permissions (id, name, description) values (4, 'SEG_EDITAR_USUARIOS', 'Permite editar usuarios');
insert into tb_permissions (id, name, description) values (5, 'SEG_REMOVER_USUARIOS', 'Permite remover usuarios');
insert into tb_permissions (id, name, description) values (6, 'SEG_CONSULTAR_GRUPOS', 'Permite consultar grupos');
insert into tb_permissions (id, name, description) values (7, 'SEG_CADASTRAR_GRUPOS', 'Permite cadastrar grupos');
insert into tb_permissions (id, name, description) values (8, 'SEG_EDITAR_GRUPOS', 'Permite editar grupos');
insert into tb_permissions (id, name, description) values (9, 'SEG_REMOVER_GRUPOS', 'Permite remover grupos');
insert into tb_permissions (id, name, description) values (10, 'SEG_CONSULTAR_PERMISSOES', 'Permite consultar permissoes');
insert into tb_permissions (id, name, description) values (11, 'SEG_CADASTRAR_PERMISSOES', 'Permite cadastrar permissoes');
insert into tb_permissions (id, name, description) values (12, 'SEG_EDITAR_PERMISSOES', 'Permite editar permissoes');
insert into tb_permissions (id, name, description) values (13, 'SEG_REMOVER_PERMISSOES', 'Permite remover permissoes');
insert into tb_permissions (id, name, description) values (14, 'SEG_CONSULTAR_USUARIOS_GRUPOS', 'Permite consultar asossiações de usuario e grupo');
insert into tb_permissions (id, name, description) values (15, 'SEG_ASSOCIAR_USUARIOS_GRUPOS', 'Permite cadastrar asossiações de usuario e grupo');
insert into tb_permissions (id, name, description) values (16, 'SEG_DESASSOCIAR_USUARIOS_GRUPOS', 'Permite editar asossiações de usuario e grupo');
insert into tb_permissions (id, name, description) values (17, 'SEG_CONSULTAR_GRUPOS_PERMISSOES', 'Permite consultar asossiações de grupo e permissão');
insert into tb_permissions (id, name, description) values (18, 'SEG_ASSOCIAR_GRUPOS_PERMISSOES', 'Permite cadastrar asossiações de grupo e permissão');
insert into tb_permissions (id, name, description) values (19, 'SEG_DESASSOCIAR_GRUPOS_PERMISSOES', 'Permite editar asossiações de grupo e permissão');

-- Modify initial value and increment
ALTER SEQUENCE seq_permission RESTART WITH 20;

-- # Adiciona todas as permissoes no grupo
insert into tb_roles_permissions (role_id, permission_id) values (1 ,1);
insert into tb_roles_permissions (role_id, permission_id) values (1 ,2);
insert into tb_roles_permissions (role_id, permission_id) values (1 ,3);
insert into tb_roles_permissions (role_id, permission_id) values (1 ,4);
insert into tb_roles_permissions (role_id, permission_id) values (1 ,5);
insert into tb_roles_permissions (role_id, permission_id) values (1 ,6);
insert into tb_roles_permissions (role_id, permission_id) values (1 ,7);
insert into tb_roles_permissions (role_id, permission_id) values (1 ,8);
insert into tb_roles_permissions (role_id, permission_id) values (1 ,9);
insert into tb_roles_permissions (role_id, permission_id) values (1 ,10);
insert into tb_roles_permissions (role_id, permission_id) values (1 ,11);
insert into tb_roles_permissions (role_id, permission_id) values (1 ,12);
insert into tb_roles_permissions (role_id, permission_id) values (1 ,13);
insert into tb_roles_permissions (role_id, permission_id) values (1 ,14);
insert into tb_roles_permissions (role_id, permission_id) values (1 ,15);
insert into tb_roles_permissions (role_id, permission_id) values (1 ,16);
insert into tb_roles_permissions (role_id, permission_id) values (1 ,17);
insert into tb_roles_permissions (role_id, permission_id) values (1 ,18);
insert into tb_roles_permissions (role_id, permission_id) values (1 ,19);
