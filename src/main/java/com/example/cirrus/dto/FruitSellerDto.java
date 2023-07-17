package com.example.cirrus.dto;

import lombok.Data;

import java.util.List;

@Data
public class FruitSellerDto {
    private Long fruitSellerId;
    private String firstName;
    private String lastName;
    private Integer phoneNumber;
    private List<FruitDto> fruitDtos;
}



