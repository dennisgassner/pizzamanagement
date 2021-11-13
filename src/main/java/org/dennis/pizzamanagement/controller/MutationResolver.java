package org.dennis.pizzamanagement.controller;

import org.dennis.pizzamanagement.database.PizzaService;
import org.dennis.pizzamanagement.model.NewPizza;
import org.dennis.pizzamanagement.model.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MutationResolver {

    @Autowired
    PizzaService pizzaService;

    @MutationMapping
    public Pizza newPizza(@Argument NewPizza newPizza) {
        return pizzaService.create(newPizza).orElse(new Pizza());
    }

}
