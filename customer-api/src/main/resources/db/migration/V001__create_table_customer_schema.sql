create table customer_table (
    id bigint not null auto_increment,
    name varchar(255) not null,
    payment bigint not null,
    primary key(id)
) engine=InnoDB default charset=utf8;

create table user_entity_table (
	id bigint not null auto_increment,
	name varchar(80) not null,
	email varchar(255) not null,
	password varchar(255) not null,
	date_create datetime not null,
	primary key (id)
) engine=InnoDB default charset=utf8;

create table group_entity_table (
	id bigint not null auto_increment,
	name varchar(60) not null,
	primary key (id)
) engine=InnoDB default charset=utf8;

create table permission_entity_table (
	id bigint not null auto_increment,
	description varchar(60) not null,
	name varchar(100) not null,
	primary key (id)
) engine=InnoDB default charset=utf8;

create table group_permission (
	group_id bigint not null,
	permission_id bigint not null,
	primary key (group_id, permission_id)
) engine=InnoDB default charset=utf8;



create table user_group (
	user_id bigint not null,
	group_id bigint not null,
	primary key (user_id, group_id)
) engine=InnoDB default charset=utf8;
--
alter table group_permission add constraint fk_group_permission_permission
foreign key (permission_id) references permission_entity_table (id);

alter table group_permission add constraint fk_group_permission_group
foreign key (group_id) references group_entity_table (id);

alter table user_group add constraint fk_user_group_group
foreign key (group_id) references group_entity_table (id);

alter table user_group add constraint fk_user_group_user
foreign key (user_id) references user_entity_table (id);