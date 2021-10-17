package org.dennis.pizzamanagement.controller;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.dennis.pizzamanagement.database.PizzaService;
import org.dennis.pizzamanagement.model.NewPizza;
import org.dennis.pizzamanagement.model.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MutationResolver implements GraphQLMutationResolver {

    @Autowired
    PizzaService pizzaService;

    public Pizza newPizza(NewPizza newPizza) {
        return pizzaService.create(newPizza).orElse(new Pizza());
    }

}
