insert into tb_users (id, username, email, password, full_name, user_type, user_status, phone_number, cpf)
values (1, 'jhonnyscerni', 'jhonnyscerni@gmail.com', '$2y$12$NSsM4gEOR7MKogflKR7GMeYugkttjNhAJMvFdHrBLaLp2HzlggP5W',
        'Jhonny Scerni Gondim Costa', 'ADMIN', 'ACTIVE', '9999999999', '99999999999');

insert into tb_roles (role_id, role_name)
values (1, 'ROLE_ADMIN');

insert into tb_users_roles(user_id, role_id)
values (1, 1);