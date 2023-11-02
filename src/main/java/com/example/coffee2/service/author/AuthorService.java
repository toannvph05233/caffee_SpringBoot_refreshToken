package com.example.coffee2.service.author;

import com.example.coffee2.dto.UserDto;
import com.example.coffee2.entity.UserEntity;
import com.example.coffee2.response.AuthResponse;

public interface AuthorService {
    boolean registerAuthor(UserDto userDto);
    AuthResponse loginAuthor(UserDto userDto);
//    boolean loginAuthor(UserDto userDto);
}
