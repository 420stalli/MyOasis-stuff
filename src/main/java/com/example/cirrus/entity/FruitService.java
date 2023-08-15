package com.example.cirrus.entity;

import com.example.cirrus.dto.FruitDto;
import com.example.cirrus.dto.FruitSellerDto;
import com.example.cirrus.repository.FruitSellerRepository;
import com.example.cirrus.repository.FruitsRepository;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class FruitService {

    private final FruitsRepository fruitsRepository;
    private final FruitSellerRepository fruitSellerRepository;
    private final EntityManager entityManager;

    public List<FruitDto> getFruits() {
        JPAQuery<?> query = new JPAQuery<>(entityManager);

        List<Fruits> fruits1 = query.select(QFruits.fruits).from(QFruits.fruits).fetch();
        log.info("============================ fruit name : "+fruits1.get(0).getName());

        List<FruitDto> dtos = new ArrayList<>();

        for (Fruits fruit:fruits1){
            FruitDto dto = new FruitDto();
            dto.setName(fruit.getName());
            dto.setPrice(fruit.getPrice());
            dto.setCountryOfOrigin(fruit.getCountryOfOrigin());
            dtos.add(dto);
        }
       return dtos;
    }

    public List<FruitDto> getFruitsByCountry(String countryOfOrigin) {
        JPAQuery<?> query = new JPAQuery<>(entityManager);

        List<Fruits> fruits1 = query.select(QFruits.fruits).from(QFruits.fruits).where(QFruits.fruits.countryOfOrigin.eq(countryOfOrigin)).fetch();

        List<FruitDto> dtos = new ArrayList<>();

        for (Fruits fruit:fruits1) {
                FruitDto dto = new FruitDto();
                dto.setName(fruit.getName());
                dto.setPrice(fruit.getPrice());
                dto.setCountryOfOrigin(fruit.getCountryOfOrigin());
                dtos.add(dto);
            }
        return dtos;
        }


    public Fruits getFruitsLessThan(Float price) {

        JPAQuery<?> query = new JPAQuery<>(entityManager);
        List<Fruits> fruits1 = new ArrayList<>();
        fruits1 = query.select(QFruits.fruits).from(QFruits.fruits).where(QFruits.fruits.price.between(0,price)).orderBy(QFruits.fruits.id.desc()).fetch();

        log.info(fruits1.get(0).getName());
//
//        if (fruits1.isEmpty()){
//            return new ArrayList<>();
//        }


        List<FruitDto> dtos = new ArrayList<>();

        for (Fruits fruit : fruits1) {
                FruitDto dto = new FruitDto();
                dto.setName(fruit.getName());
                dto.setPrice(fruit.getPrice());
                dto.setCountryOfOrigin(fruit.getCountryOfOrigin());
                dtos.add(dto);

        }
        return fruits1.get(0);
    }



    @Transactional
    public String addNewFruit(FruitDto dto) {
        Fruits fruits = new Fruits();
        fruits.setPrice(dto.getPrice());
        fruits.setName(dto.getName());
        fruits.setCountryOfOrigin(dto.getCountryOfOrigin());
        fruitsRepository.save(fruits);
        return "fruit created successfully";
    }

    public void deleteFruits(Long fruitsId) {
        boolean exists = fruitsRepository.existsById(fruitsId);
        if (!exists) {
            throw new IllegalStateException("fruit with id " + fruitsId + " does not exist");
        }
        fruitsRepository.deleteById(fruitsId);
    }

    @Transactional
    public void updateFruits(Long fruitsId,
                             Long fruitSellerId,
                             String name,
                             String countryOfOrigin,
                             Float price) {
        Fruits fruits = fruitsRepository.findById(fruitsId)
                .orElseThrow(() -> new IllegalStateException(
                        "fruit with id" + fruitsId + "does not exist"));

        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(fruits.getName(), name)) {
            fruits.setName(name);

        }
        if (price != null&&
                !Objects.equals(fruits.getPrice(), price)){

            fruits.setPrice(price);
        }

        if (countryOfOrigin != null &&
                countryOfOrigin.length() > 0 &&
                !Objects.equals(fruits.getCountryOfOrigin(), countryOfOrigin)) {
            fruits.setCountryOfOrigin(countryOfOrigin);
        }



    }

    @Transactional
    public FruitSellerDto getFruitSellerAndFruits(Long id){
        FruitSeller seller = fruitSellerRepository.findByFruitSellerId(id).orElseThrow(RuntimeException::new);
        List<Fruits> fruits = fruitsRepository.findFruitsByFruitSeller(seller);

        List<FruitDto> dtos = new ArrayList<>();

        for (Fruits fruit:fruits){
            FruitDto fruitDto = new FruitDto();
            fruitDto.setName(fruit.getName());
            fruitDto.setPrice(fruit.getPrice());
            fruitDto.setCountryOfOrigin(fruit.getCountryOfOrigin());

            dtos.add(fruitDto);

        }

        FruitSellerDto dto = new FruitSellerDto();
        dto.setFirstName(seller.getFirstName());
        dto.setLastName(seller.getLastName());
        dto.setPhoneNumber(seller.getPhoneNumber());
        dto.setFruitDtos(dtos);

        return dto;
    }
    @Transactional
    public String addSellerId(Long id, Long fruitId){
        Optional<FruitSeller> seller =  fruitSellerRepository.findByFruitSellerId(id);
        Optional<Fruits> fruit = fruitsRepository.findById(fruitId);

        if(seller.isPresent() && fruit.isPresent()){
          fruit.get().setFruitSeller(seller.get());
          fruitsRepository.save(fruit.get());
          return "fruit updated successfully";
        } else if (seller.isEmpty()){
            return "seller Id is invalid";
        } else {
            return "fruit id is invalid";
        }
    }

}

