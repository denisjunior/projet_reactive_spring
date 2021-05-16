package org.group10.reactive.microservices.rendezvous.rdv;

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
class RdvEndpointConfiguration {

    @Bean("rdvRoutes")
    RouterFunction<ServerResponse> routes(RdvHandler handler) {
        return route(GET("/api/rdv"), handler::all)
                .andRoute(GET("/api/rdv/{id}"), handler::getById)
                .andRoute(DELETE("/api/rdv/{id}"), handler::deleteById)
                .andRoute(POST("/api/rdv"), handler::create)
                .andRoute(PUT("/api/rdv/{id}"), handler::updateById);
    }
}
