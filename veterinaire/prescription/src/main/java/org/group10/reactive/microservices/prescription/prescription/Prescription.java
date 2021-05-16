package org.group10.reactive.microservices.prescription.prescription;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
class Prescription {
    @Id
    private Long id;
    private Long quantite;
    private String posologie;
    private Long idOrdonnance;
    private Long idMedicament;

}