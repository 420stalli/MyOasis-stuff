package com.example.cirrus.entity;



import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;


@Entity(name = "Fruits")
@Table(name = "fruits")
@Data
public class Fruits implements Serializable {

    @Id
    @SequenceGenerator(
            name = "fruits_sequence",
            sequenceName = "fruits_sequence",
            allocationSize = 1

    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "fruits_sequence"
    )
    @Column(
            updatable = false
    )
    private Long id;
    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;
    @Column(
            nullable = false
    )
    private Float price;
    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String countryOfOrigin;



    @JoinColumn(name = "fruit_seller", referencedColumnName = "fruitSellerId")
    @ManyToOne(fetch = LAZY)
    private FruitSeller fruitSeller;


}

