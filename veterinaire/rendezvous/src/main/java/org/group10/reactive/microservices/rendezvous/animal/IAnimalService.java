package org.group10.reactive.microservices.rendezvous.animal;

import reactor.core.publisher.Mono;

public interface IAnimalService {
    Mono<Boolean> animalExistsById(Long animalId);
}
