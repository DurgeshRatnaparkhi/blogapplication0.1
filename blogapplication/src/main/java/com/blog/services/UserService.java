package com.blog.services;

import com.blog.payload.UserDto;

import java.util.List;

public interface UserService {

    // in Interface, we can not use body so we implement all method in service impl class
    UserDto createUser(UserDto user);

    UserDto updateUser(UserDto userDto,Integer userId);

    UserDto getUserById(Integer userId);

    List<UserDto> getAllUser();

    void deleteUser(Integer userId);
    
   
}
