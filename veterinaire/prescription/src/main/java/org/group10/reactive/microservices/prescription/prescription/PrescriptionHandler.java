package org.group10.reactive.microservices.prescription.prescription;

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
class PrescriptionHandler {

    private final PrescriptionService prescriptionService;

    Mono<ServerResponse> getById(ServerRequest r) {
        return defaultReadResponse(this.prescriptionService.get(id(r)));
    }

    Mono<ServerResponse> all(ServerRequest r) {
        return defaultReadResponse(this.prescriptionService.all());
    }

    Mono<ServerResponse> deleteById(ServerRequest r) {
        return defaultReadResponse(this.prescriptionService.delete(id(r)));
    }

    Mono<ServerResponse> updateById(ServerRequest r) {
        Flux<Prescription> id = r.bodyToFlux(Prescription.class)
                .flatMap(p -> this.prescriptionService.update(id(r), p.getQuantite(), p.getPosologie(), p.getIdOrdonnance(), p.getIdMedicament()));
        return defaultReadResponse(id);
    }

    Mono<ServerResponse> create(ServerRequest request) {
        Flux<Prescription> flux = request
                .bodyToFlux(Prescription.class)
                .flatMap(toWrite -> this.prescriptionService.create(toWrite.getQuantite(), toWrite.getPosologie(), toWrite.getIdOrdonnance(), toWrite.getIdMedicament()));
        return defaultWriteResponse(flux);
    }


    private static Mono<ServerResponse> defaultWriteResponse(Publisher<Prescription> prescription) {
        return Mono
                .from(prescription)
                .flatMap(p -> ServerResponse
                        .created(URI.create("/api/prescription/" + p.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .build()
                );
    }


    private static Mono<ServerResponse> defaultReadResponse(Publisher<Prescription> prescription) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(prescription, Prescription.class);
    }

    private static Long id(ServerRequest r) {
        return Long.parseUnsignedLong(r.pathVariable("id"));
    }
}

