package serviciorest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//El punto de entrada de toda aplición Spring Boot es "Application" y se genera por defecto.
//Mediante la anotaon @SpringBootApplication le decimos a Spring que:
//1. Busque anotaciones de Spring para dar de alta objetos e inyectar dependencias. Las clases tienen que 
// estar en este paquete o en uno hijo.
//2. Se autoconfigure la app, en nuestro caso hemos generado una app web y se configurará un servidor Tomcat para abrirla.
//3. Buscará metodos dentro de esta clase anotados con @Bean para dar de alta objetos en el contexto de Spring.

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		
				//Mediante el metodo "run" arrancaremos el contexto de Spring
				//y daremos de alta todos los objetos que hayamos configurado
				//en nuestra aplicacion así como sus dependecias con otros 
				//objetos.
		
		System.out.println("Servicio Rest para la tarea del grupo 5 ---> Cargando el contexto de Spring...");
		
		SpringApplication.run(Application.class, args);
		
		System.out.println("Servicio Rest ---> Contexto de Spring cargado :)");
	}

}
