package com.example.cryptocurrency.controller;

import com.example.cryptocurrency.exception.UserNotFoundException;
import com.example.cryptocurrency.service.CryptoScheduler;
import com.example.cryptocurrency.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/crypto")
public class CryptoController {

    @Autowired
    private CryptoService cryptoService;

    @GetMapping
    public ResponseEntity getCrypto(){
        try {
            return ResponseEntity.ok(cryptoService.getAll());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/one")
    public ResponseEntity getOneCrypto(@RequestParam Long id){
        try {
            return ResponseEntity.ok(cryptoService.getOneCrypto(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }


}
