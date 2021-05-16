package org.group10.reactive.microservices.rendezvous.parent;


import org.springframework.data.repository.reactive.ReactiveCrudRepository;

interface ParentRepository extends ReactiveCrudRepository<Parent, Long> {
}
