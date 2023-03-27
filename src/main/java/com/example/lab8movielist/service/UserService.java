package com.example.lab8movielist.service;

import com.example.lab8movielist.dto.UserDto;
import com.example.lab8movielist.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
