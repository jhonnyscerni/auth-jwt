insert into tb_person_physical (id, address_complement, address_district, address_nm_city, address_number, address_state,
                                address_street, address_zip_code, email, name, phone_number, vote, birth_date, cpf, gender,
                                observation, section_vote, surname, zone_voting, company_id, godfather_id)
values (1, 'complement','district', 'nm_city', 'number', 'state', 'street', '66615005', 'jhonnyscerni@gmail.com', 'Jhonny Scerni', '9999999', 'A_CONQUISTAR',
        null, '98473891287', 'MASCULINO', '', 'secao voto', 'scerni', 'zona votação', null, null);

ALTER SEQUENCE seq_person RESTART WITH 2;

insert into tb_users (id, username, password, user_status, person_id)
values (1, 'jhonnyscerni','$2a$10$5SAcyRvZdMGYnWu5pTriR.yS9GC6i2ff6r/dR3WU1cJBJbksXQ5/W', 'ACTIVE', 1);
-- Modify initial value and increment
ALTER SEQUENCE seq_usuario RESTART WITH 2;

insert into tb_roles (id, name)
values (1, 'ROLE_USUARIOS'),  (2, 'ROLE_PESSOAS'), (3, 'ROLE_EMPRESAS'), (4, 'ROLE_PERMISSOES'), (5, 'ROLE_GRUPOS'), (6, 'ROLE_DASHBOARD');


insert into tb_users_roles(user_id, role_id)
values (1, 1);
insert into tb_users_roles(user_id, role_id)
values (1, 2);
insert into tb_users_roles(user_id, role_id)
values (1, 3);
insert into tb_users_roles(user_id, role_id)
values (1, 4);
insert into tb_users_roles(user_id, role_id)
values (1, 5);
insert into tb_users_roles(user_id, role_id)
values (1, 6);

-- Modify initial value and increment
ALTER SEQUENCE seq_role RESTART WITH 7


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

insert into tb_permissions (id, name, description) values (14, 'SEG_CONSULTAR_PESSOAS', 'Permite consultar pessoas');
insert into tb_permissions (id, name, description) values (15, 'SEG_CADASTRAR_PESSOAS', 'Permite cadastrar pessoas');
insert into tb_permissions (id, name, description) values (16, 'SEG_EDITAR_PESSOAS', 'Permite editar pessoas');
insert into tb_permissions (id, name, description) values (17, 'SEG_REMOVER_PESSOAS', 'Permite remover pessoas');

insert into tb_permissions (id, name, description) values (18, 'SEG_CONSULTAR_EMPRESAS', 'Permite consultar empresas');
insert into tb_permissions (id, name, description) values (19, 'SEG_CADASTRAR_EMPRESAS', 'Permite cadastrar empresas');
insert into tb_permissions (id, name, description) values (20, 'SEG_EDITAR_EMPRESAS', 'Permite editar empresas');
insert into tb_permissions (id, name, description) values (21, 'SEG_REMOVER_EMPRESAS', 'Permite remover empresas');

--insert into tb_permissions (id, name, description) values (14, 'SEG_CONSULTAR_USUARIOS_GRUPOS', 'Permite consultar asossiações de usuario e grupo');
--insert into tb_permissions (id, name, description) values (15, 'SEG_ASSOCIAR_USUARIOS_GRUPOS', 'Permite cadastrar asossiações de usuario e grupo');
--insert into tb_permissions (id, name, description) values (16, 'SEG_DESASSOCIAR_USUARIOS_GRUPOS', 'Permite editar asossiações de usuario e grupo');
--insert into tb_permissions (id, name, description) values (17, 'SEG_CONSULTAR_GRUPOS_PERMISSOES', 'Permite consultar asossiações de grupo e permissão');
--insert into tb_permissions (id, name, description) values (18, 'SEG_ASSOCIAR_GRUPOS_PERMISSOES', 'Permite cadastrar asossiações de grupo e permissão');
--insert into tb_permissions (id, name, description) values (19, 'SEG_DESASSOCIAR_GRUPOS_PERMISSOES', 'Permite editar asossiações de grupo e permissão');

-- Modify initial value and increment
ALTER SEQUENCE seq_permission RESTART WITH 22;

-- # Adiciona todas as permissoes no grupo

insert into tb_roles_permissions (role_id, permission_id) values (1 ,2);
insert into tb_roles_permissions (role_id, permission_id) values (1 ,3);
insert into tb_roles_permissions (role_id, permission_id) values (1 ,4);
insert into tb_roles_permissions (role_id, permission_id) values (1 ,5);

insert into tb_roles_permissions (role_id, permission_id) values (5 ,6);
insert into tb_roles_permissions (role_id, permission_id) values (5 ,7);
insert into tb_roles_permissions (role_id, permission_id) values (5 ,8);
insert into tb_roles_permissions (role_id, permission_id) values (5 ,9);

insert into tb_roles_permissions (role_id, permission_id) values (4 ,10);
insert into tb_roles_permissions (role_id, permission_id) values (4 ,11);
insert into tb_roles_permissions (role_id, permission_id) values (4 ,12);
insert into tb_roles_permissions (role_id, permission_id) values (4 ,13);

insert into tb_roles_permissions (role_id, permission_id) values (2 ,14);
insert into tb_roles_permissions (role_id, permission_id) values (2 ,15);
insert into tb_roles_permissions (role_id, permission_id) values (2 ,16);
insert into tb_roles_permissions (role_id, permission_id) values (2 ,17);

insert into tb_roles_permissions (role_id, permission_id) values (3 ,18);
insert into tb_roles_permissions (role_id, permission_id) values (3 ,19);
insert into tb_roles_permissions (role_id, permission_id) values (3 ,20);
insert into tb_roles_permissions (role_id, permission_id) values (3 ,21);

insert into tb_roles_permissions (role_id, permission_id) values (6,1);
