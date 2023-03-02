package edu.tus.ofoa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@ComponentScan({"edu.tus.ofoa.controller","edu.tus.ofoa.service"})
@EntityScan("edu.tus.ofoa.entity")
@EnableJpaRepositories("edu.tus.ofoa.repository")
@SpringBootApplication(scanBasePackages = {"edu.tus.ofoa"})
public class FOM {
	public static void main(String[] args) {
		SpringApplication.run(FOM.class, args);
	}

}
