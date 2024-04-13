package com.cydeo.repository;

import com.cydeo.model.Pizza;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class PizzaRepository {

    private static List<Pizza> pizzaList = new ArrayList<>();

    public Pizza createPizza(Pizza pizzaToSave) {
        pizzaToSave.setId(UUID.randomUUID());
        pizzaList.add(pizzaToSave);
        return pizzaToSave;
    }

    public List<Pizza> readAll() {
        return pizzaList;
    }

    // TODO complete method --> done
    public Pizza findPizzaById(UUID uuid) {
        return pizzaList.stream().filter(p->p.getId().equals(uuid)).findFirst().orElseThrow();
    }

}
