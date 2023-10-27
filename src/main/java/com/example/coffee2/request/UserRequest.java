package com.example.coffee2.request;

import lombok.Data;

@Data
public class UserRequest {
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

    private int pageIndex;
    private int pageSize;
}
