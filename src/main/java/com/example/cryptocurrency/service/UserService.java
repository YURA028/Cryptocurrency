package com.example.cryptocurrency.service;

import com.example.cryptocurrency.entity.Crypto;
import com.example.cryptocurrency.entity.Price;
import com.example.cryptocurrency.entity.User;
import com.example.cryptocurrency.exception.UserAlreadyExistException;
import com.example.cryptocurrency.repository.CryptoRepository;
import com.example.cryptocurrency.repository.PriceRepository;
import com.example.cryptocurrency.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CryptoRepository cryptoRepository;
    @Autowired
    private PriceRepository priceRepository;


    public void registration(User user) throws UserAlreadyExistException {
        Crypto crypto = cryptoRepository.findBySymbol(user.getSymbol());
        if (crypto == null) {
            throw new UserAlreadyExistException("Неизвестный код криптоволюты");
        } else {
            if (userRepository.findByUsername(user.getUsername()) != null) {
                throw new UserAlreadyExistException("Польователь с таким именем зарегестрирован");
            } else {
                Price price = Price.builder()
                        .id(user.getId())
                        .priceUsd(crypto.getPriceUsd())
                        .build();
                priceRepository.save(price);
                user.setPrice(price);
                userRepository.save(user);
            }
        }
    }

    public void priceChange (User user){
        Crypto crypto = cryptoRepository.findBySymbol(user.getSymbol());
            double w = crypto.getPriceUsd();
            double q = user.getPrice().getPriceUsd();
            double e = ((q-w)/((q+w)/2))*100;
        System.out.println(e);
            boolean n = true;
        while (n){
            if (e >= 1){
                log.warn("Код валюты :" + user.getSymbol() + "имя пользователя :" + user.getUsername()
                        + "процент изменения цены :" + e);
                n = false;
            }
        }
    }
}
