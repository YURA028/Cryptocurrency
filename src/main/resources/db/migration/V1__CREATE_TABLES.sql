create table crypto (
                        id bigint not null auto_increment,
                        csupply double precision,
                        market_cap_usd double precision,
                        msupply double precision,
                        name varchar(255),
                        nameid varchar(255),
                        percent_change_1h double precision,
                        percent_change_7d double precision,
                        percent_change_24h double precision,
                        price_btc double precision,
                        price_usd double precision,
                        rank_crypto bigint,
                        symbol varchar(255),
                        tsupply double precision,
                        volume24 double precision,
                        volume24_native double precision,
                        primary key (id)
) engine=InnoDB