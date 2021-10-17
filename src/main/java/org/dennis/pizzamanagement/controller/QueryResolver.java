package org.dennis.pizzamanagement.controller;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.dennis.pizzamanagement.database.PizzaService;
import org.dennis.pizzamanagement.model.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryResolver implements GraphQLQueryResolver {

    @Autowired
    PizzaService pizzaService;

    public List<Pizza> allPizzas() {
        return pizzaService.getAll();
    }

}
