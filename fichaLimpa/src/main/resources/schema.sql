drop table if exists partido;
create table partido(
	id identity primary key ,
	numero varchar(5) not null,	
	nome varchar(50) not null,
    sigla varchar(15) not null
);

drop table if exists politico;
create table politico(
	id identity primary key,
    --documento varchar(15) not null,
    nome varchar(60) not null,
    cargo varchar(20) not null,
    nome_eleitoral varchar(50) not null,
    id_partido bigint not null,
    numero_eleitoral varchar(50) not null,
    registro_candidatura varchar(50) not null,
    data_nascimento timestamp not null,
    nota double,
    contato varchar(50) not null
);
alter table politico add foreign key (id_partido) references partido(id);

drop table if exists proposta;
create table proposta(
	id identity primary key,
    titulo varchar(200) not null,
    descricao text not null,
    categoria varchar(30),
    id_politico bigint
);
alter table proposta add foreign key (id_politico) references politico(id)
ON DELETE CASCADE;

drop table if exists cidadao;
create table cidadao(
	id identity primary key,
    cpf varchar(14) not null unique,
    telefone varchar(15) not null,
    nome varchar(50),
    data_nascimento timestamp not null
);

drop table if exists endereco;
create table endereco(
	id identity primary key,
    cep varchar(9),
    logradouro varchar(50) not null,
    numero int not null,
    cidade varchar(20) not null,
    estado varchar(2) not null,
    complemento varchar (50),
    cidadao bigint not null
);
alter table endereco add foreign key (cidadao) references cidadao(id);


drop table if exists publicacao;
create table publicacao(
	id identity primary key,
    data_publicacao timestamp not null,
    titulo varchar(40) not null,
    descricao text,
    anexo varchar(10),
    id_cidadao bigint not null,
    resposta varchar,
    aprovado boolean not null,
    avaliacao int not null,
    denunciar text not null,
    id_politico bigint not null
);

alter table publicacao add foreign key (id_cidadao) references cidadao(id)
ON DELETE CASCADE;
alter table publicacao add foreign key (id_politico) references politico(id)
ON DELETE CASCADE;

drop table if exists comentarios;
create table comentarios(
    id identity primary key,
    texto text,
    denunciar text not null,
    id_publicacao bigint not null,
    id_cidadao bigint not null
);

alter table comentarios add foreign key (id_publicacao) references publicacao(id)
ON DELETE CASCADE;
alter table comentarios add foreign key (id_cidadao) references cidadao(id)
ON DELETE CASCADE;

drop table if exists users;
create table users (
	username varchar(50) not null primary key,
	password varchar(500) not null,
	enabled boolean not null,
	cidadao_id bigint not null
);
alter table users add foreign key (cidadao_id) references cidadao(id) ON DELETE CASCADE;

drop table if exists authorities;
create table authorities (
	username varchar(50) not null,
	authority varchar(50) not null
);
alter table authorities add foreign key (username) references users(username) ON DELETE CASCADE;



