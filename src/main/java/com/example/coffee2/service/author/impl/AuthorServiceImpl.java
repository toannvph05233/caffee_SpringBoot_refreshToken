package com.example.coffee2.service.author.impl;

import com.example.coffee2.dto.UserDto;
import com.example.coffee2.entity.UserEntity;
import com.example.coffee2.reponsitory.UserRespository;
import com.example.coffee2.response.AuthResponse;
import com.example.coffee2.response.base.ApiBaseResponse;
import com.example.coffee2.service.author.AuthorService;
import com.example.coffee2.service.author.JwtService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private UserRespository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Override
    public boolean registerAuthor(UserDto userDto) {
        try {
            List<String> checkNameExist = userRepository.findAllUserName();
            //???
            if (checkNameExist.contains(userDto.getUserName())) {
                log.error("Tài khoản đã tồn tại");
                return false;
            }
            UserEntity user = new UserEntity();
            user.setUserName(userDto.getUserName());
            user.setPassWord(passwordEncoder.encode(userDto.getPassWord()));
            // Cài đặt các trường dữ liệu khác từ userDto (ví dụ: email, tên, địa chỉ, v.v.)
            user.setEmail(userDto.getEmail());
            user.setName(userDto.getName());
            user.setStatus(1L);
            user.setRole(1L);
            // Lưu user vào cơ sở dữ liệu và trả về user đã được lưu
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            log.error("error: " + e.getMessage());
            return false;
        }

    }

    @Override
    public AuthResponse loginAuthor(UserDto userDto) {
        List<UserEntity> users = userRepository.findByUserName(userDto.getUserName());
        if (!users.isEmpty()) {
            UserEntity user = users.get(0);
            if (passwordEncoder.matches(userDto.getPassWord(), user.getPassWord())) {
                // Authentication successful
                String token = jwtService.generateToken(user.getUserName());
                log.info("new AuthResponse(token, user): " + new AuthResponse(token, user));
                return new AuthResponse(token, user);
            }
        }
        return null;
    }

//    @Override
//    public boolean loginAuthor(UserDto userDto) {
//        List<UserEntity> users = userRepository.findByUserName(userDto.getUserName());
//        if (!users.isEmpty()) {
//            UserEntity user = users.get(0);
//            if (passwordEncoder.matches(userDto.getPassWord(), user.getPassWord())) {
//                // Authentication successful
//                String token = jwtService.generateToken(user.getUserName());
//                log.info("new AuthResponse(token, user): " + new AuthResponse(token, user));
//                return true;
//            }
//            return false;
//        }
//        return false;
//    }

}
