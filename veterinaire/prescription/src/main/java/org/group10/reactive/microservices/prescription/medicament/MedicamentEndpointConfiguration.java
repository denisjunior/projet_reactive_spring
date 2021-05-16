package org.group10.reactive.microservices.prescription.medicament;

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
class MedicamentEndpointConfiguration {

    @Bean("medicamentRoutes")
    RouterFunction<ServerResponse> routes(MedicamentHandler handler) {
        return route(GET("/api/medicament"), handler::all)
                .andRoute(GET("/api/medicament/{id}"), handler::getById)
                .andRoute(DELETE("/api/medicament/{id}"), handler::deleteById)
                .andRoute(POST("/api/medicament"), handler::create)
                .andRoute(PUT("/api/medicament/{id}"), handler::updateById);
    }
}
