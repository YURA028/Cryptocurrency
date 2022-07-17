create table crypto (
                        id bigint not null,
                        csupply varchar(255),
                        market_cap_usd varchar(255),
                        msupply varchar(255),
                        name varchar(255),
                        nameid varchar(255),
                        percent_change_1h varchar(255),
                        percent_change_24h varchar(255),
                        percent_change_7d varchar(255),
                        price_btc varchar(255),
                        price_usd decimal(19,2),
                        rank_crypto varchar(255),
                        symbol varchar(255),
                        tsupply varchar(255),
                        volume24 varchar(255),
                        volume24_native varchar(255),
                        primary key (id)
) engine=InnoDB;

create table price (
                       id bigint not null auto_increment,
                       price_usd decimal(19,2),
                       primary key (id)
) engine=InnoDB;

create table user (
                      id bigint not null auto_increment,
                      symbol varchar(255),
                      username varchar(255),
                      price_id bigint,
                      primary key (id)
) engine=InnoDB;

alter table user
    add constraint FKgqgeei1n23wfr5eyyg2ayuymv
        foreign key (price_id)
            references price (id);