package org.group10.reactive.microservices.prescription.ordonnnance;

import lombok.AllArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@AllArgsConstructor
@Component
class OrdonnanceHandler {

    private final OrdonnanceService ordonnanceService;

    Mono<ServerResponse> getById(ServerRequest r) {
        return defaultReadResponse(this.ordonnanceService.get(id(r)));
    }

    Mono<ServerResponse> all(ServerRequest r) {
        return defaultReadResponse(this.ordonnanceService.all());
    }

    Mono<ServerResponse> deleteById(ServerRequest r) {
        return defaultReadResponse(this.ordonnanceService.delete(id(r)));
    }

    Mono<ServerResponse> updateById(ServerRequest r) {
        Flux<Ordonnance> id = r.bodyToFlux(Ordonnance.class)
                .flatMap(p -> this.ordonnanceService.update(id(r), p.getIdRvd()));
        return defaultReadResponse(id);
    }

    Mono<ServerResponse> create(ServerRequest request) {
        Flux<Ordonnance> flux = request
                .bodyToFlux(Ordonnance.class)
                .flatMap(toWrite -> this.ordonnanceService.create(toWrite.getIdRvd()));
        return defaultWriteResponse(flux);
    }


    private static Mono<ServerResponse> defaultWriteResponse(Publisher<Ordonnance> ordonnance) {
        return Mono
                .from(ordonnance)
                .flatMap(p -> ServerResponse
                        .created(URI.create("/api/ordonnance/" + p.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .build()
                );
    }


    private static Mono<ServerResponse> defaultReadResponse(Publisher<Ordonnance> ordonnance) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(ordonnance, Ordonnance.class);
    }

    private static Long id(ServerRequest r) {
        return Long.parseUnsignedLong(r.pathVariable("id"));
    }
}
