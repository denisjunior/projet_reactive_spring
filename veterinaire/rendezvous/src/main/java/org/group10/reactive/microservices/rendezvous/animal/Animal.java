package org.group10.reactive.microservices.rendezvous.animal;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
class Animal {
    @Id
    private Long id;
    private String nomAnimal;
    private String prenomAnimal;
    private Long parentID;
}
