package serviciorest.cliente.servicio;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import serviciorest.cliente.entidad.VideoJuego;

//Con esta anotacion damos de alta un objeto de tipo
//ServicioProxyVideoJuego dentro del contexto de Spring
@SuppressWarnings("unused")
@Service
public class ServicioProxyVideoJuego {

	//La URL base del servicio REST de videojuego
	public static final String URL = "http://localhost:8090/videojuegos/";
	
	//Inyectamos el objeto de tipo RestTemplate que nos ayudara
	//a hacer las peticiones HTTP al servicio REST
	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * Metodo que obtiene un videojuego del servicio REST a partir de un id
	 * En caso de que el id no exita arrojaria una expcepcion que se captura
	 * para sacar el codigo de respuesta
	 * 
	 * @param id que queremos obtener
	 * @return retorna el videojuego que estamos buscando, null en caso de que el
	 * videojuego no se encuentre en el servidor (devuelva 404) o haya habido algun
	 * otro error.
	 */
	public VideoJuego obtener(int id){
		try {
			ResponseEntity<VideoJuego> vi = restTemplate.getForEntity(URL + id, VideoJuego.class);
			HttpStatus hs= vi.getStatusCode();
			if(hs == HttpStatus.OK) {	
				//Si el videoJuego existe, el videoJuego viene en formato JSON en el body
				//Al ser el objeto ResponseEntity de tipo VideoJuego, al obtener el 
				//body me lo convierte automaticamente a tipo VideoJuego
				return vi.getBody();
			}else {
				System.out.println("Respuesta no contemplada");
				return null;
			}
		}catch (HttpClientErrorException e) {
			System.out.println("obtener -> el videojuego NO se ha encontrado, id: " + id);
		    System.out.println("obtener -> Codigo de respuesta: " + e.getStatusCode());
		    return null;
		}
	}
	/**
	 * Metodo que da de alta una VideoJuego en el servicio REST
	 * 
	 * @param v el VideoJuego que vamos a dar de alta
	 * @return el VideoJuego con el id actualizado que se ha dado de alta en el
	 * servicio REST. Null en caso de que no se haya podido dar de alta
	 */
	public VideoJuego alta(VideoJuego v){
		try {
			//Para hacer un post de una entidad usamos el metodo postForEntity
			//El primer parametro la URL
			//El segundo parametros la VideoJuego que ira en body
			//El tercer parametro el objeto que esperamos que nos envie el servidor
			ResponseEntity<VideoJuego> vi = restTemplate.postForEntity(URL, v, VideoJuego.class);
			System.out.println("alta -> Codigo de respuesta " + vi.getStatusCode());
			return vi.getBody();
		} catch (HttpClientErrorException e) {
			System.out.println("alta -> El VideoJuego NO se ha dado de alta, id: " + v);
		    System.out.println("alta -> Codigo de respuesta: " + e.getStatusCode());
		    return null;
		}
	}
	/**
	 * Modifica un videojuego en el servicio REST
	 * 
	 * @param v el videojuego que queremos modificar, se hara a partir del 
	 * id por lo que tiene que estar relleno.
	 * @return true en caso de que se haya podido modificar el videojuego. 
	 * false en caso contrario.
	 */
	public boolean modificar(VideoJuego v){
		try {
			restTemplate.put(URL + v.getId(), v, VideoJuego.class);
			return true;
		} catch (HttpClientErrorException e) {
			System.out.println("modificar -> La VideoJuego NO se ha modificado, id: " + v.getId());
		    System.out.println("modificar -> Codigo de respuesta: " + e.getStatusCode());
		    return false;
		}
	}
	/**
	 * Borra un videojuego en el servicio REST
	 * 
	 * @param id el id del videojuego que queremos borrar.
	 * @return true en caso de que se haya podido borrar el videojuego. 
	 * false en caso contrario.
	 */
	public boolean borrar(int id){
		try {
			restTemplate.delete(URL + id);
			return true;
		} catch (HttpClientErrorException e) {
			System.out.println("borrar -> El VideoJuego NO se ha borrado, id: " + id);
		    System.out.println("borrar -> Codigo de respuesta: " + e.getStatusCode());
		    return false;
		}
	}
	/**
	 * Metodo que devuelve todas los videojuegos
	 * @return el listado de los videojuegos o null en caso de algun error con el servicio REST
	 */
	public List<VideoJuego> listar(){

		
		try {
			ResponseEntity<VideoJuego[]> response =
					  restTemplate.getForEntity(URL ,VideoJuego[].class);
			VideoJuego[] arrayVideoJuegos = response.getBody();
			return Arrays.asList(arrayVideoJuegos);//convertimos el array en un arraylist
		} catch (HttpClientErrorException e) {
			System.out.println("listar -> Error al obtener la lista de videojuegos");
		    System.out.println("listar -> Codigo de respuesta: " + e.getStatusCode());
		    return null;
		}
	}
}
