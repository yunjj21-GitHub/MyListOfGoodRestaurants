package com.example.mylistofgoodrestaurants.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    // http://43.200.83.7:80/
    @RequestMapping("/")
    public String main(){
        return "index.html";
    }
}
