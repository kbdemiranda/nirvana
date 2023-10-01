create schema if not exists nirvana;

create table plataformas (
    id bigserial,
    nome text not null,
    preco numeric(10,2) not null,
    tipo text not null,
    total_vagas integer not null,
    vagas_disponiveis integer not null,
    data_criacao timestamp not null,
    data_atualizacao timestamp,
    data_exclusao timestamp,

    constraint pk_plataformas primary key (id)
)