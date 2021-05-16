package org.group10.reactive.microservices.rendezvous.animal;

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
class AnimalHandler {

    private final AnimalService animalService;

    Mono<ServerResponse> getById(ServerRequest r) {
        return defaultReadResponse(this.animalService.get(id(r)));
    }

    Mono<ServerResponse> all(ServerRequest r) {
        return defaultReadResponse(this.animalService.all());
    }

    Mono<ServerResponse> deleteById(ServerRequest r) {
        return defaultReadResponse(this.animalService.delete(id(r)));
    }

    Mono<ServerResponse> updateById(ServerRequest r) {
        Flux<Animal> id = r.bodyToFlux(Animal.class)
                .flatMap(p -> this.animalService.update(id(r), p.getNomAnimal(), p.getPrenomAnimal(), p.getParentID()));
        return defaultReadResponse(id);
    }

    Mono<ServerResponse> create(ServerRequest request) {
        Flux<Animal> flux = request
                .bodyToFlux(Animal.class)
                .flatMap(toWrite -> this.animalService.create(toWrite.getNomAnimal(), toWrite.getPrenomAnimal(), toWrite.getParentID()));
        return defaultWriteResponse(flux);
    }


    private static Mono<ServerResponse> defaultWriteResponse(Publisher<Animal> animal) {
        return Mono
                .from(animal)
                .flatMap(p -> ServerResponse
                        .created(URI.create("/api/animal/" + p.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .build()
                );
    }


    private static Mono<ServerResponse> defaultReadResponse(Publisher<Animal> animal) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(animal, Animal.class);
    }

    private static Long id(ServerRequest r) {
        return Long.parseUnsignedLong(r.pathVariable("id"));
    }
}
