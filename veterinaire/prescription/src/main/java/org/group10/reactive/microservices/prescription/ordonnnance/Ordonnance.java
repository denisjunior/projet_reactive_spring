package org.group10.reactive.microservices.prescription.ordonnnance;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
class Ordonnance {
    @Id
    private Long id;
    private Long idRvd;

}