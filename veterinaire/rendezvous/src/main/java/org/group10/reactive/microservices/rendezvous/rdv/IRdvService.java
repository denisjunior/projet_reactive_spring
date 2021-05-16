package org.group10.reactive.microservices.rendezvous.rdv;

import reactor.core.publisher.Mono;

public interface IRdvService {
    Mono<Boolean> rdvExistsById(Long rdvId);
}