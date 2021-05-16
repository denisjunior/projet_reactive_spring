package org.group10.reactive.microservices.rendezvous.animal;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

interface AnimalRepository extends ReactiveCrudRepository<Animal, Long> {
}