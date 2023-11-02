package com.example.coffee2.controller;

import com.example.coffee2.dto.UserDto;
import com.example.coffee2.entity.UserEntity;
import com.example.coffee2.response.AuthResponse;
import com.example.coffee2.response.base.ApiBaseResponse;
import com.example.coffee2.service.author.AuthorService;
import com.example.coffee2.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/register")
    public ApiBaseResponse register(@RequestBody UserDto userDto) {
        ApiBaseResponse apiBaseResponse = new ApiBaseResponse();
        boolean rs = authorService.registerAuthor(userDto);
        if(!rs) {
            apiBaseResponse.setErrorCode(Constants.CALL_API_CODE_FAIL);
            apiBaseResponse.setErrorDescription("Tài khoản đã tồn tại");
            apiBaseResponse.setData(userDto);
            apiBaseResponse.setOptional(1L);
            return apiBaseResponse;
        }
        apiBaseResponse.setErrorCode(Constants.CALL_API_CODE_SUCCESS);
        apiBaseResponse.setErrorDescription("Tạo tài khoản thành công");
        apiBaseResponse.setData(userDto);
        apiBaseResponse.setOptional(1L);
        return apiBaseResponse;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody UserDto userDto) {
        return authorService.loginAuthor(userDto);
    }

//    @PostMapping("/login")
//    public ApiBaseResponse login(@RequestBody UserDto userDto) {
//        ApiBaseResponse apiBaseResponse = new ApiBaseResponse();
//        boolean rs = authorService.loginAuthor(userDto);
//        if(!rs) {
//            apiBaseResponse.setErrorCode(Constants.CALL_API_CODE_FAIL);
//            apiBaseResponse.setErrorDescription("Tài khoản hoặc mật khẩu không đúng");
//            apiBaseResponse.setData(userDto);
//            apiBaseResponse.setOptional(1L);
//            return apiBaseResponse;
//        }
//        apiBaseResponse.setErrorCode(Constants.CALL_API_CODE_SUCCESS);
//        apiBaseResponse.setErrorDescription("Đăng nhập thành công");
//        apiBaseResponse.setData(userDto);
//        apiBaseResponse.setOptional(1L);
//        return apiBaseResponse;
//    }
}
