package org.group10.reactive.microservices.rendezvous.rdv;

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
class RdvHandler {

    private final RdvService rdvService;

    Mono<ServerResponse> getById(ServerRequest r) {
        return defaultReadResponse(this.rdvService.get(id(r)));
    }

    Mono<ServerResponse> all(ServerRequest r) {
        return defaultReadResponse(this.rdvService.all());
    }

    Mono<ServerResponse> deleteById(ServerRequest r) {
        return defaultReadResponse(this.rdvService.delete(id(r)));
    }

    Mono<ServerResponse> updateById(ServerRequest r) {
        Flux<Rdv> id = r.bodyToFlux(Rdv.class)
                .flatMap(p -> this.rdvService.update(id(r), p.getDaterdv(), p.getHeurerdv(), p.getAnimalId(), p.getMedecinId()));
        return defaultReadResponse(id);
    }

    Mono<ServerResponse> create(ServerRequest request) {
        Flux<Rdv> flux = request
                .bodyToFlux(Rdv.class)
                .flatMap(toWrite -> this.rdvService.create(toWrite.getDaterdv(), toWrite.getHeurerdv(), toWrite.getAnimalId(), toWrite.getMedecinId()));
        return defaultWriteResponse(flux);
    }


    private static Mono<ServerResponse> defaultWriteResponse(Publisher<Rdv> rdv) {
        return Mono
                .from(rdv)
                .flatMap(p -> ServerResponse
                        .created(URI.create("/api/rdv/" + p.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .build()
                );
    }


    private static Mono<ServerResponse> defaultReadResponse(Publisher<Rdv> rdv) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(rdv, Rdv.class);
    }

    private static Long id(ServerRequest r) {
        return Long.parseUnsignedLong(r.pathVariable("id"));
    }
}

