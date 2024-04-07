package com.cydeo.controller;

import com.cydeo.bootstrap.DataGenerator;
import com.cydeo.enums.Cheese;
import com.cydeo.model.Pizza;
import com.cydeo.repository.PizzaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class DesignPizzaController {

    private PizzaRepository pizzaRepository;

    @PostMapping
    public String showDesignForm(@ModelAttribute ("cheeseList") List<Cheese> cheeseList,  Model model) {

        model.addAttribute("cheeses", DataGenerator.cheeseTypeList);
        model.addAttribute("sauces", DataGenerator.sauceTypeList);
        model.addAttribute("toppings", DataGenerator.toppingTypeList);

        model.addAttribute("cheeseList", cheeseList);
        return "/design";

    }

    @PostMapping("/createPizza")
    public String processPizza(Pizza pizza) {

        pizza.setId(UUID.randomUUID());
        pizzaRepository.createPizza(pizza);

        return "redirect:/orders/current?pizzaId=" + pizza.getId();

    }

}
