package org.group10.reactive.microservices.rendezvous;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication
public class RendezvousApplication {

	public static void main(String[] args) {
		SpringApplication.run(RendezvousApplication.class, args);
	}

}
