INSERT INTO tb_estado (name) VALUES('São Paulo');
INSERT INTO tb_estado (name) VALUES('Rio de Janeiro');

INSERT INTO tb_cidade (name, estado_id) VALUES('Paraguaçu Paulista', 1);
INSERT INTO tb_cidade (name, estado_id) VALUES('Cidade de Deus', 2);

INSERT INTO tb_role (authority) VALUES ('ROLE_OPERATOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user (name, email, cpf) VALUES ('José','email@random','cpfrandom');
INSERT INTO tb_user (name, email, cpf) VALUES ('Augusto','email1@random','cpfrandom2');

INSERT INTO tb_endereco (cidade_id, user_id, logradouro, numero, complemento, bairro, cep) VALUES (1, 1, 'Avenida Matos', '105', 'Sala 800', 'Centro', '38777012');
INSERT INTO tb_endereco (cidade_id, user_id, logradouro, numero, complemento, bairro, cep) VALUES (2, 2, 'Rua Flores', '360', 'apto 380', 'Jardim', '38222012');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);