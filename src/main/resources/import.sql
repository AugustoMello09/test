INSERT INTO tb_estado (name) VALUES('São Paulo');
INSERT INTO tb_estado (name) VALUES('Rio de Janeiro');

INSERT INTO tb_cidade (name, estado_id) VALUES('Paraguaçu Paulista', 1);
INSERT INTO tb_cidade (name, estado_id) VALUES('Cidade de Deus', 2);

INSERT INTO tb_role (authority) VALUES ('ROLE_OPERATOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user (name, email, cpf, password) VALUES ('José','jose@gmail.com','cpfrandom', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, cpf, password) VALUES ('Augusto','augusto@gmail.com','cpfrandom','$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_endereco (cidade_id, user_id, logradouro, numero, complemento, bairro, cep) VALUES (1, 1, 'Avenida Matos', '105', 'Sala 800', 'Centro', '38777012');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);

INSERT INTO tb_categoria (nome_Categoria) VALUES('Ação');	
INSERT INTO tb_categoria (nome_Categoria) VALUES('Aventura');	
INSERT INTO tb_categoria (nome_Categoria) VALUES('Drama');


	



