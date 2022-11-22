package com.example.mylistofgoodrestaurants;

import com.example.mylistofgoodrestaurants.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyRepository extends JpaRepository<Restaurant, Integer> {

}
