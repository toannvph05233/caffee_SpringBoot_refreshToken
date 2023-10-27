package com.example.coffee2.service.user;

import com.example.coffee2.dto.UserDto;
import com.example.coffee2.request.UserRequest;
import com.example.coffee2.response.UserResponse;

import java.util.List;

public interface UserService {


//    String addUser(UserDto userDto);

    List<UserResponse> getListUser(UserRequest request);

    Long getCountListUser(UserRequest request);

    boolean create(UserRequest resquest);

    boolean update(UserRequest resquest);

    boolean delete(UserRequest resquest);
}
