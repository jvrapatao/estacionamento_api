CREATE TABLE estacionamento (
id BIGINT not null auto_increment,
modelo varchar(100) not null,
marca varchar(100) not null,
período varchar(100) not null,
vagas varchar(100) not null,
hora varchar (100) not null,
data varchar(100) not null,

primary key (id)
);
