package org.group10.reactive.microservices.rendezvous.parent;

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
class ParentEndpointConfiguration {

    @Bean("parentRoutes")
    RouterFunction<ServerResponse> routes(ParentHandler handler) {
        return route(GET("/api/parent"), handler::all)
                .andRoute(GET("/api/parent/{id}"), handler::getById)
                .andRoute(DELETE("/api/parent/{id}"), handler::deleteById)
                .andRoute(POST("/api/parent"), handler::create)
                .andRoute(PUT("/api/parent/{id}"), handler::updateById);
    }
}
