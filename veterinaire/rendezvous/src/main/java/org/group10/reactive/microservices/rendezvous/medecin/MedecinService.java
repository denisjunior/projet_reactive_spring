package org.group10.reactive.microservices.rendezvous.medecin;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@RequiredArgsConstructor
@Service
class MedecinService implements IMedecinService {

    private final MedecinRepository medecinRepository;

    public Flux<Medecin> all() {
        return this.medecinRepository.findAll();
    }

    public Mono<Medecin> get(Long id) {
        return this.medecinRepository.findById(id);
    }

    public Mono<Medecin> update(Long id, String nom, String prenom, String email, String telephone) {
        return this.medecinRepository
                .findById(id)
                .map(p -> {
                    p.setNom(nom);
                    p.setPrenom(prenom);
                    p.setEmail(email);
                    p.setTelephone(telephone);
                    return p;
                })
                .flatMap(this.medecinRepository::save);
    }

    public Mono<Medecin> delete(Long id) {
        return this.medecinRepository
                .findById(id)
                .flatMap(p -> this.medecinRepository.deleteById(p.getId()).thenReturn(p));
    }

    public Mono<Medecin> create(String nom, String prenom, String email, String telephone) {
        return this.medecinRepository
                .save(new Medecin(null, nom, prenom, email, telephone));
    }

    @Override
    public Mono<Boolean> medecinExistsById(Long medecinId) {
        return this.medecinRepository.existsById(medecinId);
    }
}
