package org.dennis.pizzamanagement.controller;

import org.dennis.pizzamanagement.database.PizzaService;
import org.dennis.pizzamanagement.model.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class QueryResolver
{

    @Autowired
    PizzaService pizzaService;

    @QueryMapping
    public List<Pizza> allPizzas() {
        return pizzaService.getAll();
    }

}
