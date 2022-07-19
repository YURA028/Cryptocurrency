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
                Gson gson = new Gson();
                Crypto crypto = new Crypto();

                String line;
                while ((line = bfR.readLine()) != null) {
                    log.warn(line);
                    CryptoDTO[] cryptoDTOS = gson.fromJson(line, CryptoDTO[].class);
                    List<CryptoDTO> cryptocurrency = Arrays.stream(cryptoDTOS)
                            .collect(Collectors.toList());

                    for (CryptoDTO cryptoDTO : cryptocurrency) {
                        crypto.setId(cryptoDTO.getId());
                        crypto.setSymbol(cryptoDTO.getSymbol());
                        crypto.setName(cryptoDTO.getName());
                        crypto.setNameId(cryptoDTO.getNameid());
                        crypto.setRank(cryptoDTO.getRank());
                        crypto.setPriceUsd(cryptoDTO.getPrice_usd());
                        crypto.setPercentChange24H(cryptoDTO.getPercent_change_24h());
                        crypto.setPercentChange1H(cryptoDTO.getPercent_change_1h());
                        crypto.setPercentChange7D(cryptoDTO.getPercent_change_7d());
                        crypto.setMarketCapUsd(cryptoDTO.getMarket_cap_usd());
                        crypto.setVolume24(cryptoDTO.getVolume24());
                        crypto.setVolume24Native(cryptoDTO.getVolume24_native());
                        crypto.setCsupply(cryptoDTO.getCsupply());
                        crypto.setPriceBtc(cryptoDTO.getPrice_btc());
                        crypto.setTsupply(cryptoDTO.getTsupply());
                        crypto.setMsupply(cryptoDTO.getMsupply());
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

}