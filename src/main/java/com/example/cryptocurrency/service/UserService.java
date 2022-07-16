package com.example.cryptocurrency.service;

import com.example.cryptocurrency.Test2;
import com.example.cryptocurrency.entity.Crypto;
import com.example.cryptocurrency.entity.Price;
import com.example.cryptocurrency.entity.User;
import com.example.cryptocurrency.exception.UserAlreadyExistException;
import com.example.cryptocurrency.exception.UserNotFoundException;
import com.example.cryptocurrency.model.UserDTO;
import com.example.cryptocurrency.repository.CryptoRepository;
import com.example.cryptocurrency.repository.PriceRepository;
import com.example.cryptocurrency.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CryptoRepository cryptoRepository;
    @Autowired
    private PriceRepository priceRepository;

    //    public void registration(User user) throws UserAlreadyExistException {
//        Crypto crypto = cryptoRepository.findBySymbol(user.getSymbol());
//        if (crypto == null) {
//            throw new UserAlreadyExistException("Неизвестный код криптоволюты");
//        } else {
//            if (userRepository.findByUsername(user.getUsername()) != null) {
//                throw new UserAlreadyExistException("Польователь с таким именем зарегестрирован");
//            } else {
//                userRepository.save(user);
//                Price price = Price.builder()
//                        .id(user.getId())
//                        .symbol(user.getSymbol())
//                        .price_usd(crypto.getPrice_usd())
//                        .build();
//                price.setUser(user);
//                priceRepository.save(price);
//                Registration22 registration22 = new Registration22();
//                notify();
//            }
//        }
//    }

    //    public void registration(User user) throws UserAlreadyExistException {
//        Crypto crypto = cryptoRepository.findBySymbol(user.getSymbol());
//        if (crypto == null) {
//            throw new UserAlreadyExistException("Неизвестный код криптоволюты");
//        } else {
//            if (userRepository.findByUsername(user.getUsername()) != null) {
//                throw new UserAlreadyExistException("Польователь с таким именем зарегестрирован");
//            } else {
//                userRepository.save(user);
//                Price price = Price.builder()
//                        .id(user.getId())
//                        .symbol(user.getSymbol())
//                        .price_usd(crypto.getPrice_usd())
//                        .build();
//                price.setUser(user);
//                priceRepository.save(price);
//
//                User user1 = userRepository.findByUsername(user.getUsername());
//                Waiter waiter = new Waiter(user);
//                Notifier notifier = new Notifier(user);
//                new Thread(waiter).start();
//                new Thread(notifier).start();
//            }
//        }
//    }
    public void registration(User user){
        Price price = new Price();
        Crypto crypto = cryptoRepository.findBySymbol(user.getSymbol());
        price.setPrice_usd(crypto.getPrice_usd());
        user.setPrice(price);
        userRepository.save(user);

    }

    public UserDTO getOne(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id).get();
        if (user == null) {
            throw new UserNotFoundException("Пользователь не найден");
        }
        return UserDTO.toModel(user);
    }
}
