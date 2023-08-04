package com.example.cirrus.repository;

import com.example.cirrus.dto.FruitDto;
import com.example.cirrus.entity.FruitSeller;
import com.example.cirrus.entity.Fruits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FruitsRepository extends JpaRepository<Fruits, Long> {


    List<Fruits> findFruitsByName(String name);

    List<Fruits> findFruitsByCountryOfOrigin(String countryOfOrigin);

    List<Fruits> findFruitsByFruitSeller(FruitSeller seller);

}