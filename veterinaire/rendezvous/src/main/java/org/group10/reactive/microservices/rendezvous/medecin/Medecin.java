package org.group10.reactive.microservices.rendezvous.medecin;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
class Medecin {
    @Id
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
}