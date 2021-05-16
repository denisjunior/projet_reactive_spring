package org.group10.reactive.microservices.prescription.ordonnnance;

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
class OrdonnanceEndpointConfiguration {

    @Bean("ordonnanceRoutes")
    RouterFunction<ServerResponse> routes(OrdonnanceHandler handler) {
        return route(GET("/api/ordonnance"), handler::all)
                .andRoute(GET("/api/ordonnance/{id}"), handler::getById)
                .andRoute(DELETE("/api/ordonnance/{id}"), handler::deleteById)
                .andRoute(POST("/api/ordonnance"), handler::create)
                .andRoute(PUT("/api/ordonnance/{id}"), handler::updateById);
    }
}
