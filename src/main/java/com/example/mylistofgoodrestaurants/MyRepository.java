package com.example.mylistofgoodrestaurants;

import com.example.mylistofgoodrestaurants.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MyRepository extends JpaRepository<Restaurant, Integer> {
    // GET
    @Query(value = "select * from restaurant where restaurant.name= :name", nativeQuery = true)
    List<Restaurant> findRestaurantsByName(String name);

    // POST

    // PUT
    @Modifying
    @Transactional
    @Query(value = "update restaurant set restaurant.name= :name, restaurant.address= :address, restaurant.star_point= :starPoint, restaurant.comment= :comment where restaurant.id= :id", nativeQuery = true)
    void updateRestaurantById(Integer id, String name, String address, Integer starPoint, String comment);

    // DELETE
    @Modifying
    @Transactional
    @Query(value = "delete from restaurant where restaurant.id= :id", nativeQuery = true)
    void deleteRestaurantById(Integer id);
}
