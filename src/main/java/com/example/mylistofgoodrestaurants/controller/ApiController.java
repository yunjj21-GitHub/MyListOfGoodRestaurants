package com.example.mylistofgoodrestaurants.controller;

import com.example.mylistofgoodrestaurants.MyRepository;
import com.example.mylistofgoodrestaurants.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiController {
    ArrayList<Restaurant> restaurants = new ArrayList<>();

    @Autowired
    private MyRepository myRepository;

    // 모든 식당의 정보를 가져옴
    // http://43.200.83.7:80/get-all-restaurants
    @GetMapping("/get-all-restaurants")
    public List<Restaurant> getAllRestaurant(){
        restaurants.clear();
        myRepository.findAll().forEach(restaurant -> {
            restaurants.add(new Restaurant(restaurant.getId(), restaurant.getName(), restaurant.getAddress(), restaurant.getStarPoint(), restaurant.getComment()));
        });
        return restaurants; // Java 객체를 던지고 Json 객체로 받는다. (유의해야 할 것은 Json 객체를 받으면 Java 객체로 변환해야 한다.)
    }
}
