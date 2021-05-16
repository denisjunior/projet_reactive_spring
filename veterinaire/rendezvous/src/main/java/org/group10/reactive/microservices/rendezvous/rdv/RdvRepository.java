package org.group10.reactive.microservices.rendezvous.rdv;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

interface RdvRepository extends ReactiveCrudRepository<Rdv, Long> {
}
