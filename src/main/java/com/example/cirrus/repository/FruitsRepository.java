package com.example.cirrus.repository;

import com.example.cirrus.entity.FruitSeller;
import com.example.cirrus.entity.Fruits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FruitsRepository extends JpaRepository<Fruits, Long> {


    Optional<Fruits> findFruitsByName(String name);

    List<Fruits> findFruitsByFruitSeller(FruitSeller seller);
}
