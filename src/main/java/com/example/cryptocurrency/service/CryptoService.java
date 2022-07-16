package com.example.cryptocurrency.service;

import com.example.cryptocurrency.entity.Crypto;
import com.example.cryptocurrency.entity.User;
import com.example.cryptocurrency.model.CryptoDTO;
import com.example.cryptocurrency.model.CryptoNameDTO;
import com.example.cryptocurrency.model.PriceDTO;
import com.example.cryptocurrency.repository.CryptoRepository;
import com.example.cryptocurrency.repository.PriceRepository;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CryptoService {

    @Autowired
    private CryptoRepository cryptoRepository;
    @Autowired
    private PriceRepository priceRepository;

    public CryptoNameDTO toDTO(Crypto crypto) {
        return CryptoNameDTO.builder()
                .name(crypto.getName())
                .build();
    }



    public List<CryptoNameDTO> getAll() {
        log.warn("method222 scheduler");
        return cryptoRepository.findAll()
                .stream().map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Crypto getCrypto (String symbol){
        return cryptoRepository.findBySymbol(symbol);
    }

    public CryptoDTO getOneCrypto(String symbol) {
        log.warn("method333 scheduler");
        Crypto crypto = cryptoRepository.findBySymbol(symbol);
        return CryptoDTO.toModel(crypto);
    }

    public void editPrice(String url_crypto) {

        log.warn("method scheduler");
        HttpURLConnection connection = null;
        URL url = null;
        InputStreamReader isR = null;
        BufferedReader bfR = null;

        try {
            url = new URL(url_crypto);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                isR = new InputStreamReader(connection.getInputStream());
                bfR = new BufferedReader(isR);
                Gson g = new Gson();
                Crypto cryptoPrice = new Crypto();

                String line;
                while ((line = bfR.readLine()) != null) {
                    System.out.println(line);

                    CryptoDTO[] p = g.fromJson(line, CryptoDTO[].class);

                    List<CryptoDTO> cryptocurrency = Arrays.stream(p)
                            .collect(Collectors.toList());

                    for (CryptoDTO a : cryptocurrency) {
                        cryptoPrice.setId(a.getId());
                        cryptoPrice.setSymbol(a.getSymbol());
                        cryptoPrice.setName(a.getName());
                        cryptoPrice.setNameid(a.getNameid());
                        cryptoPrice.setRank(a.getRank());
                        cryptoPrice.setPrice_usd(a.getPrice_usd());
                        cryptoPrice.setPercent_change_24h(a.getPercent_change_24h());
                        cryptoPrice.setPercent_change_1h(a.getPercent_change_1h());
                        cryptoPrice.setPercent_change_7d(a.getPercent_change_7d());
                        cryptoPrice.setMarket_cap_usd(a.getMarket_cap_usd());
                        cryptoPrice.setVolume24(a.getVolume24());
                        cryptoPrice.setVolume24_native(a.getVolume24_native());
                        cryptoPrice.setCsupply(a.getCsupply());
                        cryptoPrice.setPrice_btc(a.getPrice_btc());
                        cryptoPrice.setTsupply(a.getTsupply());
                        cryptoPrice.setMsupply(a.getMsupply());

                    }
                    cryptoRepository.save(cryptoPrice);
                }
            } else {
                System.out.println("No connection " + connection.getResponseCode());
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
