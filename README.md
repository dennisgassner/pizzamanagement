# pizzamanagement

This Spring Boot Application provides a graphql interface to select a list of all stored pizzas and their detail information - which are defined by the requesting consumer due to / thanks to graphql. The interface also features to store new pizzas in the [mongo database](https://github.com/dennisgassner/deliverymongo).

Therefor the graphql schema defines a query `allPizzas` and a mutation `newPizza`

```
type Pizza {
  id: Int !
  title: String
  description: String
  price: Float
  image: String
}

input NewPizza {
  title: String !
  description: String !
  price: Float !
}

type Query {
    allPizzas: [Pizza]
}

type Mutation {
    newPizza(input:NewPizza): Pizza !
}
```

The applications uses the Spring Boot implementation of GraphQL Java, which can be found [here](https://github.com/spring-projects/spring-graphql). This project is under development, but it is already possible to use a comfortable way of testing the graphql interface via the `GraphQlTester`.

To create a docker image usable in the [Pizza Scenario](https://github.com/dennisgassner/pizza-delivery) just build it via the simple way described by [Spring Boot](https://spring.io/guides/topicals/spring-boot-docker/), so docker build `--build-arg JAR_FILE=target/*.jar -t orgdennis/pizzamgmt .`
