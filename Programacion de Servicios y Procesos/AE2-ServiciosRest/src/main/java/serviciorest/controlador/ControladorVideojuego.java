package serviciorest.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import serviciorest.modelo.DAO.DaoVideojuego;

//Aquí vamos a gestionar el CRUD completo que nos pide la tarea. La bbdd está simulada en memoria
//con los 5 videojuegos que hemos añadido anteriormente en la clase DaoVideojuego.

@RestController

/*
 * Con la anotación RestController damos de alta en el contexto de Spring, la clase ControladorVideojuego. ésta necesita
 * un objeto de tipo DaoVideojuego para realizar su trabajo (@component).
 */
public class ControladorVideojuego {
	
	@Autowired //@Autowired se usa para hacer inyecciones de dependencias de objetos dados de alta dentro del contexto de Spring.
	private DaoVideojuego daoVideojuego;

}
