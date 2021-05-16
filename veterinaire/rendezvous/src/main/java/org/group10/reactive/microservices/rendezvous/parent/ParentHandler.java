package org.group10.reactive.microservices.rendezvous.parent;

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
class ParentHandler {

    private final ParentService parentService;

    Mono<ServerResponse> getById(ServerRequest r) {
        return defaultReadResponse(this.parentService.get(id(r)));
    }

    Mono<ServerResponse> all(ServerRequest r) {
        return defaultReadResponse(this.parentService.all());
    }

    Mono<ServerResponse> deleteById(ServerRequest r) {
        return defaultReadResponse(this.parentService.delete(id(r)));
    }

    Mono<ServerResponse> updateById(ServerRequest r) {
        Flux<Parent> id = r.bodyToFlux(Parent.class)
                .flatMap(p -> this.parentService.update(id(r), p.getNomParent(), p.getPrenomParent(), p.getEmailParent(), p.getTelParent()));
        return defaultReadResponse(id);
    }

    Mono<ServerResponse> create(ServerRequest request) {
        Flux<Parent> flux = request
                .bodyToFlux(Parent.class)
                .flatMap(toWrite -> this.parentService.create(toWrite.getNomParent(), toWrite.getPrenomParent(), toWrite.getEmailParent(), toWrite.getTelParent()));
        return defaultWriteResponse(flux);
    }


    private static Mono<ServerResponse> defaultWriteResponse(Publisher<Parent> parent) {
        return Mono
                .from(parent)
                .flatMap(p -> ServerResponse
                        .created(URI.create("/api/parent/" + p.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .build()
                );
    }


    private static Mono<ServerResponse> defaultReadResponse(Publisher<Parent> parent) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(parent, Parent.class);
    }

    private static Long id(ServerRequest r) {
        return Long.parseUnsignedLong(r.pathVariable("id"));
    }
}
