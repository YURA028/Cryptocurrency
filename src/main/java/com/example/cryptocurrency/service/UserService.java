package com.example.cryptocurrency.service;

import com.example.cryptocurrency.entity.UserEntity;
import com.example.cryptocurrency.exception.UserAlreadyExistException;
import com.example.cryptocurrency.exception.UserNotFoundException;
import com.example.cryptocurrency.model.User;
import com.example.cryptocurrency.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity registration (UserEntity userEntity) throws UserAlreadyExistException {
        if (userRepository.findByUsername(userEntity.getUsername()) != null){
            throw  new  UserAlreadyExistException("Польователь с таким именем зарегестрирован");
        }
        return userRepository.save(userEntity);
    }

    public User getOne(Long id) throws UserNotFoundException {
        UserEntity user = userRepository.findById(id).get();
        if (user == null){
            throw new UserNotFoundException("Пользователь не найден");
        }
        return User.toModel(user);
    }

    public Long delete(Long id){
        userRepository.deleteById(id);
        return id;
    }
}
