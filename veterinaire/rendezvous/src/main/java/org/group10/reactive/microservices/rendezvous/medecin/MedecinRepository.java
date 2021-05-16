package org.group10.reactive.microservices.rendezvous.medecin;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

interface MedecinRepository extends ReactiveCrudRepository<Medecin, Long> {
}