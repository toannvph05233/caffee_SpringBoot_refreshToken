package com.example.coffee2.service.user;

import com.example.coffee2.dto.UserDto;
import com.example.coffee2.request.UserRequest;
import com.example.coffee2.response.UserResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {


//    String addUser(UserDto userDto);

    List<UserResponse> getListUser(UserRequest request);

    Long getCountListUser(UserRequest request);

    boolean create(UserRequest request);

    boolean update(UserRequest request);

    boolean updateInfo(UserRequest request);

    boolean delete(UserRequest request);
}
