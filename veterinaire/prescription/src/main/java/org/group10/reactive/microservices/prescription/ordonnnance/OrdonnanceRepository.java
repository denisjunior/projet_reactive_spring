package org.group10.reactive.microservices.prescription.ordonnnance;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

interface OrdonnanceRepository extends ReactiveCrudRepository<Ordonnance, Long> {
}
