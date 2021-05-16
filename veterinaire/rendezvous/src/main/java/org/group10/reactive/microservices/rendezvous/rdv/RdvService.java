package org.group10.reactive.microservices.rendezvous.rdv;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@RequiredArgsConstructor
@Service
class RdvService implements IRdvService {

    private final RdvRepository rdvRepository;

    public Flux<Rdv> all() {
        return this.rdvRepository.findAll();
    }

    public Mono<Rdv> get(Long id) {
        return this.rdvRepository.findById(id);
    }

    public Mono<Rdv> update(Long id, String date, String heure, Long animalId, Long medecinId) {
        return this.rdvRepository
                .findById(id)
                .map(p -> {
                    p.setDaterdv(date);
                    p.setHeurerdv(heure);
                    p.setAnimalId(animalId);
                    p.setMedecinId(medecinId);
                    return p;
                })
                .flatMap(this.rdvRepository::save);
    }

    public Mono<Rdv> delete(Long id) {
        return this.rdvRepository
                .findById(id)
                .flatMap(p -> this.rdvRepository.deleteById(p.getId()).thenReturn(p));
    }

    public Mono<Rdv> create(String date, String heure, Long animalId, Long medecinId) {
        return this.rdvRepository
                .save(new Rdv(null, date, heure, animalId, medecinId));
    }

    @Override
    public Mono<Boolean> rdvExistsById(Long rdvId) {
        return this.rdvRepository.existsById(rdvId);
    }
}

