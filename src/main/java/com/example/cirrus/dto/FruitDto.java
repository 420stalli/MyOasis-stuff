package com.example.cirrus.dto;


import com.example.cirrus.entity.FruitSeller;
import lombok.Data;

@Data
public class FruitDto {
    private String name;
    private Float price;
    private String countryOfOrigin;
}
