# create table hibernate_sequence (
#     next_val bigint
# ) engine=MyISAM;
#
# insert into hibernate_sequence values ( 1 );
# insert into hibernate_sequence values ( 1 );
#
# CREATE TABLE user (
#                       id BIGINT NOT NULL AUTO_INCREMENT,
#                       password varchar(64) not null,
#                       username varchar(64) not null unique,
#                       PRIMARY KEY (id)
# ) engine=MyISAM;
#
# CREATE TABLE todo (
#                       id BIGINT NOT NULL AUTO_INCREMENT,
#                       title varchar(64) not null,
#                       completed BOOLEAN not null,
#                       PRIMARY KEY (id)
# ) engine=MyISAM;
#
# CREATE TABLE crypto (
#                       id BIGINT NOT NULL,
#                       symbol varchar(64) not null,
#                       name varchar(64) not null,
#                       price_usd DECIMAL not null,
#                       percent_change DECIMAL not null,
#                       PRIMARY KEY (id)
# ) engine=MyISAM;