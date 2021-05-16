package org.group10.reactive.microservices.prescription.medicament;

import org.group10.reactive.microservices.prescription.medicament.Medicament;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

interface MedicamentRepository extends ReactiveCrudRepository<Medicament, Long> {
}