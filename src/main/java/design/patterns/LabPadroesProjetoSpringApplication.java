package design.patterns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
/**
   Projeto Spring Boot gerado via Spring Initializr.
   Módulos selecionados:
   - Spring Web
   - Spring Data JPA
   - H2 Database
   - OpenFeign

   @author bradb
 */

@EnableFeignClients
@SpringBootApplication
public class LabPadroesProjetoSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabPadroesProjetoSpringApplication.class, args);
	}

}
