package org.dennis.pizzamanagement.database;

import org.dennis.pizzamanagement.database.model.Pizza;
import org.dennis.pizzamanagement.model.NewPizza;
import org.bson.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PizzaService {

    @Autowired
    MongoTemplate mongoTemplate;

    final String PIZZA_COLLECTION = "pizzas";

    public List<org.dennis.pizzamanagement.model.Pizza> getAll() {
        return mongoTemplate.findAll(Pizza.class, PIZZA_COLLECTION).stream().
                map(p -> org.dennis.pizzamanagement.model.Pizza.fromMongo(p))
                .collect(Collectors.toList());
    }

    public Pizza getById(int id) {
        Query customId_is_id = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(customId_is_id, Pizza.class, PIZZA_COLLECTION);
    }

    private int getNextCustomId() {
        return (int) mongoTemplate.getCollection(PIZZA_COLLECTION).find().sort(new JsonObject("{\"id\":-1}")).limit(1).first().get("id")+1;
    }

    public Optional<org.dennis.pizzamanagement.model.Pizza> create(NewPizza newPizza) {
        Optional<org.dennis.pizzamanagement.model.Pizza> result = Optional.empty();
        if(getAll().stream().filter(p->p.getTitle().equals(newPizza.getTitle())).findAny().isEmpty()) {
            Pizza mongoPizza = Pizza.builder().title(newPizza.getTitle()).description(newPizza.getDescription()).price(newPizza.getPrice())
                    .id(getNextCustomId())
                    .image(getById(1).getImage()).build();
            mongoTemplate.insert(mongoPizza, PIZZA_COLLECTION);
            result = Optional.of(org.dennis.pizzamanagement.model.Pizza.fromMongo(mongoPizza));
        }
        return result;
    }

}
