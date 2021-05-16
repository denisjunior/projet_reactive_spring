package org.group10.reactive.microservices.rendezvous.parent;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
class Parent {
    @Id
    private Long id;
    private String nomParent;
    private String prenomParent;
    private String emailParent;
    private String telParent;
}