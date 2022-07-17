package com.example.cryptocurrency.service;

import com.example.cryptocurrency.entity.Crypto;
import com.example.cryptocurrency.entity.Price;
import com.example.cryptocurrency.entity.User;
import com.example.cryptocurrency.exception.UserAlreadyExistException;
import com.example.cryptocurrency.repository.CryptoRepository;
import com.example.cryptocurrency.repository.PriceRepository;
import com.example.cryptocurrency.repository.UserRepository;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@Log4j
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CryptoRepository cryptoRepository;
    @Autowired
    private PriceRepository priceRepository;

    @Override
    public void registration(User user) throws UserAlreadyExistException {
        Crypto crypto = cryptoRepository.findBySymbol(user.getSymbol());
        if (crypto == null) {
            throw new UserAlreadyExistException("Unknown cryptocurrency code");
        } else {
            if (crypto.getPriceUsd() == null){
                throw new UserAlreadyExistException("No cryptocurrency price");
            }else {
                if (userRepository.findByUsername(user.getUsername()) != null) {
                    throw new UserAlreadyExistException("User with this name is registered");
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
}
