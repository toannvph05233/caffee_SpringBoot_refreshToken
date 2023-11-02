package com.example.coffee2.response;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String userName;
    private String passWord;
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private Long age;
    private Long role;
    private Long phoneNumber;
    private String dateOfBirth;
    private Long sex;
    private String createDate;
    private Long status;
    private String image;
//    private byte[] data;

    private String data;


}
