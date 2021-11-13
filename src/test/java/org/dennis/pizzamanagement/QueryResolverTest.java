package org.dennis.pizzamanagement;


import org.dennis.pizzamanagement.controller.QueryResolver;
import org.dennis.pizzamanagement.database.PizzaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.boot.test.GraphQlTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import java.util.List;

import static org.mockito.BDDMockito.given;


@GraphQlTest(QueryResolver.class)
class QueryResolverTest {

    private final String query = "{\n" +
            "          allPizzas {\n" +
            "            id, title, description, price     \n" +
            "          }\n" +
            "        }";

    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    PizzaService pizzaServiceMock;

    @Test
    public void testAllPizzas() {
        given(this.pizzaServiceMock.getAll()).willReturn(
                List.of(org.dennis.pizzamanagement.model.Pizza.builder().id(1).price(1.2).title("dummy").build()));

        this.graphQlTester.query(query).execute().path("allPizzas[0].id").pathExists();
    }





}
