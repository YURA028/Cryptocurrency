package com.example.cryptocurrency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CryptoScheduler {

    private final CryptoServiceImpl cryptoService;

    @Autowired
    public CryptoScheduler(CryptoServiceImpl cryptoService) {
        this.cryptoService = cryptoService;
    }

    final String bts = "https://api.coinlore.net/api/ticker/?id=90";
    final String eth = "https://api.coinlore.net/api/ticker/?id=80";
    final String sol = "https://api.coinlore.net/api/ticker/?id=48543";

    @Scheduled(cron = "0 0/1 * * * ?")
    void doWork(){
        cryptoService.editPrice(bts);
        cryptoService.editPrice(eth);
        cryptoService.editPrice(sol);
        cryptoService.priceChange();
    }
}
