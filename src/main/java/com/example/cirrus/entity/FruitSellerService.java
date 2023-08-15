package com.example.cirrus.entity;


import com.example.cirrus.dto.FruitDto;
import com.example.cirrus.dto.FruitSellerDto;
import com.example.cirrus.repository.FruitSellerRepository;
import com.example.cirrus.repository.FruitsRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FruitSellerService {

    private final FruitSellerRepository fruitSellerRepository;
    private final FruitsRepository fruitsRepository;

    public List<FruitSellerDto> getFruitSeller() {
        List<FruitSeller> fruitSellers = fruitSellerRepository.findAll();

        List<FruitSellerDto> dtos = new ArrayList<>();

        for (FruitSeller fruitSeller:fruitSellers){
            FruitSellerDto dto = new FruitSellerDto();
            dto.setFirstName(fruitSeller.getFirstName());
            dto.setLastName(fruitSeller.getLastName());
            dto.setPhoneNumber(fruitSeller.getPhoneNumber());
            dtos.add(dto);
        }
        return dtos;
    }


    public FruitSellerDto getFruitSellerByFirstName(String firstName) {
        List<FruitSeller> fruitSellers = fruitSellerRepository.findAll();

        FruitSellerDto dto = new FruitSellerDto();
        for (FruitSeller fruitSeller : fruitSellers) {
            if (fruitSeller.getFirstName().equals(firstName)) {
                dto.setFirstName(fruitSeller.getFirstName());
                dto.setLastName(fruitSeller.getLastName());
                dto.setPhoneNumber(fruitSeller.getPhoneNumber());

            }
            else{
                throw new IllegalStateException("fruit seller with the first name "+ firstName+" does not exist");
            }
        }
        return dto;
    }

    public FruitSellerDto getFruitSellerByLastName(String lastName) {
        List<FruitSeller> fruitSellers = fruitSellerRepository.findAll();

        FruitSellerDto dto = new FruitSellerDto();
        for (FruitSeller fruitSeller : fruitSellers) {
            if (fruitSeller.getLastName().equals(lastName)) {
                dto.setFirstName(fruitSeller.getFirstName());
                dto.setLastName(fruitSeller.getLastName());
                dto.setPhoneNumber(fruitSeller.getPhoneNumber());

            }
            else{
                throw new IllegalStateException("fruit seller with the last name "+ lastName+" does not exist");
            }

        }return dto;
    }

    public FruitSellerDto getFruitSellerByPhoneNumber(Integer phoneNumber) {
        List<FruitSeller> fruitSellers = fruitSellerRepository.findAll();

        FruitSellerDto dto = new FruitSellerDto();
        for (FruitSeller fruitSeller : fruitSellers) {
            if (fruitSeller.getPhoneNumber()==(phoneNumber)) {
                dto.setFirstName(fruitSeller.getFirstName());
                dto.setLastName(fruitSeller.getLastName());
                dto.setPhoneNumber(fruitSeller.getPhoneNumber());

            }
            else{
                throw new IllegalStateException("fruit seller with the number "+ phoneNumber+" does not exist");
            }

        }return dto;
    }



//    public FruitSellerDto getFruitSellerByFruit() {
//        List<FruitSeller> fruitSellers = fruitSellerRepository.findAll();
//
//        FruitSellerDto dto = new FruitSellerDto();
//
//        for (FruitSeller fruitSeller:fruitSellers){
//            for(Fruits fruits:fruitSeller.)
//
//            FruitSellerDto dto = new FruitSellerDto();
//            dto.setFirstName(fruitSeller.getFirstName());
//            dto.setLastName(fruitSeller.getLastName());
//            dto.setPhoneNumber(fruitSeller.getPhoneNumber());
//            dtos.add(dto);
//        }
//        return dtos;
//    }







    public String addNewFruitSeller(FruitSellerDto dto) {
        Optional<FruitSeller> fruitSellerOptional = fruitSellerRepository.
                findFruitSellerByFirstName(dto.getFirstName());
        Optional<FruitSeller> fruitSellerOptional1 = fruitSellerRepository.
                findFruitSellerByLastName(dto.getLastName());
        if (fruitSellerOptional.isPresent() && fruitSellerOptional1.isPresent()) {
            return "fruit seller already registered";
        }
        FruitSeller fruitSeller = new FruitSeller();
        fruitSeller.setFirstName(dto.getFirstName());
        fruitSeller.setLastName(dto.getLastName());
        fruitSeller.setPhoneNumber(dto.getPhoneNumber());
        createFruits(dto.getFruitDtos(), fruitSellerRepository.save(fruitSeller));

        return "fruit seller registered successfully";
    }

    private void createFruits(List<FruitDto> dtos, FruitSeller seller){
        for(FruitDto fruitDto:dtos){
            Fruits fruit= new Fruits();
            fruit.setName(fruitDto.getName());
            fruit.setPrice(fruitDto.getPrice());
            fruit.setCountryOfOrigin(fruitDto.getCountryOfOrigin());
            fruit.setFruitSeller(seller);
            fruitsRepository.save(fruit);
        }
    }

    public void deleteFruitSeller(Long fruitSellerId) {
        boolean exists = fruitSellerRepository.existsById(fruitSellerId);
        if (!exists) {
            throw new IllegalStateException("fruit seller with id " + fruitSellerId + " does not exist");
        }
        fruitSellerRepository.deleteById(fruitSellerId);
    }

    @Transactional
    public void updateFruitSeller(Long fruitSellerId,
                             String firstName,
                             String lastName,
                             Integer phoneNumber) {
        FruitSeller fruitSeller = fruitSellerRepository.findByFruitSellerId(fruitSellerId)
                .orElseThrow(() -> new IllegalStateException(
                        "fruit seller with id" + fruitSellerId + "does not exist"));

        if (firstName != null &&
                firstName.length() > 0 &&
                !Objects.equals(fruitSeller.getFirstName(), firstName)) {
            fruitSeller.setFirstName(firstName);

        }
        if (lastName != null &&
                lastName.length() > 0 &&
                !Objects.equals(fruitSeller.getLastName(), lastName)) {
            fruitSeller.setLastName(lastName);
        }
        if (phoneNumber != null&&
                !Objects.equals(fruitSeller.getPhoneNumber(), phoneNumber)){

            fruitSeller.setPhoneNumber(phoneNumber);
        }

    }


}
