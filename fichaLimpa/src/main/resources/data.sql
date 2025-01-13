
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
insert into politico (nome, cargo, nome_eleitoral, id_partido, numero_eleitoral, registro_candidatura, data_nascimento, nota, contato)
	values ('POLITICO TESTE 1', 'PRESIDENTE', 'POLITICO 1', 1, '123', '111111', '1999-10-10', 3.0, 'politico1@email.com');

insert into politico (nome, cargo, nome_eleitoral, id_partido, numero_eleitoral, registro_candidatura, data_nascimento, nota, contato)
    values ('Pietro', 'VEREADOR', 'Pietroggg', 3, '455', '145881', '2001-07-10', 5.0, 'pietro@email.com');
	
delete from cidadao;
insert into cidadao (cpf, telefone, nome, data_nascimento)
values('292.834.321-00', '(98)92783-9145', 'ADMINISTRADOR', '2024-11-04 00:00:00');
insert into cidadao (cpf, telefone, nome, data_nascimento)
values('287.111.222-00', '(98)93432-8521', 'CIDADAO TESTE 2', '2004-06-09 00:00:00');
insert into cidadao (cpf, telefone, nome, data_nascimento)
values('333.111.222-00', '(98)99731-8521', 'CIDADAO TESTE 3', '2003-06-05 00:00:00');

delete from endereco;
insert into endereco (cep, logradouro, numero, cidade, estado, complemento, cidadao)
values ('69314-164', 'Rua Lírio do Campo', '841', 'Boa Vista', 'RR', 'Nenhum', 1);
insert into endereco (cep, logradouro, numero, cidade, estado, complemento, cidadao)
values ('69314-164', 'Rua Lírio do Campo', '841', 'Boa Vista', 'RR', 'Nenhum', 3);

INSERT INTO publicacao (data_publicacao, titulo, descricao, anexo, id_cidadao, resposta, aprovado, avaliacao, id_politico)
VALUES ('2024-01-13', 'Publicação teste', 'Descrição de uma avaliação fictícia para fins de teste.', NULL, 1, NULL, TRUE, 8, 1);

delete proposta;
insert into proposta (titulo, descricao, categoria, id_politico)
values ('Proposta A', 'Descrição de uma proposta fictícia para fins de teste.', 'SAUDE', 1);

/*delete from publicacao;
insert into publicacao (data_publicacao, titulo, descricao, id_cidadao, aprovado, avaliacao, id_politico)
values ('2024-12-04', 'Publicação Teste', 'Esta é uma publicação de teste.', 2, true, 3.0, 1);*/

delete from publicacao;
insert into publicacao (data_publicacao, titulo, descricao, id_cidadao, aprovado, avaliacao, id_politico)
values ('2024-12-04', 'Publicação Teste', 'Esta é uma publicação de teste.', 3, true, 5.0, 2);

/* admin123 */
/* 123456 */
insert into users (username, password, enabled, cidadao_id) values ('admin', '$2a$10$REXaxyIzUNgsYc6BswnC9u35FKBAR64J1LhRe1oG0nVt7sg4OpfhW', true, 1);
insert into users (username, password, enabled, cidadao_id) values ('02', '$2a$10$7lFqHhfnnQuq5y2B0VKrMueXYZJ1A4qr.xCCwBlKru3aTmjfezk.i', true, 2);
insert into users (username, password, enabled, cidadao_id) values ('03', '$2a$10$7lFqHhfnnQuq5y2B0VKrMueXYZJ1A4qr.xCCwBlKru3aTmjfezk.i', true, 3);

insert into authorities (username, authority) values ('admin', 'ROLE_ADMIN');
insert into authorities (username, authority) values ('admin', 'ROLE_USER');
insert into authorities (username, authority) values ('02', 'ROLE_USER');
insert into authorities (username, authority) values ('03', 'ROLE_USER');
