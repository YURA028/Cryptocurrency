package com.example.cryptocurrency.service;

import com.example.cryptocurrency.entity.Crypto;
import com.example.cryptocurrency.model.CryptoNameDTO;

import java.util.List;

public interface CryptoService {

    List<CryptoNameDTO> getAll();

    Crypto getCrypto (String symbol);

    void editPrice(String cryptos);

    void priceChange ();
}
