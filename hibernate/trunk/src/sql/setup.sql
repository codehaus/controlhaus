create table Parent (id BIGINT not null, primary key (id));
create table Child (id BIGINT not null, hufse BIGINT, name VARCHAR(255), primary key (id));
alter table Child add constraint FK3E104FC5F0490B foreign key (hufse) references Parent;
