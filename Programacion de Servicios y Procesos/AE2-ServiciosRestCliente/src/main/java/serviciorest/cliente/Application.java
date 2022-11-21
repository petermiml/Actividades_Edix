package serviciorest.cliente;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import serviciorest.cliente.entidad.VideoJuego;
import serviciorest.cliente.servicio.ServicioProxyVideoJuego;

@SpringBootApplication
public class Application implements CommandLineRunner{

	//Primero inyectaremos todos los objetos que necesitamos para
	//acceder a nuestro ServicioRest, el ServicioProxyVideojuego
	@Autowired
	private ServicioProxyVideoJuego spv;

	//Tambien necesitaremos acceder al contexto de Spring para parar
	//la aplicacion, ya que esta app al ser una aplicacion web se
	//lanzará en un tomcat. De esta manera le decimos a Spring que
	//nos inyecte su propio contexto.
	@Autowired
	private ApplicationContext context;
	
	//Metodo para parar la aplicacion
	public void pararAplicacion() {

		SpringApplication.exit(context, () -> 0);	
	}
	
	//En este metodo daremos de alta un objeto de tipo RestTemplate que sera
	//el objeto mas importante de esta aplicacion. Sera usado por los 
	//objetos ServicioProxy para hacer las peticiones HTTP a nuestro
	//servicio REST. Como no podemos anotar la clase RestTemplate porque
	//no la hemos creado nosotros, usaremos la anotacion @Bean para decirle
	//a Spring que ejecute este metodo y meta el objeto devuelto dentro
	//del contexto de Spring con ID "restTemplate" (el nombre del metodo)
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	//Metodo main que lanza la aplicacion
	public static void main(String[] args) {
		System.out.println("Cliente -> Cargando el contexto de Spring");
		SpringApplication.run(Application.class, args);

		//Notese que como este metodo es estatico no podemos acceder
		//a los metodos dinamicos de la clase, como el "spv".
		//Para solucionar esto, haremos que nuestra clase implemente
		//"CommandLineRunner" e implementaremos el metodo "run"
		//Cuando se acabe de arrancar el contexto, se llamara automaticamente
		//al metodo run
	}
	
	//Este metodo es dinamico por la tanto ya podemos acceder a los atributos
	//dinamicos
	@Override
	public void run(String... args) throws Exception {
		System.out.println("****** Arrancando el cliente REST ************\n");
		// Creamos variables necesarias para el codigo.
					String opcion = "";
					boolean continuar = true;
					int nota=0;
		try(Scanner sc = new Scanner(System.in)){
			do {
					System.out.println("Elige el numero de la opcion que desea:\n");
					System.out.println("1*Dar de alta un videojuego");
					System.out.println("2*Dar de baja un videojuego por ID");
					System.out.println("3*Modificar un videojuego por ID");
					System.out.println("4*Obtener un videojuego por ID");
					System.out.println("5*Listar todos los videojuegos");
					System.out.println("6*Salir de la aplicacion\n");
					System.out.println("Introduce el numero de la opcion que desea:");
						
					opcion = sc.nextLine();	// La variable "opcion" contendra lo que introduzca el usuario por teclado.
						
				switch (opcion) {
						
					case "1":
						// En primer lugar solicitamos los datos al usuario y lo guardamos en las respectivas variables:
						
						System.out.println("Por favor, escribe el nombre del videojuego a introducir: ");
					    String nombre = sc.nextLine();
					 	
					    System.out.println("Por favor, escribe el nombre de la compañia a introducir: ");
					    String compañia = sc.nextLine();
	
					    System.out.println("Por favor, escribe la nota del videojuego a introducir: ");
					    nota = sc.nextInt();sc.nextLine();
						//Creamos un objeto y le introducimos los datos recogidos por el cliente.
						VideoJuego videoJuego = new VideoJuego();
						videoJuego.setNombre(nombre);
						videoJuego.setCompañia(compañia);
						videoJuego.setNota(nota);
						//Le damos de alta 
						VideoJuego vAlta = spv.alta(videoJuego);
						System.out.println("run -> Videojuego dado de alta " + vAlta+"\n");
						break;    

					case "2":
						// En primer lugar solicitamos el id del objeto que queremos borrar y lo guardamos en una variable:
						System.out.println("Por favor, escribe el id del videojuego a borrar: ");
					    int id = sc.nextInt();sc.nextLine();
					    //Borramos el objeto seleccionado
						boolean borrado = spv.borrar(id);
						System.out.println("run -> Videojuego con el id: " +id+" está borrado?"+ borrado+"\n");
						
					    break;
							    
					case "3":
						// En primer lugar solicitamos los datos al usuario y lo guardamos en las respectivas variables:
					    System.out.println("Por favor, escribe el id del videojuego a modificar: ");
					    id = sc.nextInt();sc.nextLine();
	
					    System.out.println("Por favor, escribe el nombre del videojuego a modificar: ");
					    nombre = sc.nextLine();
					 	
					    System.out.println("Por favor, escribe el nombre de la compañia a modificar: ");
					    compañia = sc.nextLine();
	
					    System.out.println("Por favor, escribe la nota del videojuego a modificar: ");
					    nota = sc.nextInt();sc.nextLine();
					    
					    
					    //Con las variables recogidas nos disponemos a modificar el objeto co sus setters 
						VideoJuego vModificar = new VideoJuego();
						vModificar.setId(id);
						vModificar.setNombre(nombre);
						vModificar.setCompañia(compañia);
						vModificar.setNota(nota);
						boolean modificado = spv.modificar(vModificar);
						System.out.println("run -> videojuego modificado? " + modificado+"\n");
					    break;
							    
					case "4":
						// En primer lugar solicitamos al usuario el id del objeto que quermos mostrar
						//Lo guardamos en una variable
						System.out.println("Por favor, escribe el id del videojuego a ver: ");
					    id = sc.nextInt();sc.nextLine();
					    //Usamos el id para mostrar el objeto con el metodo obtener del ServicioProxyVideoJuego
						videoJuego = spv.obtener(id);
						System.out.println("run -> Videojuego con id 20: " + videoJuego+"\n");	
						break;
		
					case "5":
						List<VideoJuego> listaVideoJuegos = spv.listar();
						//Recorremos la lista y la imprimimos.
						listaVideoJuegos.forEach((v) -> System.out.println(v));
						break;
				
			       case "6": 
			    	   
			    	 //Mandamos parar nuestra aplicacion Spring Boot
			    	   System.out.println("Fin de conexión");
			   		   pararAplicacion();
			   		   continuar=false;
			    	    break;
			    	 // En caso de escoger una opcion incorrecta, se indicar� al usuario.
					default:
						System.out.println("Opcion incorrecta.\n");
						break;
				}}while(continuar);

                sc.close();
					
			//Recogemos las excepciones con los 'catch'	
			}catch (Exception e) {
						System.out.println("Error en la conexion con el servidor");
						e.printStackTrace();
						
				}
		}
}
