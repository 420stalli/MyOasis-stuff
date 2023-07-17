package com.example.cirrus;

import com.example.cirrus.entity.Fruits;
import com.example.cirrus.repository.FruitsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CirrusApplication {

    public static void main(String[] args) {
        SpringApplication.run(CirrusApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(FruitsRepository fruitsRepository){
//        return args -> {
//            Fruits Strawberry =new Fruits(
//                    "Strawberry",
//                    4.50F,
//                    "North America"
//
//            );
//            fruitsRepository.save(Strawberry);
//        };
//    }



}
