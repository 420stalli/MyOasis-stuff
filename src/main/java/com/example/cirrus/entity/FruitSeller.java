package com.example.cirrus.entity;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;



@Entity
@Table(name="fruit_seller")
@Data
public class FruitSeller implements Serializable {
    @Id
    @SequenceGenerator(
            name = "fruit_seller_sequence",
            sequenceName = "fruit_seller_sequence",
            allocationSize = 1

    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "fruit_seller_sequence"
    )
    private Long fruitSellerId;
    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String firstName;
    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String lastName;

    @Column(
            nullable = false
    )
    private Integer phoneNumber;







}
