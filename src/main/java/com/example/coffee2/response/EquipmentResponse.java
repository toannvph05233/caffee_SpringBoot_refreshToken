package com.example.coffee2.response;

import lombok.Data;

@Data
public class EquipmentResponse {
    private Long id;
    private String name;
    private String title;
    private String contentEquipment;
    private String power;
    private Double price;
    private String capacity;
    private String description;
    private Long status;


}
