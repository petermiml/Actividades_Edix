package serviciorest.controlador;

//import java.awt.PageAttributes.MediaType;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.MediaType;

import serviciorest.modelo.DAO.DaoVideojuego;
import serviciorest.modelo.entidad.VideoJuego;

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

	/*
	 * Cuando se cree este objeto "DaoVideojuego" dentro del contexto de Spring, mediante esta anotación
	 * le vamos a decir que inyecte un objeto del tipo a la referencia daoVideojuego. 
	 */
	
	//DAR DE ALTA UN VIDEOJUEGO. (POST)
	/*
	 * Queremos dar de alta un videojuego nuevo, para ello usaremos el método POST, que nos permite crear o actualizar
	 * uno o más recursos, sin crear su ID. Seremos nosotros los que asignemos un ID al videojuego nuevo.
	 * 
	 * La tarea pide que el intercambio de mensajes se realice a través de JSON.
	 * 
	 * La URL para acceder a este método será:
	 * "http://localhost:8090/videojuegos" y el método a usar será POST como hemos dicho anteriormente.
	 * Pasamos el videojuego que queramos crear sin el ID.
	 */
	
	@PostMapping(path="videojuegos",consumes=MediaType.
			APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseEntity<VideoJuego> altaVideoJuego (@RequestBody VideoJuego v){
		System.out.println("Videojuego dado de alta : " + v);
		daoVideojuego.addGame(v);
		return new ResponseEntity<VideoJuego>(v,HttpStatus.CREATED);
		
	}
	
	//DAR DE BAJA UN VIDEOJUEGO. (DELETE)
	/*
	 * Vamos a borrar un videojuego pasándole un ID, usaremos el método DELETE.
	 * 
	 * La URL que usaremos para borrar videojuegos será:
	 * "http://localhost:8090/videojuegos/ID" y el método a utilizar será DELETE.
	 */
	
	@DeleteMapping(path="videojuegos/{id}")
	
	public ResponseEntity<VideoJuego> borrarVideoJuego (@PathVariable ("id") int id){
		System.out.println("ID a borrar : " + id);
		VideoJuego v = daoVideojuego.deleteGame(id);
		
		if(v != null) {
			return new ResponseEntity<VideoJuego>(v,HttpStatus.OK); //Si existe el id introducido,el servidor 
																	//nos da un mensaje de OK(200).
		}else {
			return new ResponseEntity<VideoJuego>(v,HttpStatus.NOT_FOUND); //Si el id no existe, nos dirá que no encuentra
																			// el id introducido (404 not found).
		}
	
	}
	
	//MODIFICAR VIDEOJUEGO POR ID. (PUT)
	/*
	 * Queremos hacer una modificación de un videojuego pasándo su ID.
	 * Vamos a recibir el ID por el PATH, y los datos los modificaremos por JSON dentro del body de http.
	 * 
	 * La URL para acceder a esta modificación será:
	 * "http://localhost:8090/videojuegos/ID" y el método será PUT pasandole el videojuego nuevo, sin el ID.
	 */
	@PutMapping(path="videojuegos/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseEntity<VideoJuego> modificarVideoJuego(@PathVariable("id") int id, @RequestBody VideoJuego v){
		System.out.println("ID a modificar : " + id);
		System.out.println("Datos a modificar : " + v);
		v.setId(id);
		VideoJuego gUpdate = daoVideojuego.update(v);
		
		if(gUpdate != null) {
			return new ResponseEntity<VideoJuego>(HttpStatus.OK);	
		}else {
			return new ResponseEntity<VideoJuego>(HttpStatus.NOT_FOUND);
			}
	}
	
	//OBTENER VIDEOJUEGO POR ID. (GET)
	/*
	 * Como hemos aprendido, al ser una búsqueda por clave primaria (ID), éste debe ir como parte del PATH de la URL.
	 * (path="videojuegos/{id}")
	 * 
	 * La URL que utilizaremos será:
	 * "http://localhost:8090/videojuegos/ID y el método GET.
	 */
	
	@GetMapping(path = "videojuegos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseEntity<VideoJuego> getVideoJuego (@PathVariable ("id") int id){
		System.out.println("Buscando Videouego por ID : " + id);
		VideoJuego g = daoVideojuego.get(id);
		
		if(g != null) {
			return new ResponseEntity<VideoJuego>(g,HttpStatus.OK);
		} else {
			return new ResponseEntity<VideoJuego>(g,HttpStatus.NOT_FOUND);
		}
		
	}
	
	//LISTAR TODOS (GET)
	/*
	 * Aquí queremos pedir todos los videojuegos que tenemos almacenados.
	 * 
	 * "http://localhost:8090/videojuegos
	 */
	
	@GetMapping(path = "videojuegos", produces = MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseEntity<List<VideoJuego>> listarVideoJuegos (){
		List<VideoJuego> listaVideoJuegos = null;
		
		System.out.println("La lista de los videojuegos es : ");
		listaVideoJuegos = daoVideojuego.list();
		System.out.println(listaVideoJuegos);
		
		return new ResponseEntity<List<VideoJuego>>(listaVideoJuegos,HttpStatus.OK);
		
	}
	
}
