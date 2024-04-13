package com.cydeo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // we are putting it in the URL directly, it should be GetMapping
    @GetMapping({"/","/home"}) //localhost:8080/
    public String getHome(){

        return "/home";
    }

}
