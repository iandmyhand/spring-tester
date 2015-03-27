-- drop table account;

create table account (
    user_key varchar(80) not null,
    email varchar(80) not null,
    first_name varchar(80) null,
    last_name varchar(80) null,
    regist_ymdt date null, 
    constraint pk_account primary key (user_key)
);
