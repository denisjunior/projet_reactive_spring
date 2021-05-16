package org.group10.reactive.microservices.prescription.ordonnnance;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@RequiredArgsConstructor
@Service
class OrdonnanceService implements IOrdonnanceService {

    private final OrdonnanceRepository ordonnanceRepository;

    public Flux<Ordonnance> all() {
        return this.ordonnanceRepository.findAll();
    }

    public Mono<Ordonnance> get(Long id) {
        return this.ordonnanceRepository.findById(id);
    }

    public Mono<Ordonnance> update(Long id, Long idRdv) {
        return this.ordonnanceRepository
                .findById(id)
                .map(p -> {
                    p.setIdRvd(idRdv);
                    return p;
                })
                .flatMap(this.ordonnanceRepository::save);
    }

    public Mono<Ordonnance> delete(Long id) {
        return this.ordonnanceRepository
                .findById(id)
                .flatMap(p -> this.ordonnanceRepository.deleteById(p.getId()).thenReturn(p));
    }

    public Mono<Ordonnance> create(Long idRdv) {
        return this.ordonnanceRepository
                .save(new Ordonnance(null, idRdv));
    }

    @Override
    public Mono<Boolean> ordonnanceExistsById(Long rdvId) {
        return this.ordonnanceRepository.existsById(rdvId);
    }
}
