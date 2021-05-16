package org.group10.reactive.microservices.rendezvous.animal;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@RequiredArgsConstructor
@Service
class AnimalService implements IAnimalService {

    private final AnimalRepository animalRepository;

    public Flux<Animal> all() {
        return this.animalRepository.findAll();
    }

    public Mono<Animal> get(Long id) {
        return this.animalRepository.findById(id);
    }

    public Mono<Animal> update(Long id, String nomAnimal, String prenomAnimal, Long parentId) {
        return this.animalRepository
                .findById(id)
                .map(p -> {
                    p.setNomAnimal(nomAnimal);
                    p.setPrenomAnimal(prenomAnimal);
                    p.setParentID(parentId);
                    return p;
                })
                .flatMap(this.animalRepository::save);
    }

    public Mono<Animal> delete(Long id) {
        return this.animalRepository
                .findById(id)
                .flatMap(p -> this.animalRepository.deleteById(p.getId()).thenReturn(p));
    }

    public Mono<Animal> create(String nomAnimal, String prenomAnimal, Long parentId) {
        return this.animalRepository
                .save(new Animal(null, nomAnimal, prenomAnimal, parentId));
    }

    @Override
    public Mono<Boolean> animalExistsById(Long animalId) {
        return this.animalRepository.existsById(animalId);
    }
}
