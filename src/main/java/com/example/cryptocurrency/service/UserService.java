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
}
