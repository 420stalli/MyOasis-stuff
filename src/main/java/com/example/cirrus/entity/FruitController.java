package com.example.cirrus.entity;

import com.example.cirrus.dto.FruitDto;
import com.example.cirrus.dto.FruitSellerDto;
import com.example.cirrus.repository.FruitsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path="api/v1/fruits")
@RequiredArgsConstructor
public class FruitController{


        private final FruitService fruitService;
        private final FruitsRepository fruitsRepository;


        @GetMapping("/get-fruits")
        public List<FruitDto> getFruits(){
            return fruitService.getFruits();
        }

//        @GetMapping("/search")
//        public List<FruitDto> getDtos(
//                @RequestParam String name,
//                @RequestParam Float price,
//                @RequestParam String country
//        ) {
//            List<Fruits> fruits =fruitsRepository.findAll();
//            if (fruits.contains(name)){
//                FruitDto fruitDto=new FruitDto();
//
//            }
//
//
//            return null;
//        }



        @GetMapping("/get-fruit-seller-and-fruits")
        public FruitSellerDto getFruitSellerAndFruits(@RequestParam Long id){
            return fruitService.getFruitSellerAndFruits(id);
        }

        @GetMapping("/get-fruits-by-country")
        public List<FruitDto> getFruitByCountry(@RequestParam String countryOfOrigin){
            return fruitService.getFruitsByCountry(countryOfOrigin);
        }

        @GetMapping("/get-cheaper-fruits")
        public List<FruitDto> getCheapFruit(@RequestParam Float price){
            return fruitService.getFruitsLessThan(price);
        }

        @GetMapping("/get-expensive-fruits")
        public List<FruitDto> getExpensiveFruit(@RequestParam Float price){
            return fruitService.getFruitsMoreThan(price);
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


