package org.group10.reactive.microservices.prescription.medicament;

import reactor.core.publisher.Mono;

public interface IMedicamentService {
    Mono<Boolean> medicamentExistsById(Long medicamantId);
}