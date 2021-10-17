package org.dennis.pizzamanagement.database.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pizza {
    @Id
    private ObjectId _id;
    private int id;
    private String title, description, image;
    private double price;
}
