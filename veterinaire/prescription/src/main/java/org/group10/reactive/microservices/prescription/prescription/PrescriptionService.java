package org.group10.reactive.microservices.prescription.prescription;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@RequiredArgsConstructor
@Service
class PrescriptionService implements IPrescriptionService {

    private final PrescriptionRepository prescriptionRepository;

    public Flux<Prescription> all() {
        return this.prescriptionRepository.findAll();
    }

    public Mono<Prescription> get(Long id) {
        return this.prescriptionRepository.findById(id);
    }

    public Mono<Prescription> update(Long id, Long quantite, String posologie, Long idOrdonnance, Long idMedicament) {
        return this.prescriptionRepository
                .findById(id)
                .map(p -> {
                    p.setQuantite(quantite);
                    p.setPosologie(posologie);
                    p.setIdOrdonnance(idOrdonnance);
                    p.setIdMedicament(idMedicament);
                    return p;
                })
                .flatMap(this.prescriptionRepository::save);
    }

    public Mono<Prescription> delete(Long id) {
        return this.prescriptionRepository
                .findById(id)
                .flatMap(p -> this.prescriptionRepository.deleteById(p.getId()).thenReturn(p));
    }

    public Mono<Prescription> create(Long quantite, String posologie, Long idOrdonnance, Long idMedicament) {
        return this.prescriptionRepository
                .save(new Prescription(null, quantite, posologie, idOrdonnance, idMedicament));
    }

    @Override
    public Mono<Boolean> prescriptionExistsById(Long prescriptionId) {
        return this.prescriptionRepository.existsById(prescriptionId);
    }
}
