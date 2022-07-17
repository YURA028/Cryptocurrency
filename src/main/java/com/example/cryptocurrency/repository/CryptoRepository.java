package com.example.cryptocurrency.repository;

import com.example.cryptocurrency.entity.Crypto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptoRepository extends JpaRepository<Crypto, Long> {

    Crypto findBySymbol (String symbol);
}
