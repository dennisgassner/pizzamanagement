package org.dennis.pizzamanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pizza {

    private int id;
    private String title, description, image;
    private double price;

    public static Pizza fromMongo(org.dennis.pizzamanagement.database.model.Pizza mongoPizza) {
        return new Pizza(mongoPizza.getId(), mongoPizza.getTitle(), mongoPizza.getDescription(), mongoPizza.getImage(), mongoPizza.getPrice());
    }

}
