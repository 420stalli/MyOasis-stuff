package com.example.cirrus.repository;

import com.example.cirrus.entity.FruitSeller;
import com.example.cirrus.entity.Fruits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface FruitSellerRepository extends JpaRepository<FruitSeller,Long> {

    Optional<FruitSeller> findByFruitSellerId(Long fruitSellerId);

    Optional<FruitSeller> findFruitSellerByFirstName(String firstName);
    Optional<FruitSeller> findFruitSellerByLastName(String lastName);


    @Transactional
    void deleteById(Long fruitSellerId);
}
