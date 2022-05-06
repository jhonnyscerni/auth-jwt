
insert into tb_person_physical (id, address_complement, address_district, address_nm_city, address_number, address_state,
                                address_street, address_zip_code, email, name, phone_number, vote, birth_date, cpf, gender,
                                observation, section_vote, surname, zone_voting, user_id)
values ('257736fc-2fa4-4650-ad06-cd98964e346b', 'complement','district', 'nm_city', 'number', 'state', 'street', '66615005', 'jhonnyscerni@gmail.com', 'Jhonny Scerni', '9999999', 'A_CONQUISTAR',
        null, '98473891287', 'MASCULINO', '', 'secao voto', 'scerni', 'zona votação', null);


insert into tb_users (id, username, password, user_status, person_id)
values ('8360ec65-2c83-4274-b636-f12d8057d8f5', 'jhonnyscerni','$2a$10$5SAcyRvZdMGYnWu5pTriR.yS9GC6i2ff6r/dR3WU1cJBJbksXQ5/W', 'ACTIVE', '257736fc-2fa4-4650-ad06-cd98964e346b');
-- Modify initial value and increment
--ALTER SEQUENCE seq_usuario RESTART WITH 2;

insert into tb_roles (id, name)
values ('3f23b431-4071-4392-90b1-8e2ee214d351', 'ROLE_USUARIOS'),  ('e713fcc2-45d7-4f06-8d5e-14ecc1025293', 'ROLE_PESSOAS'), ('19261cd1-6c9f-43a2-ad17-1f44ee492247', 'ROLE_EMPRESAS'), ('21b8ac8f-3e2d-493e-bc9a-2a95648673f4', 'ROLE_PERMISSOES'), ('40072e3d-f728-44c9-a360-8365751c46a4', 'ROLE_GRUPOS'), ('911799e1-8d29-4853-8f97-0c133f44b766', 'ROLE_DASHBOARD');


insert into tb_users_roles(user_id, role_id)
values ('8360ec65-2c83-4274-b636-f12d8057d8f5', '3f23b431-4071-4392-90b1-8e2ee214d351');

insert into tb_users_roles(user_id, role_id)
values ('8360ec65-2c83-4274-b636-f12d8057d8f5', 'e713fcc2-45d7-4f06-8d5e-14ecc1025293');

insert into tb_users_roles(user_id, role_id)
values ('8360ec65-2c83-4274-b636-f12d8057d8f5', '19261cd1-6c9f-43a2-ad17-1f44ee492247');

insert into tb_users_roles(user_id, role_id)
values ('8360ec65-2c83-4274-b636-f12d8057d8f5', '21b8ac8f-3e2d-493e-bc9a-2a95648673f4');

insert into tb_users_roles(user_id, role_id)
values ('8360ec65-2c83-4274-b636-f12d8057d8f5', '40072e3d-f728-44c9-a360-8365751c46a4');

insert into tb_users_roles(user_id, role_id)
values ('8360ec65-2c83-4274-b636-f12d8057d8f5', '911799e1-8d29-4853-8f97-0c133f44b766');


-- Modify initial value and increment
--ALTER SEQUENCE seq_role RESTART WITH 7


insert into tb_permissions (id, name ,description) values ('adca9387-b982-44fa-89da-ba9608f7f2f3', 'CONSULTAR_DASHBOARD', 'Permite consultar dashboard');

insert into tb_permissions (id, name, description) values ('a3bafff7-c670-4b3e-9ffd-cdadcec19116', 'SEG_CONSULTAR_USUARIOS', 'Permite consultar usuarios');
insert into tb_permissions (id, name, description) values ('4e3b6666-86b9-459b-8ad5-a254261ed2ac', 'SEG_CADASTRAR_USUARIOS', 'Permite cadastrar usuarios');
insert into tb_permissions (id, name, description) values ('ef5046c4-47fa-47aa-8d9e-09d8ce6daaf5', 'SEG_EDITAR_USUARIOS', 'Permite editar usuarios');
insert into tb_permissions (id, name, description) values ('71e3cb61-19bb-48ad-a8db-bfd9c14cb1c4', 'SEG_REMOVER_USUARIOS', 'Permite remover usuarios');

insert into tb_permissions (id, name, description) values ('b2116eca-cd38-4c47-aa7c-a49bf29a2771', 'SEG_CONSULTAR_GRUPOS', 'Permite consultar grupos');
insert into tb_permissions (id, name, description) values ('0ac24163-2196-4c14-b1e6-4cad3b3f9307', 'SEG_CADASTRAR_GRUPOS', 'Permite cadastrar grupos');
insert into tb_permissions (id, name, description) values ('07b946ff-edc5-43d9-8593-17a2a16ee8ac', 'SEG_EDITAR_GRUPOS', 'Permite editar grupos');
insert into tb_permissions (id, name, description) values ('09afbdfb-533a-405b-afc8-556691f361b1', 'SEG_REMOVER_GRUPOS', 'Permite remover grupos');

insert into tb_permissions (id, name, description) values ('56a1d3e0-b872-489c-82a1-80307311e231', 'SEG_CONSULTAR_PERMISSOES', 'Permite consultar permissoes');
insert into tb_permissions (id, name, description) values ('d04137d0-e4f8-465a-8f20-c590f2218166', 'SEG_CADASTRAR_PERMISSOES', 'Permite cadastrar permissoes');
insert into tb_permissions (id, name, description) values ('1443f0f7-6376-4c71-a3fa-97e42de45dd1', 'SEG_EDITAR_PERMISSOES', 'Permite editar permissoes');
insert into tb_permissions (id, name, description) values ('0750a4a2-ad6f-438f-a34e-adeef01744ad', 'SEG_REMOVER_PERMISSOES', 'Permite remover permissoes');

insert into tb_permissions (id, name, description) values ('077e9294-6540-49db-9e96-639b722ca10b', 'SEG_CONSULTAR_PESSOAS', 'Permite consultar pessoas');
insert into tb_permissions (id, name, description) values ('aa4a09a7-8bc4-4841-8c12-ec16fcb2b71b', 'SEG_CADASTRAR_PESSOAS', 'Permite cadastrar pessoas');
insert into tb_permissions (id, name, description) values ('56c91e6d-4cec-4ad4-b06b-88f3b18c53a4', 'SEG_EDITAR_PESSOAS', 'Permite editar pessoas');
insert into tb_permissions (id, name, description) values ('5d136b54-97f6-4792-a7ef-fd0ddb2010a3', 'SEG_REMOVER_PESSOAS', 'Permite remover pessoas');

insert into tb_permissions (id, name, description) values ('2a1d21c4-7156-47ed-a4e2-e5fc011426d3', 'SEG_CONSULTAR_EMPRESAS', 'Permite consultar empresas');
insert into tb_permissions (id, name, description) values ('61b31f0f-8a59-4ed4-8637-c3da77b8c20e', 'SEG_CADASTRAR_EMPRESAS', 'Permite cadastrar empresas');
insert into tb_permissions (id, name, description) values ('c68dc9ec-a09c-42a4-b139-84d36fc80e31', 'SEG_EDITAR_EMPRESAS', 'Permite editar empresas');
insert into tb_permissions (id, name, description) values ('4e87ec48-f79a-4b9c-8043-b9b80fe8a51f', 'SEG_REMOVER_EMPRESAS', 'Permite remover empresas');

--insert into tb_permissions (id, name, description) values (14, 'SEG_CONSULTAR_USUARIOS_GRUPOS', 'Permite consultar asossiações de usuario e grupo');
--insert into tb_permissions (id, name, description) values (15, 'SEG_ASSOCIAR_USUARIOS_GRUPOS', 'Permite cadastrar asossiações de usuario e grupo');
--insert into tb_permissions (id, name, description) values (16, 'SEG_DESASSOCIAR_USUARIOS_GRUPOS', 'Permite editar asossiações de usuario e grupo');
--insert into tb_permissions (id, name, description) values (17, 'SEG_CONSULTAR_GRUPOS_PERMISSOES', 'Permite consultar asossiações de grupo e permissão');
--insert into tb_permissions (id, name, description) values (18, 'SEG_ASSOCIAR_GRUPOS_PERMISSOES', 'Permite cadastrar asossiações de grupo e permissão');
--insert into tb_permissions (id, name, description) values (19, 'SEG_DESASSOCIAR_GRUPOS_PERMISSOES', 'Permite editar asossiações de grupo e permissão');

-- Modify initial value and increment
--ALTER SEQUENCE seq_permission RESTART WITH 22;

-- # Adiciona todas as permissoes no grupo

insert into tb_roles_permissions (role_id, permission_id) values ('3f23b431-4071-4392-90b1-8e2ee214d351' ,'a3bafff7-c670-4b3e-9ffd-cdadcec19116');
insert into tb_roles_permissions (role_id, permission_id) values ('3f23b431-4071-4392-90b1-8e2ee214d351' ,'4e3b6666-86b9-459b-8ad5-a254261ed2ac');
insert into tb_roles_permissions (role_id, permission_id) values ('3f23b431-4071-4392-90b1-8e2ee214d351' ,'ef5046c4-47fa-47aa-8d9e-09d8ce6daaf5');
insert into tb_roles_permissions (role_id, permission_id) values ('3f23b431-4071-4392-90b1-8e2ee214d351' ,'71e3cb61-19bb-48ad-a8db-bfd9c14cb1c4');

insert into tb_roles_permissions (role_id, permission_id) values ('40072e3d-f728-44c9-a360-8365751c46a4' ,'b2116eca-cd38-4c47-aa7c-a49bf29a2771');
insert into tb_roles_permissions (role_id, permission_id) values ('40072e3d-f728-44c9-a360-8365751c46a4' ,'0ac24163-2196-4c14-b1e6-4cad3b3f9307');
insert into tb_roles_permissions (role_id, permission_id) values ('40072e3d-f728-44c9-a360-8365751c46a4' ,'07b946ff-edc5-43d9-8593-17a2a16ee8ac');
insert into tb_roles_permissions (role_id, permission_id) values ('40072e3d-f728-44c9-a360-8365751c46a4' ,'09afbdfb-533a-405b-afc8-556691f361b1');

insert into tb_roles_permissions (role_id, permission_id) values ('21b8ac8f-3e2d-493e-bc9a-2a95648673f4' ,'56a1d3e0-b872-489c-82a1-80307311e231');
insert into tb_roles_permissions (role_id, permission_id) values ('21b8ac8f-3e2d-493e-bc9a-2a95648673f4' ,'d04137d0-e4f8-465a-8f20-c590f2218166');
insert into tb_roles_permissions (role_id, permission_id) values ('21b8ac8f-3e2d-493e-bc9a-2a95648673f4' ,'1443f0f7-6376-4c71-a3fa-97e42de45dd1');
insert into tb_roles_permissions (role_id, permission_id) values ('21b8ac8f-3e2d-493e-bc9a-2a95648673f4' ,'0750a4a2-ad6f-438f-a34e-adeef01744ad');

insert into tb_roles_permissions (role_id, permission_id) values ('e713fcc2-45d7-4f06-8d5e-14ecc1025293','077e9294-6540-49db-9e96-639b722ca10b');
insert into tb_roles_permissions (role_id, permission_id) values ('e713fcc2-45d7-4f06-8d5e-14ecc1025293' ,'aa4a09a7-8bc4-4841-8c12-ec16fcb2b71b');
insert into tb_roles_permissions (role_id, permission_id) values ('e713fcc2-45d7-4f06-8d5e-14ecc1025293' ,'56c91e6d-4cec-4ad4-b06b-88f3b18c53a4');
insert into tb_roles_permissions (role_id, permission_id) values ('e713fcc2-45d7-4f06-8d5e-14ecc1025293' ,'5d136b54-97f6-4792-a7ef-fd0ddb2010a3');

insert into tb_roles_permissions (role_id, permission_id) values ('19261cd1-6c9f-43a2-ad17-1f44ee492247' ,'2a1d21c4-7156-47ed-a4e2-e5fc011426d3');
insert into tb_roles_permissions (role_id, permission_id) values ('19261cd1-6c9f-43a2-ad17-1f44ee492247' ,'61b31f0f-8a59-4ed4-8637-c3da77b8c20e');
insert into tb_roles_permissions (role_id, permission_id) values ('19261cd1-6c9f-43a2-ad17-1f44ee492247' ,'c68dc9ec-a09c-42a4-b139-84d36fc80e31');
insert into tb_roles_permissions (role_id, permission_id) values ('19261cd1-6c9f-43a2-ad17-1f44ee492247' ,'4e87ec48-f79a-4b9c-8043-b9b80fe8a51f');

insert into tb_roles_permissions (role_id, permission_id) values ('911799e1-8d29-4853-8f97-0c133f44b766','adca9387-b982-44fa-89da-ba9608f7f2f3');