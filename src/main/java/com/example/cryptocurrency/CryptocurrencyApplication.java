package com.example.cryptocurrency;

import com.example.cryptocurrency.entity.Crypto;
import com.example.cryptocurrency.service.CryptoScheduler;
import com.example.cryptocurrency.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CryptocurrencyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptocurrencyApplication.class, args);
	}
}
