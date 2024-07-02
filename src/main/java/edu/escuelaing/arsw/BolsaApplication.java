package edu.escuelaing.arsw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the Bolsa service.
 * This class is responsible for bootstrapping the Spring Boot application.
 */
@SpringBootApplication
public class BolsaApplication {
	/**
     * The entry point of the Bolsa application.
     *
     * @param args Command line arguments passed to the application.
     */
	public static void main(String[] args) {
		SpringApplication.run(BolsaApplication.class, args);
	}
}
