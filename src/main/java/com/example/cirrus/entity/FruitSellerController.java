package com.example.cirrus.entity;


import com.example.cirrus.dto.FruitDto;
import com.example.cirrus.dto.FruitSellerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/fruit-seller")

public class FruitSellerController {

    private final FruitSellerService fruitSellerService;
    @Autowired
    public FruitSellerController(FruitSellerService fruitSellerService) {
        this.fruitSellerService = fruitSellerService;
    }


    @GetMapping("/get-fruit-seller")
    public List<FruitSellerDto> getFruitSeller(){
        return fruitSellerService.getFruitSeller();
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
