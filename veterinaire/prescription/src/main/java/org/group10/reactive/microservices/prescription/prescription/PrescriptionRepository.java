package org.group10.reactive.microservices.prescription.prescription;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

interface PrescriptionRepository extends ReactiveCrudRepository<Prescription, Long> {
}
