package com.example.cryptocurrency.model;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@ToString
public class Crypto {

    private Long id;
    private String symbol;
    private String name;
    private String nameid;
    private String rank;
    private BigDecimal price_usd;
    private String percent_change_24h;
    private String percent_change_1h;
    private String percent_change_7d;
    private String market_cap_usd;
    private String volume24;
    private String volume24_native;
    private String csupply;
    private String price_btc;
    private String tsupply;
    private String msupply;

    public static Crypto toModel(com.example.cryptocurrency.entity.Crypto entity) {
        Crypto model = new Crypto();
        model.setId(entity.getId());
        model.setSymbol(entity.getSymbol());
        model.setName(entity.getName());
        model.setPrice_usd(entity.getPrice_usd());
        model.setPercent_change_1h(entity.getPercent_change_1h());
        return model;
    }
}
