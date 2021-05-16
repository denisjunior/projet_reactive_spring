package org.group10.reactive.microservices.rendezvous.medecin;

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
class MedecinHandler {

    private final MedecinService medecinService;

    Mono<ServerResponse> getById(ServerRequest r) {
        return defaultReadResponse(this.medecinService.get(id(r)));
    }

    Mono<ServerResponse> all(ServerRequest r) {
        return defaultReadResponse(this.medecinService.all());
    }

    Mono<ServerResponse> deleteById(ServerRequest r) {
        return defaultReadResponse(this.medecinService.delete(id(r)));
    }

    Mono<ServerResponse> updateById(ServerRequest r) {
        Flux<Medecin> id = r.bodyToFlux(Medecin.class)
                .flatMap(p -> this.medecinService.update(id(r), p.getNom(), p.getPrenom(), p.getEmail(), p.getTelephone()));
        return defaultReadResponse(id);
    }

    Mono<ServerResponse> create(ServerRequest request) {
        Flux<Medecin> flux = request
                .bodyToFlux(Medecin.class)
                .flatMap(toWrite -> this.medecinService.create(toWrite.getNom(), toWrite.getPrenom(), toWrite.getEmail(), toWrite.getTelephone()));
        return defaultWriteResponse(flux);
    }


    private static Mono<ServerResponse> defaultWriteResponse(Publisher<Medecin> medecin) {
        return Mono
                .from(medecin)
                .flatMap(p -> ServerResponse
                        .created(URI.create("/api/medecin/" + p.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .build()
                );
    }


    private static Mono<ServerResponse> defaultReadResponse(Publisher<Medecin> medecin) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(medecin, Medecin.class);
    }

    private static Long id(ServerRequest r) {
        return Long.parseUnsignedLong(r.pathVariable("id"));
    }
}
