package org.dennis.pizzamanagement;

import org.dennis.pizzamanagement.controller.MutationResolver;

import org.dennis.pizzamanagement.database.PizzaService;
import org.dennis.pizzamanagement.model.NewPizza;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.boot.test.GraphQlTest;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@GraphQlTest(MutationResolver.class)
public class MutationResolverTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    PizzaService pizzaServiceMock;

    final String pizzaName="dummy";

    GraphQlTester.ResponseSpec responseSpec;

    @BeforeEach
    public void doQuery() {
        given(this.pizzaServiceMock.create(any(NewPizza.class))).
                willReturn(Optional.of(org.dennis.pizzamanagement.model.Pizza.builder().id(1).price(1.2).title(pizzaName).build()));
        String query = "mutation {\n" +
                "        newPizza(input: {title: \""+pizzaName+"\", description: \"dummyDescription\", price: 1.2}) {\n" +
                "          id\n" +
                "          title\n" +
                "          description\n" +
                "          price\n" +
                "        }\n" +
                "      }";
        responseSpec = this.graphQlTester.query(query).//variable("input",input).
                execute();
    }

    @Test
    public void testMutationTitle() {
        responseSpec.path("newPizza.title[0]").equals(pizzaName);
    }

    @Test
    public void testMutationErrors() {
        responseSpec.errors().verify();
    }

    @Test
    public void testMutationId() {
        responseSpec.path("newPizza.id").valueExists();
    }
}
