create sequence if not exists seq_step;
create sequence if not exists seq_task;

create table if not exists step(
	id bigint default seq_step.nextval not null,
	title varchar(100),
	isFinally boolean default false not null,
	constraint pkstep00 primary key(id)
);

insert into step(title, isFinally) (
    select title, isFinally from (
        select 'Etapa 1' as title, false as isFinally, 1 as ordered union
        select 'Etapa 2', false, 2 union
        select 'Etapa 3', false, 3 union
        select 'Etapa 4', false, 4 union
        select 'Etapa 5', false, 5 union
        select 'Finalizado', true, 6
    ) as x
    where not exists (select title from step)
    order by ordered
);


create table if not exists task(
	id bigint default seq_step.nextval not null,
	title varchar(100),
	description varchar(255),
	stepId int not null,
	color varchar(20),
	constraint pktask00 primary key(id),
	constraint fktask01 foreign key(stepId) references step(id)
);
