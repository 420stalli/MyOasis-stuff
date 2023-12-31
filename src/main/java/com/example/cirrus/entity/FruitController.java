package com.example.cirrus.entity;

import com.example.cirrus.dto.FruitDto;
import com.example.cirrus.dto.FruitSellerDto;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path="api/v1/fruits")
@RequiredArgsConstructor
public class FruitController{


        private final FruitService fruitService;
        private final EntityManager entityManager;


        @GetMapping("/search")
        public List<Fruits> getFruitsT(
                @RequestParam Optional<String> name,
                @RequestParam Optional<Float> price,
                @RequestParam Optional<String> countryOfOrigin
        ) {

            JPAQuery<?> query = new JPAQuery<>(entityManager);

            JPAQuery<Fruits> fruitsJPAQuery = (JPAQuery<Fruits>) query.from(QFruits.fruits);

            if(name.isPresent()){
                fruitsJPAQuery.where(QFruits.fruits.name.equalsIgnoreCase(name.get()));
            }
            if(price.isPresent()){
                fruitsJPAQuery.where(QFruits.fruits.price.eq(price.get()));
            }
            if(countryOfOrigin.isPresent()){
                fruitsJPAQuery.where(QFruits.fruits.countryOfOrigin.equalsIgnoreCase(countryOfOrigin.get()));
            }
            return fruitsJPAQuery.fetch();
        }
        @GetMapping("/search-ranges")
        public List<Fruits> getFruitsT(@RequestParam Optional<String> priceLevel,
                                       @RequestParam Optional<Float> price){
            JPAQuery<?> query = new JPAQuery<>(entityManager);
            JPAQuery<Fruits> fruitsJPAQuery = (JPAQuery<Fruits>) query.from(QFruits.fruits);

            if(priceLevel.get().equals("expensive")){
                fruitsJPAQuery.where(QFruits.fruits.price.between(price.get(), 1000));
            }
            if(priceLevel.get().equals("cheaper")) {
                fruitsJPAQuery.where(QFruits.fruits.price.between(0, price.get()));
            }
            return fruitsJPAQuery.fetch();
        }


        @GetMapping("/get-fruit-seller-and-fruits")
        public FruitSellerDto getFruitSellerAndFruits(@RequestParam Long id){
            return fruitService.getFruitSellerAndFruits(id);
        }



        @PostMapping("/add-fruits")
        public ResponseEntity<?> registerNewFruits(@RequestBody FruitDto dto){
            return ResponseEntity.ok(fruitService.addNewFruit(dto));
        }

        @DeleteMapping(path="{fruitsId}")
        public void deleteFruits(@PathVariable("fruitsId") Long fruitsId){
            fruitService.deleteFruits(fruitsId);

        }
        @PutMapping(path="{fruitsId}")
            public void updateFruits(
                @PathVariable("fruitsId") Long fruitsId,
                @RequestParam(required = false) Long fruitSellerId,
                @RequestParam(required=false) String name,
                @RequestParam(required=false) String countryOfOrigin,
                @RequestParam(required=false) Float price) {
            fruitService.updateFruits(fruitsId, fruitSellerId, name, countryOfOrigin, price);
        }

        @PutMapping("/add-seller-id")
            public ResponseEntity<?> addSellerId(@RequestParam Long sellerId,
                                    @RequestParam Long fruitId){
                return ResponseEntity.ok(fruitService.addSellerId(sellerId, fruitId));
            }
}
