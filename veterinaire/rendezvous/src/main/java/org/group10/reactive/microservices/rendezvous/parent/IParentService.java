package org.group10.reactive.microservices.rendezvous.parent;

import reactor.core.publisher.Mono;

public interface IParentService {
    Mono<Boolean> parentExistsById(Long parentId);
}
