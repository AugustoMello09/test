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

INSERT INTO tb_categoria (nome_Categoria) VALUES('Ação');	
INSERT INTO tb_categoria (nome_Categoria) VALUES('Aventura');	
INSERT INTO tb_categoria (nome_Categoria) VALUES('Drama');

INSERT INTO tb_estoque (qtd, status) VALUES(10, 0);
INSERT INTO tb_estoque (qtd, status) VALUES(10, 0);
INSERT INTO tb_estoque (qtd, status) VALUES(10, 0);	

INSERT INTO tb_filme (nome, descricao, diretor, categoria_id, estoque_id) VALUES('Rush - No Limite da Emoção', 'Mussum Ipsum, cacilds vidis litro abertis. Vehicula non. Ut sed ex eros. Vivamus sit amet nibh non tellus tristique interdum.Não sou faixa preta cumpadi, sou preto inteiris, inteiris.Praesent vel viverra nisi. Mauris aliquet nunc non turpis scelerisque, eget.Si num tem leite então bota uma pinga aí cumpadi!', 'Ron Howard', 1, 1);
INSERT INTO tb_filme (nome, descricao, diretor, categoria_id, estoque_id) VALUES('Interestelar', 'Mussum Ipsum, cacilds vidis litro abertis. Vehicula non. Ut sed ex eros. Vivamus sit amet nibh non tellus tristique interdum.Não sou faixa preta cumpadi, sou preto inteiris, inteiris.Praesent vel viverra nisi. Mauris aliquet nunc non turpis scelerisque, eget.Si num tem leite então bota uma pinga aí cumpadi!', 'Christopher Nolan', 2, 2);
INSERT INTO tb_filme (nome, descricao, diretor, categoria_id, estoque_id) VALUES('Um Lugar Secreto', 'Mussum Ipsum, cacilds vidis litro abertis. Vehicula non. Ut sed ex eros. Vivamus sit amet nibh non tellus tristique interdum.Não sou faixa preta cumpadi, sou preto inteiris, inteiris.Praesent vel viverra nisi. Mauris aliquet nunc non turpis scelerisque, eget.Si num tem leite então bota uma pinga aí cumpadi!', 'Pascual Sisto', 3, 3);