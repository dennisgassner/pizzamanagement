package org.dennis.pizzamanagement.model;

import lombok.Data;

@Data
public class NewPizza {

    private String title, description;
    private double price;
}
