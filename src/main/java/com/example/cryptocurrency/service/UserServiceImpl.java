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
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CryptoRepository cryptoRepository;
    @Autowired
    private PriceRepository priceRepository;

    public void registration(User user) throws UserAlreadyExistException {
        Crypto crypto = cryptoRepository.findBySymbol(user.getSymbol());
        if (crypto == null) {
            throw new UserAlreadyExistException("Unknown cryptocurrency code");
        } else {
            if (crypto.getPriceUsd() == null) {
                throw new UserAlreadyExistException("No cryptocurrency price");
            } else {
                if (userRepository.findByUsername(user.getUsername()) != null) {
                    throw new UserAlreadyExistException("User with this name is registered");
                } else {
                    new Waiter(user).start();
                    new Notifier(user).start();
                }
            }
        }
    }

    class Waiter extends Thread {

        private final User user;

        public Waiter(User user) {
            this.user = user;
        }

        boolean n = true;

        @Override
        public void run() {
            while (true) {
                synchronized (user) {
                    try {
                        if (n) {
                            user.wait();
                            n = false;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    double priceUsd = cryptoRepository.findBySymbol(user.getSymbol()).getPriceUsd().doubleValue();
                    double price = user.getPrice().getPriceUsd().doubleValue();
                    double calculationMore = ((priceUsd - price) / ((priceUsd + price) / 2)) * 100;
                    double calculationLess = ((price - priceUsd) / ((price + priceUsd) / 2)) * 100;
                    if (calculationMore >= 1) {
                        log.warn("\nSymbol : " + user.getSymbol()
                                + "\nUsername : " + user.getUsername()
                                + "\nPrice change percentage : +" + (int) calculationMore + " %");
                        n = true;
                        break;
                    } else {
                        if (calculationLess >= 1) {
                            log.warn("\nSymbol : " + user.getSymbol()
                                    + "\nUsername : " + user.getUsername()
                                    + "\nPrice change percentage : -" + (int) calculationLess + " %");
                            n = true;
                            break;
                        }
                    }
                }
            }
        }
    }

    class Notifier extends Thread {
        private final User user;

        public Notifier(User user) {
            this.user = user;
        }

        @Override
        public void run() {
            while (user.getId() == null) {
                synchronized (user) {
                    Price price = Price.builder()
                            .id(user.getId())
                            .priceUsd(cryptoRepository.findBySymbol(user.getSymbol()).getPriceUsd())
                            .build();
                    user.setPrice(price);
                    userRepository.save(user);
                    priceRepository.save(price);
                    user.notify();
                }
            }
        }
    }
}
