package com.example.cryptocurrency.repository;

import com.example.cryptocurrency.entity.Crypto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface CryptoRepository extends JpaRepository<Crypto, Long> {
    List<Crypto> findByName (String name);
    Crypto findBySymbol (String symbol);
}
