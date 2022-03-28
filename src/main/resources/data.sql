insert into tb_users (id, username, email, password, full_name, user_type, user_status, phone_number, cpf)
values (1, 'jhonnyscerni', 'jhonnyscerni@gmail.com', '$2a$10$5SAcyRvZdMGYnWu5pTriR.yS9GC6i2ff6r/dR3WU1cJBJbksXQ5/W',
        'Jhonny Scerni Gondim Costa', 'ADMIN', 'ACTIVE', '9999999999', '99999999999');

insert into tb_roles (role_id, role_name)
values (1, 'ROLE_ADMIN'),  (2, 'ROLE_USER');

insert into tb_users_roles(user_id, role_id)
values (1, 1);

insert into tb_permissions (permission_id, nome, descricao) values (1, 'CONSULTAR_DASHBOARD', 'Permite consultar dashboard');
insert into tb_permissions (permission_id, nome, descricao) values (2, 'SEG_CONSULTAR_USUARIOS', 'Permite consultar usuarios');

-- # Adiciona todas as permissoes no grupo
insert into tb_roles_permissions (role_id, permission_id)
select 1, permission_id from tb_roles_permissions;
select 2, permission_id from tb_roles_permissions;
insert into tb_roles_permissions (role_id, permission_id)