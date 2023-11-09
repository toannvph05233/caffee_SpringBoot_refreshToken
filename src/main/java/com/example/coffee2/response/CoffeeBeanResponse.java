package com.example.coffee2.response;

import lombok.Data;

@Data
public class CoffeeBeanResponse {
    private Long id;
    private String name;
    private String title;
    private String popular;
    private String description;
    private String origin;
    private String plantingInstructions;
    private Long status;
    private String contentCoffee;
    private String image;
//    private Long slug;


}
