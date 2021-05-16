package org.group10.reactive.microservices.rendezvous.animal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
class AnimalEndpointConfiguration {

    @Bean("animalRoutes")
    RouterFunction<ServerResponse> routes(AnimalHandler handler) {
        return route(GET("/api/animal"), handler::all)
                .andRoute(GET("/api/animal/{id}"), handler::getById)
                .andRoute(DELETE("/api/animal/{id}"), handler::deleteById)
                .andRoute(POST("/api/animal"), handler::create)
                .andRoute(PUT("/api/animal/{id}"), handler::updateById);
    }
}
