package org.group10.reactive.microservices.prescription.prescription;

import reactor.core.publisher.Mono;

public interface IPrescriptionService {
    Mono<Boolean> prescriptionExistsById(Long prescriptionId);
}
