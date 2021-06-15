drop table if exists category CASCADE;
drop table if exists product CASCADE;

drop table if exists sub_category CASCADE;

drop sequence if exists sm_sequence;

create sequence sm_sequence start with 1 increment by 1;

create table category
(
    id           bigint       not null,
    created_date timestamp,
    name         varchar(100) not null,
    updated_date timestamp,
    primary key (id)
);

create table product
(
    id              bigint         not null,
    created_date    timestamp,
    name            varchar(100)   not null,
    updated_date    timestamp,
    price           decimal(19, 2) not null,
    stock_amount    bigint         not null,
    sub_category_id bigint         not null,
    primary key (id)
);

create table sub_category
(
    id           bigint       not null,
    created_date timestamp,
    name         varchar(100) not null,
    updated_date timestamp,
    category_id  bigint       not null,
    primary key (id)
);

alter table category
    add constraint UK_46ccwnsi9409t36lurvtyljak unique (name);

alter table product
    add constraint UK_jmivyxk9rmgysrmsqw15lqr5b unique (name);

alter table sub_category
    add constraint UK_pxgji9v439t19r7qg0henfshu unique (name);

alter table product
    add constraint FKd9gfnsjgfwjtaxl57udrbocsp foreign key (sub_category_id) references sub_category on delete cascade;

alter table sub_category
    add constraint FKl65dyy5me2ypoyj8ou1hnt64e foreign key (category_id) references category on delete cascade