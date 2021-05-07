package com.awin.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/*This class responsible start the application.
 * */
@SpringBootApplication(scanBasePackages={"com.awin"})
@EntityScan("com.awin.entity")
@EnableJpaRepositories("com.awin.repository")
public class AwinApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwinApplication.class, args);
	}
	
}
