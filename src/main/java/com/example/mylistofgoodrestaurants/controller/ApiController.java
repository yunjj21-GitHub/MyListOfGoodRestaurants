package com.example.mylistofgoodrestaurants.controller;

import com.example.mylistofgoodrestaurants.MyRepository;
import com.example.mylistofgoodrestaurants.model.Restaurant;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.desktop.SystemEventListener;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiController {
    ArrayList<Restaurant> restaurants = new ArrayList<>();

    // 새로 등록하는 식당의 ID를 난수로 생성하기 위함
    Integer MIN = 0;
    Integer MAX = 2147483647;

    @Autowired
    private MyRepository myRepository;

    // GET (리소스 조회)
    // http://localhost:80/get-all-restaurants
    @GetMapping("/get-all-restaurants")
    public List<Restaurant> getAllRestaurants(){
        restaurants.clear();
        myRepository.findAll().forEach(restaurant -> {
            restaurants.add(new Restaurant(restaurant.getId(), restaurant.getName(), restaurant.getAddress(), restaurant.getStarPoint(), restaurant.getComment()));
        });
        return restaurants; // Java 객체를 던지고 Json 객체로 받는다. (유의해야 할 것은 Json 객체를 받으면 Java 객체로 변환해야 한다.)
    }

    // http://localhost:80/get-restaurants-by-name
    @GetMapping("/get-restaurants-by-name")
    public List<Restaurant> getRestaurantsByName(String name){
        return myRepository.findRestaurantsByName(name);
    }

    // POST (리소스 생성, 추가)
    // http://localhost:80/post-restaurant
    @PostMapping("/post-restaurant")
    public void postRestaurant(String jsonNewRestaurant){
        // JSON 객체를 Java 객체로 변환
        Gson gson = new Gson();
        Restaurant javaNewPerson = gson.fromJson(jsonNewRestaurant, Restaurant.class);

        // 식당의 ID 생성
        int id = (int)((Math.random() * (MAX - MIN)) + MIN);
        javaNewPerson.setId(id);

        myRepository.save(javaNewPerson);
    }

    // PUT (리소스 갱신, 생성(이미 존재하는 데이터면 갱신, 아니면 생성))
    // http://localhost:80/put-restaurant-by-id
    @PutMapping("/put-restaurant-by-id")
    public void putRestaurantById(String jsonNewRestaurant){
        // JSON 객체를 Java 객체로 변환
        Gson gson = new Gson();
        Restaurant javaNewRestaurant = gson.fromJson(jsonNewRestaurant, Restaurant.class);

        myRepository.updateRestaurantById(
                javaNewRestaurant.getId(),
                javaNewRestaurant.getName(),
                javaNewRestaurant.getAddress(),
                javaNewRestaurant.getStarPoint(),
                javaNewRestaurant.getComment()
        );
    }

    // DELETE (리소스 삭제)
    // http://localhost:80/delete-restaurant-by-id
    @DeleteMapping("/delete-restaurant-by-id")
    public void deleteRestaurantById(Integer id){
        myRepository.deleteRestaurantById(id);
    }
}
