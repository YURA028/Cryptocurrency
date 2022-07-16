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
@Table
public class Crypto {

        @Id
        @Column
        private Long id;
        @Column
        private String symbol;
        @Column
        private String name;
        @Column
        private String nameid;
        @Column(name = "'rank'")
        private String rank;
        @Column
        private BigDecimal price_usd;
        @Column
        private String percent_change_24h;
        @Column
        private String percent_change_1h;
        @Column
        private String percent_change_7d;
        @Column
        private String market_cap_usd;
        @Column
        private String volume24;
        @Column
        private String volume24_native;
        @Column
        private String csupply;
        @Column
        private String price_btc;
        @Column
        private String tsupply;
        @Column
        private String msupply;
}
