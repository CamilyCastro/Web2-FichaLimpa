delete from partido;

insert into partido (sigla, nome, numero) values ('MDB', 'MOVIMENTO DEMOCRÁTICO BRASILEIRO', '15');
insert into partido (sigla, nome, numero) values ('PDT', 'PARTIDO DEMOCRÁTICO TRABALHISTA', '12');
insert into partido (sigla, nome, numero) values ('PT', 'PARTIDO DOS TRABALHADORES', '13');
insert into partido (sigla, nome, numero) values ('PCdoB', 'PARTIDO COMUNISTA DO BRASIL', '65');
insert into partido (sigla, nome, numero) values ('PSB', 'PARTIDO SOCIALISTA BRASILEIRO', '40');
insert into partido (sigla, nome, numero) values ('PSDB', 'PARTIDO DA SOCIAL DEMOCRACIA BRASILEIRA', '45');
insert into partido (sigla, nome, numero) values ('AGIR', 'AGIR', '36');
insert into partido (sigla, nome, numero) values ('MOBILIZA', 'Mobilização Nacional', '33');
insert into partido (sigla, nome, numero) values ('CIDADANIA', 'CIDADANIA', '23');
insert into partido (sigla, nome, numero) values ('PV', 'PARTIDO VERDE', '43');
insert into partido (sigla, nome, numero) values ('AVANTE', 'AVANTE', '70');
insert into partido (sigla, nome, numero) values ('PP', 'PROGRESSISTAS', '11');
insert into partido (sigla, nome, numero) values ('PSTU', 'PARTIDO SOCIALISTA DOS TRABALHADORES UNIFICADO', '16');
insert into partido (sigla, nome, numero) values ('PCB', 'PARTIDO COMUNISTA BRASILEIRO', '21');
insert into partido (sigla, nome, numero) values ('PRTB', 'PARTIDO RENOVADOR TRABALHISTA BRASILEIRO', '28');
insert into partido (sigla, nome, numero) values ('DC', 'DEMOCRACIA CRISTÃ', '27');
insert into partido (sigla, nome, numero) values ('PCO', 'PARTIDO DA CAUSA OPERÁRIA', '29');
insert into partido (sigla, nome, numero) values ('PODE', 'PODEMOS', '20');
insert into partido (sigla, nome, numero) values ('REP', 'REPUBLICANOS', '10');
insert into partido (sigla, nome, numero) values ('PSOL', 'PARTIDO SOCIALISMO E LIBERDADE', '50');
insert into partido (sigla, nome, numero) values ('PL', 'PARTIDO LIBERAL', '22');
insert into partido (sigla, nome, numero) values ('PSD', 'PARTIDO SOCIAL DEMOCRÁTICO', '55');
insert into partido (sigla, nome, numero) values ('SOLI', 'SOLIDARIEDADE', '77');
insert into partido (sigla, nome, numero) values ('NOVO', 'PARTIDO NOVO', '30');
insert into partido (sigla, nome, numero) values ('REDE', 'REDE SUSTENTABILIDADE', '18');
insert into partido (sigla, nome, numero) values ('PMB', 'PARTIDO DA MULHER BRASILEIRA', '35');
insert into partido (sigla, nome, numero) values ('UP', 'UNIDADE POPULAR', '80');
insert into partido (sigla, nome, numero) values ('UNIÃO', 'UNIÃO BRASIL', '44');
insert into partido (sigla, nome, numero) values ('PRD', 'PARTIDO RENOVAÇÃO DEMOCRÁTICA', '25');

delete from politico;
insert into politico (nome, cargo, nome_eleitoral, id_partido, numero_eleitoral, registro_candidatura, data_nascimento, nota, contato) values
('Maria Silva', 'SENADOR', 'MariaSenadora', 1, '1010', '202201', '1980-05-15', 4.5, 'maria.silva@email.com');

insert into politico (nome, cargo, nome_eleitoral, id_partido, numero_eleitoral, registro_candidatura, data_nascimento, nota, contato) values
('Carlos Oliveira', 'DEPUTADO FEDERAL', 'CarlosOliveira', 2, '2020', '202202', '1985-09-20', 3.8, 'carlos.oliveira@email.com');

insert into politico (nome, cargo, nome_eleitoral, id_partido, numero_eleitoral, registro_candidatura, data_nascimento, nota, contato) values
('Ana Pereira', 'GOVERNADOR', 'AnaPereiraGov', 3, '3030', '202203', '1975-12-12', 4.9, 'ana.pereira@email.com');

insert into politico (nome, cargo, nome_eleitoral, id_partido, numero_eleitoral, registro_candidatura, data_nascimento, nota, contato) values
('João Santos', 'VEREADOR', 'JoaoSantos', 4, '4040', '202204', '1990-03-25', 4.2, 'joao.santos@email.com');

insert into politico (nome, cargo, nome_eleitoral, id_partido, numero_eleitoral, registro_candidatura, data_nascimento, nota, contato) values
('Laura Lima', 'PRESIDENTE', 'LauraPresidente', 5, '5050', '202205', '1978-07-07', 4.7, 'laura.lima@email.com');

delete from cidadao;
insert into cidadao (cpf, telefone, nome, data_nascimento) values
('123.456.789-00', '(11)91234-5678', 'Pedro Almeida', '1985-01-01');

insert into cidadao (cpf, telefone, nome, data_nascimento) values
('987.654.321-00', '(21)99876-5432', 'Fernanda Souza', '1992-02-15');

insert into cidadao (cpf, telefone, nome, data_nascimento) values
('456.123.789-00', '(31)91234-5678', 'Roberto Costa', '1975-03-10');

insert into cidadao (cpf, telefone, nome, data_nascimento) values
('321.987.654-00', '(41)93456-7890', 'Camila Fernandes', '1980-05-20');

insert into cidadao (cpf, telefone, nome, data_nascimento) values
('789.123.456-00', '(51)94321-5678', 'Ricardo Lima', '1990-07-25');

delete from endereco;
insert into endereco (cep, logradouro, numero, cidade, estado, complemento, cidadao) values
('01001-000', 'Avenida Paulista', '1000', 'São Paulo', 'SP', 'Apartamento 101', 1);

insert into endereco (cep, logradouro, numero, cidade, estado, complemento, cidadao) values
('20040-000', 'Rua do Ouvidor', '200', 'Rio de Janeiro', 'RJ', 'Sala 15', 2);

insert into endereco (cep, logradouro, numero, cidade, estado, complemento, cidadao) values
('30120-000', 'Praça da Liberdade', '50', 'Belo Horizonte', 'MG', 'Casa', 3);

insert into endereco (cep, logradouro, numero, cidade, estado, complemento, cidadao) values
('80040-000', 'Rua XV de Novembro', '300', 'Curitiba', 'PR', 'Sala 20', 4);

insert into endereco (cep, logradouro, numero, cidade, estado, complemento, cidadao) values
('90050-000', 'Avenida Farrapos', '500', 'Porto Alegre', 'RS', 'Casa 2', 5);

delete from proposta;
insert into proposta (titulo, descricao, categoria, id_politico) values
('Melhoria na Saúde', 'Aumentar o número de hospitais públicos.', 'SAUDE', 1);

insert into proposta (titulo, descricao, categoria, id_politico) values
('Educação para Todos', 'Proposta para aumentar o acesso ao ensino superior.', 'EDUCACAO', 2);

insert into proposta (titulo, descricao, categoria, id_politico) values
('Transporte Público', 'Implementar novas linhas de ônibus.', 'TRANSPORTE', 3);

insert into proposta (titulo, descricao, categoria, id_politico) values
('Segurança Pública', 'Contratar mais policiais.', 'SEGURANCA', 4);

insert into proposta (titulo, descricao, categoria, id_politico) values
('Sustentabilidade', 'Projetos para preservação ambiental.', 'MEIOAMBIENTE', 5);

delete from publicacao;
insert into publicacao (data_publicacao, titulo, descricao, id_cidadao, aprovado, avaliacao, denunciar, id_politico) values
('2024-12-01', 'Saúde é Prioridade', 'Precisamos de mais hospitais.', 1, true, 4.0, 'aprovado', 1);

insert into publicacao (data_publicacao, titulo, descricao, id_cidadao, aprovado, avaliacao, denunciar, id_politico) values
('2024-12-05', 'Educação para o Futuro', 'Educação de qualidade é essencial.', 2, true, 4.5, 'aprovado', 2);

insert into publicacao (data_publicacao, titulo, descricao, id_cidadao, aprovado, avaliacao, denunciar, id_politico) values
('2024-12-06', 'Mobilidade Urbana', 'Melhorar o transporte público.', 3, false, 3.8, 'aprovado', 3);

insert into publicacao (data_publicacao, titulo, descricao, id_cidadao, aprovado, avaliacao, denunciar, id_politico) values
('2024-12-07', 'Mais Segurança', 'Precisamos de ruas mais seguras.', 4, true, 4.2, 'aprovado', 4);

insert into publicacao (data_publicacao, titulo, descricao, id_cidadao, aprovado, avaliacao, denunciar, id_politico) values
('2024-12-08', 'Preservação Ambiental', 'Vamos salvar o planeta.', 5, true, 5.0, 'aprovado', 5);

delete from users;
insert into users (username, password, enabled, cidadao_id) values
('admin', '$2a$10$REXaxyIzUNgsYc6BswnC9u35FKBAR64J1LhRe1oG0nVt7sg4OpfhW', true, 1);

insert into users (username, password, enabled, cidadao_id) values
('02', '$2a$10$7lFqHhfnnQuq5y2B0VKrMueXYZJ1A4qr.xCCwBlKru3aTmjfezk.i', true, 2);

insert into users (username, password, enabled, cidadao_id) values
('03', '$2a$10$7lFqHhfnnQuq5y2B0VKrMueXYZJ1A4qr.xCCwBlKru3aTmjfezk.i', true, 3);

insert into users (username, password, enabled, cidadao_id) values
('04', '$2a$10$7lFqHhfnnQuq5y2B0VKrMueXYZJ1A4qr.xCCwBlKru3aTmjfezk.i', true, 4);

insert into users (username, password, enabled, cidadao_id) values
('05', '$2a$10$7lFqHhfnnQuq5y2B0VKrMueXYZJ1A4qr.xCCwBlKru3aTmjfezk.i', true, 5);

delete from authorities;
insert into authorities (username, authority) values
('admin', 'ROLE_ADMIN');

insert into authorities (username, authority) values
('admin', 'ROLE_USER');

insert into authorities (username, authority) values
('02', 'ROLE_USER');

insert into authorities (username, authority) values
('03', 'ROLE_USER');

insert into authorities (username, authority) values
('04', 'ROLE_USER');

insert into authorities (username, authority) values
('05', 'ROLE_USER');

delete from comentarios;
insert into comentarios (texto, denunciar, id_publicacao, id_cidadao) values
('Concordo com esta proposta.', 'aprovado', 1, 2);

insert into comentarios (texto, denunciar, id_publicacao, id_cidadao) values
('Muito importante esta iniciativa.', 'aprovado', 2, 3);

insert into comentarios (texto, denunciar, id_publicacao, id_cidadao) values
('Sugiro mais detalhes.', 'pendente', 3, 4);

insert into comentarios (texto, denunciar, id_publicacao, id_cidadao) values
('Excelente proposta.', 'aprovado', 4, 5);

insert into comentarios (texto, denunciar, id_publicacao, id_cidadao) values
('Vamos apoiar!', 'aprovado', 5, 1);
