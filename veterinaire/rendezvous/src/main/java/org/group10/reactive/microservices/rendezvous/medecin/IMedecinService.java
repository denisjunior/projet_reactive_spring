package org.group10.reactive.microservices.rendezvous.medecin;

import reactor.core.publisher.Mono;

public interface IMedecinService {
    Mono<Boolean> medecinExistsById(Long medecinId);
}
