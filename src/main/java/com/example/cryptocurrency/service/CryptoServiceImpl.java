package com.example.cryptocurrency.service;

import com.example.cryptocurrency.entity.Crypto;
import com.example.cryptocurrency.entity.User;
import com.example.cryptocurrency.model.CryptoDTO;
import com.example.cryptocurrency.model.CryptoNameDTO;
import com.example.cryptocurrency.repository.CryptoRepository;
import com.example.cryptocurrency.repository.UserRepository;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CryptoServiceImpl implements CryptoService {

    @Autowired
    private CryptoRepository cryptoRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<CryptoNameDTO> getAll() {
        return cryptoRepository.findAll()
                .stream().map(this::toDTO)
                .collect(Collectors.toList());
    }

    public CryptoNameDTO toDTO(Crypto crypto) {
        return CryptoNameDTO.builder()
                .name(crypto.getName())
                .build();
    }

    @Override
    public Crypto getCrypto(String symbol) {
        return cryptoRepository.findBySymbol(symbol);
    }

    @Override
    public void editPrice(String cryptos) {

        HttpURLConnection connection = null;
        URL url = null;
        InputStreamReader isR = null;
        BufferedReader bfR = null;

        try {
            url = new URL(cryptos);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                isR = new InputStreamReader(connection.getInputStream());
                bfR = new BufferedReader(isR);
                Gson g = new Gson();
                Crypto crypto = new Crypto();

                String line;
                while ((line = bfR.readLine()) != null) {
                    log.warn(line);
                    CryptoDTO[] p = g.fromJson(line, CryptoDTO[].class);

                    List<CryptoDTO> cryptocurrency = Arrays.stream(p)
                            .collect(Collectors.toList());

                    for (CryptoDTO a : cryptocurrency) {
                        crypto.setId(a.getId());
                        crypto.setSymbol(a.getSymbol());
                        crypto.setName(a.getName());
                        crypto.setNameId(a.getNameid());
                        crypto.setRank(a.getRank());
                        crypto.setPriceUsd(a.getPrice_usd());
                        crypto.setPercentChange24H(a.getPercent_change_24h());
                        crypto.setPercentChange1H(a.getPercent_change_1h());
                        crypto.setPercentChange7D(a.getPercent_change_7d());
                        crypto.setMarketCapUsd(a.getMarket_cap_usd());
                        crypto.setVolume24(a.getVolume24());
                        crypto.setVolume24Native(a.getVolume24_native());
                        crypto.setCsupply(a.getCsupply());
                        crypto.setPriceBtc(a.getPrice_btc());
                        crypto.setTsupply(a.getTsupply());
                        crypto.setMsupply(a.getMsupply());
                    }
                    cryptoRepository.save(crypto);
                }
            } else {
                log.warn("No connection " + connection.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (isR != null) {
                    isR.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bfR != null) {
                    bfR.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void priceChange() {
        List<User> users = userRepository.findAll();
        List<Crypto> cryptos = cryptoRepository.findAll();
        for (Crypto crypto : cryptos) {
            for (User user : users) {
                if (user.getSymbol().equals(crypto.getSymbol())) {
                    double priceUsd = crypto.getPriceUsd().doubleValue();
                    double price = user.getPrice().getPriceUsd().doubleValue();
                    if (priceUsd >= price) {
                        double calculation = ((priceUsd - price) / ((priceUsd + price) / 2)) * 100;
                        int percent = (int) calculation;
                        if (calculation >= 1) {
                            log.warn("\nSymbol : " + user.getSymbol()
                                    + "\nUsername : " + user.getUsername()
                                    + "\nPrice change percentage : -" + percent + "%");
                        }
                    } else {
                        double calculation = ((price - priceUsd) / ((price + priceUsd) / 2)) * 100;
                        int percent = (int) calculation;
                        if (calculation >= 1) {
                            log.warn("\nSymbol : " + user.getSymbol()
                                    + "\nUsername : " + user.getUsername()
                                    + "\nPrice change percentage : -" + percent + "%");
                        }
                    }
                }
            }
        }
    }
}
