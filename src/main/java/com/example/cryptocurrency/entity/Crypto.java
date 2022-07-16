package com.example.cryptocurrency.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "crypto")
public class Crypto {

        @Id
        @Column
        private Long id;
        @Column
        private String symbol;
        @Column
        private String name;
        @Column(name = "nameid")
        private String nameId;
        @Column(name = "rank_crypto")
        private String rank;
        @Column(name = "price_usd")
        private Double priceUsd;
        @Column(name = "percent_change_24h")
        private String percentChangeTwentyFourH;
        @Column(name = "percent_change_1h")
        private String percentChangeOneH;
        @Column(name = "percent_change_7d")
        private String percentChangeSevenD;
        @Column(name = "market_cap_usd")
        private String marketCapUsd;
        @Column(name = "volume24")
        private String volumeTwentyFour;
        @Column(name = "volume24_native")
        private String volumeTwentyFourNative;
        @Column
        private String csupply;
        @Column(name = "price_btc")
        private String priceBtc;
        @Column
        private String tsupply;
        @Column
        private String msupply;

}
