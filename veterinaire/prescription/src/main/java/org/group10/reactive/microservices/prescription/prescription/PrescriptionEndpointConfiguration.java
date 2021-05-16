package org.group10.reactive.microservices.prescription.prescription;

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
class PrescriptionEndpointConfiguration {

    @Bean("prescriptionRoutes")
    RouterFunction<ServerResponse> routes(PrescriptionHandler handler) {
        return route(GET("/api/prescription"), handler::all)
                .andRoute(GET("/api/prescription/{id}"), handler::getById)
                .andRoute(DELETE("/api/prescription/{id}"), handler::deleteById)
                .andRoute(POST("/api/prescription"), handler::create)
                .andRoute(PUT("/api/prescription/{id}"), handler::updateById);
    }
}