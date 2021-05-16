package org.group10.reactive.microservices.prescription.medicament;

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
class MedicamentHandler {

    private final MedicamentService medicamentService;

    Mono<ServerResponse> getById(ServerRequest r) {
        return defaultReadResponse(this.medicamentService.get(id(r)));
    }

    Mono<ServerResponse> all(ServerRequest r) {
        return defaultReadResponse(this.medicamentService.all());
    }

    Mono<ServerResponse> deleteById(ServerRequest r) {
        return defaultReadResponse(this.medicamentService.delete(id(r)));
    }

    Mono<ServerResponse> updateById(ServerRequest r) {
        Flux<Medicament> id = r.bodyToFlux(Medicament.class)
                .flatMap(p -> this.medicamentService.update(id(r), p.getNomMedicamant()));
        return defaultReadResponse(id);
    }

    Mono<ServerResponse> create(ServerRequest request) {
        Flux<Medicament> flux = request
                .bodyToFlux(Medicament.class)
                .flatMap(toWrite -> this.medicamentService.create(toWrite.getNomMedicamant()));
        return defaultWriteResponse(flux);
    }


    private static Mono<ServerResponse> defaultWriteResponse(Publisher<Medicament> medicament) {
        return Mono
                .from(medicament)
                .flatMap(p -> ServerResponse
                        .created(URI.create("/api/medicament/" + p.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .build()
                );
    }


    private static Mono<ServerResponse> defaultReadResponse(Publisher<Medicament> medicament) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(medicament, Medicament.class);
    }

    private static Long id(ServerRequest r) {
        return Long.parseUnsignedLong(r.pathVariable("id"));
    }
}
