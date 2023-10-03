create table membros
(
    id               bigserial,
    nome             text not null,
    email            text not null,
    data_cadastro    timestamp not null,
    data_atualizacao timestamp,
    data_exclusao    timestamp,

    constraint pk_membros primary key (id),
    constraint uk_membros_email unique (email)
);
