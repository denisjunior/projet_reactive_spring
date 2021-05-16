package org.group10.reactive.microservices.rendezvous.rdv;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@AllArgsConstructor
class Rdv {
    @Id
    private Long id;
    private String daterdv;
    private String heurerdv;
    private Long animalId;
    private Long medecinId;
}