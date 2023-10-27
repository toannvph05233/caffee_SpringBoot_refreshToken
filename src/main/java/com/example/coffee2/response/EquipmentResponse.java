package com.example.coffee2.response;

import lombok.Data;

@Data
public class EquipmentResponse {
    private Long id;
    private String model;
    private String name;
    private String brand;
    private String power;
    private Double price;
    private String capacity;
    private String description;
    private Long status;

}
