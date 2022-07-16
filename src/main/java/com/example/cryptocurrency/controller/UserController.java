package com.example.cryptocurrency.controller;

import com.example.cryptocurrency.entity.User;
import com.example.cryptocurrency.exception.UserAlreadyExistException;
import com.example.cryptocurrency.exception.UserNotFoundException;
import com.example.cryptocurrency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    //    @PostMapping
//    public ResponseEntity<?> registration(@RequestBody User user){
//        try {
//            userService.registration(user);
//            return ResponseEntity.ok("Пользователь успешно сохранен");
//        }catch (UserAlreadyExistException e){
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }catch (Exception e){
//            return ResponseEntity.badRequest().body("Произошла ошибка");
//        }
//    }
    @PostMapping(value = "/user")
    public ResponseEntity<?> registration(@RequestBody User user) {
        userService.registration(user);
        return ResponseEntity.ok("Пользователь успешно сохранен");

    }

//    @GetMapping(value = "/user")
//    public ResponseEntity<?> getOneUser(@RequestParam Long id) {
//        try {
//            return ResponseEntity.ok(userService.getOne(id));
//        } catch (UserNotFoundException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Произошла ошибка");
//        }
//    }

}
