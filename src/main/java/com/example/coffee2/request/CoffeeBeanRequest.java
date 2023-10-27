package com.example.coffee2.request;

import lombok.Data;

@Data
public class CoffeeBeanRequest {
    private Long id;
    private String name;
    private Float price;
    private String popular;
    private String description;
    private String origin;
    private String plantingInstructions;
    private String preservationMethod;
    private Long status;
    private String contentCoffee;

    private int pageIndex;
    private int pageSize;
}