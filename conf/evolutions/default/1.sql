# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table User_Table (
  id                        bigint not null,
  full_name                 varchar(255),
  company                   varchar(255),
  email                     varchar(255),
  password                  varchar(255),
  constraint pk_User_Table primary key (id))
;

create sequence User_Table_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists User_Table;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists User_Table_seq;

