package com.example.cryptocurrency.controller;

import com.example.cryptocurrency.service.CryptoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/crypto")
public class CryptoController {

    @Autowired
    private CryptoServiceImpl cryptoService;

    @GetMapping
    public ResponseEntity<?> getCrypto(){
        try {
            return ResponseEntity.ok(cryptoService.getAll());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("An error has occurred");
        }
    }

    @GetMapping("/{symbol}")
    public ResponseEntity<?> getCryptoPage(@PathVariable String symbol){
        try {
            return ResponseEntity.ok(cryptoService.getCrypto(symbol).getPriceUsd());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("An error has occurred");
        }
    }
}
