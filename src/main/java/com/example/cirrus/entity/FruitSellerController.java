package com.example.cirrus.entity;


import com.example.cirrus.dto.FruitSellerDto;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;

@RestController
@RequestMapping(path="api/v1/fruit-seller")
@RequiredArgsConstructor

public class FruitSellerController {

    private final FruitSellerService fruitSellerService;
    private final EntityManager entityManager;



    @GetMapping("/search")
    public List<FruitSeller> getFruitSeller(
            @RequestParam Optional<String> firstName,
            @RequestParam Optional<String> lastName,
            @RequestParam Optional<Integer> phoneNumber
    ){
        JPAQuery<?> query = new JPAQuery<>(entityManager);

        JPAQuery<FruitSeller> fruitSellerJPAQuery = (JPAQuery<FruitSeller>) query.from(QFruitSeller.fruitSeller);

        if(firstName.isPresent()){
            fruitSellerJPAQuery.where(QFruitSeller.fruitSeller.firstName.equalsIgnoreCase(firstName.get()));
        }
        if(lastName.isPresent()){
            fruitSellerJPAQuery.where(QFruitSeller.fruitSeller.lastName.equalsIgnoreCase(lastName.get()));
        }
        if(phoneNumber.isPresent()){
            fruitSellerJPAQuery.where(QFruitSeller.fruitSeller.phoneNumber.eq(phoneNumber.get()));
        }
        return fruitSellerJPAQuery.fetch();
    }



    @PostMapping("add-fruit-seller")
    public ResponseEntity<?> registerNewFruitSeller(@RequestBody FruitSellerDto dto){
        return ResponseEntity.ok(fruitSellerService.addNewFruitSeller(dto));
    }

    @DeleteMapping(path="{fruitSellerId}")
    public void deleteFruitSeller(@PathVariable("fruitSellerId") Long fruitSellerId){
        fruitSellerService.deleteFruitSeller(fruitSellerId);

    }

    @PutMapping(path="{fruitSellerId}")
    public void updateFruitSeller(
            @PathVariable("fruitSellerId") Long fruitSellerId,
            @RequestParam(required=false) String firstName,
            @RequestParam(required=false) String lastName,
            Integer phoneNumber)
    {
        fruitSellerService.updateFruitSeller(fruitSellerId, firstName, lastName, phoneNumber);
    }



}
