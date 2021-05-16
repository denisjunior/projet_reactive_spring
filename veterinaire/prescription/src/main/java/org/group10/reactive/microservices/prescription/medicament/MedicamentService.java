package org.group10.reactive.microservices.prescription.medicament;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@RequiredArgsConstructor
@Service
class MedicamentService implements IMedicamentService {

    private final MedicamentRepository medicamentRepository;

    public Flux<Medicament> all() {
        return this.medicamentRepository.findAll();
    }

    public Mono<Medicament> get(Long id) {
        return this.medicamentRepository.findById(id);
    }

    public Mono<Medicament> update(Long id, String nomMedicament) {
        return this.medicamentRepository
                .findById(id)
                .map(p -> {
                    p.setNomMedicamant(nomMedicament);
                    return p;
                })
                .flatMap(this.medicamentRepository::save);
    }

    public Mono<Medicament> delete(Long id) {
        return this.medicamentRepository
                .findById(id)
                .flatMap(p -> this.medicamentRepository.deleteById(p.getId()).thenReturn(p));
    }

    public Mono<Medicament> create(String nomMedicament) {
        return this.medicamentRepository
                .save(new Medicament(null, nomMedicament));
    }

    @Override
    public Mono<Boolean> medicamentExistsById(Long medicamentId) {
        return this.medicamentRepository.existsById(medicamentId);
    }
}