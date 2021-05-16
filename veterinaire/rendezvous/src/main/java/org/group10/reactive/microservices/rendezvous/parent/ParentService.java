package org.group10.reactive.microservices.rendezvous.parent;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@RequiredArgsConstructor
@Service
class ParentService implements IParentService {

    private final ParentRepository parentRepository;

    public Flux<Parent> all() {
        return this.parentRepository.findAll();
    }

    public Mono<Parent> get(Long id) {
        return this.parentRepository.findById(id);
    }

    public Mono<Parent> update(Long id, String nomParent, String prenomParent, String emailParent, String telParent) {
        return this.parentRepository
                .findById(id)
                .map(p -> {
                    p.setNomParent(nomParent);
                    p.setPrenomParent(prenomParent);
                    p.setEmailParent(emailParent);
                    p.setTelParent(telParent);
                    return p;
                })
                .flatMap(this.parentRepository::save);
    }

    public Mono<Parent> delete(Long id) {
        return this.parentRepository
                .findById(id)
                .flatMap(p -> this.parentRepository.deleteById(p.getId()).thenReturn(p));
    }

    public Mono<Parent> create(String nomParent, String prenomParent, String emailParent, String telParent) {
        return this.parentRepository
                .save(new Parent(null, nomParent, prenomParent, emailParent, telParent));
    }

    @Override
    public Mono<Boolean> parentExistsById(Long parentId) {
        return this.parentRepository.existsById(parentId);
    }
}
