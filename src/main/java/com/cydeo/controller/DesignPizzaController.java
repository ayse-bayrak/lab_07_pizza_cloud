package com.cydeo.controller;

import com.cydeo.bootstrap.DataGenerator;
import com.cydeo.model.Pizza;
import com.cydeo.repository.PizzaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/design") // adding class endpoint
public class DesignPizzaController {

    private final PizzaRepository pizzaRepository;
    // to do dependency injection we need constructor in here,
    // because error says pizzaRepository is null and
    // we look at here

    public DesignPizzaController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @GetMapping  // edit @PostMapping to @GetMapping // localhost:8080/design
    public String showDesignForm(Model model) {

        model.addAttribute("pizza", new Pizza());
        model.addAttribute("cheeses", DataGenerator.cheeseTypeList);
        model.addAttribute("sauces", DataGenerator.sauceTypeList);
        model.addAttribute("toppings", DataGenerator.toppingTypeList);
        return "/design";
    }

    @PostMapping("/createPizza")  //localhost:8080/design/createPizza
    public String processPizza(  Pizza pizza) {
        pizza.setId(UUID.randomUUID());
        pizzaRepository.createPizza(pizza); // to add pizzaList
        //null.createPizza() it is not possible..

        return "redirect:/orders/current?pizzaId=" + pizza.getId();
    }

}
