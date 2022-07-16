package com.example.cryptocurrency.controller;

import com.example.cryptocurrency.exception.UserNotFoundException;
import com.example.cryptocurrency.service.CryptoScheduler;
import com.example.cryptocurrency.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crypto")
public class CryptoController {

    @Autowired
    private CryptoService cryptoService;

    @GetMapping
    public ResponseEntity<?> getCrypto(){
        try {
            return ResponseEntity.ok(cryptoService.getAll());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/{symbol}")
    public ResponseEntity<?> getOneCrypto(@PathVariable String symbol){
        try {
            return ResponseEntity.ok(cryptoService.getCrypto(symbol).getPrice_usd());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
