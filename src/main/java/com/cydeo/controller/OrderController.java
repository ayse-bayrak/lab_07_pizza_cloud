package com.cydeo.controller;

import com.cydeo.exception.PizzaNotFoundException;
import com.cydeo.model.Pizza;
import com.cydeo.model.PizzaOrder;
import com.cydeo.repository.PizzaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/orders") // affect all the method inside the class, it is like a  categorization
public class OrderController {

    private final PizzaRepository pizzaRepository;

    public OrderController(PizzaRepository pizzaRepository) { // in the error page it is gonna be raplace as Paramater 0, with this way we figure out error
        this.pizzaRepository = pizzaRepository;
    }

    @GetMapping("/current")
    public String orderForm(@RequestParam UUID pizzaId, Model model) {

        PizzaOrder pizzaOrder = new PizzaOrder();
        // TODO fix the getPizza() method below in line 46. ->done
     pizzaOrder.setPizza(getPizza(pizzaId));

      model.addAttribute("pizzaOrder", pizzaOrder);

        return "/orderForm";
    }

    @PostMapping("/{pizzaId}")
    public String processOrder(@PathVariable UUID pizzaId,  PizzaOrder pizzaOrder) { // actually in here there is @ModelParameter, but after Spring 2.5 or 2.6 i don't need to put @ModelAttribute anymore

        // Saving the order

        pizzaOrder.setPizza(getPizza(pizzaId)); // i am doing again exactly same thing one more time like previous method
        // because i need to connect to selected pizza in the previous page (method)
        //pizzaOrder.setName("");
//pizzaOrder.setPizza(null); if there is no field in the form, this was like this
        System.out.println("Order is successfully saved");

        return "redirect:/home";
    }

    //TODO complete method -> done
    private Pizza getPizza(UUID pizzaId) throws PizzaNotFoundException {
        // Get the pizza.png from repository based on it's id -> done
        return pizzaRepository.findPizzaById(pizzaId);
              //  pizzaRepository.readAll().stream().filter(p->p.getId().equals(pizzaId)).findFirst().orElseThrow(()->new PizzaNotFoundException("Pizza Not Found"));
        //you can directly put here but in case we put repository part adding new method called findById it is best practice
    }

}
