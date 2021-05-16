package org.group10.reactive.microservices.rendezvous.medecin;

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
class MedecinEndpointConfiguration {

    @Bean("medecinRoutes")
    RouterFunction<ServerResponse> routes(MedecinHandler handler) {
        return route(GET("/api/medecin"), handler::all)
                .andRoute(GET("/api/medecin/{id}"), handler::getById)
                .andRoute(DELETE("/api/medecin/{id}"), handler::deleteById)
                .andRoute(POST("/api/medecin"), handler::create)
                .andRoute(PUT("/api/medecin/{id}"), handler::updateById);
    }
}
