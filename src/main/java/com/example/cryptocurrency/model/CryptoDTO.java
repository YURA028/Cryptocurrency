package com.example.cryptocurrency.model;

import com.example.cryptocurrency.entity.Crypto;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@ToString
@Builder
public class CryptoDTO {

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

    public static CryptoDTO toModel(Crypto entity) {
        return CryptoDTO.builder().id(entity.getId())
                .symbol(entity.getSymbol())
                .name(entity.getName())
                .price_usd(entity.getPrice_usd())
                .build();
    }
}
