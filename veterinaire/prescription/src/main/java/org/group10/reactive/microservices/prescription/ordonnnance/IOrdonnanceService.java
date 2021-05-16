package org.group10.reactive.microservices.prescription.ordonnnance;

import reactor.core.publisher.Mono;

public interface IOrdonnanceService {
    Mono<Boolean> ordonnanceExistsById(Long ordonnanceId);
}
