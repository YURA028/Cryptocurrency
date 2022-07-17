package com.example.cryptocurrency.service;

import com.example.cryptocurrency.entity.User;
import com.example.cryptocurrency.exception.UserAlreadyExistException;

public interface UserService {

    void registration(User user) throws UserAlreadyExistException;
}
