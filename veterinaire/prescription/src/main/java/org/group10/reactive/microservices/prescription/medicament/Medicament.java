package org.group10.reactive.microservices.prescription.medicament;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
class Medicament {
    @Id
    private Long id;
    private String nomMedicamant;

}