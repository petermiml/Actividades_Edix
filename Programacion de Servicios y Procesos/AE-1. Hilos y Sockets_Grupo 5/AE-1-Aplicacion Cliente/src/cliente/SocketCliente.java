package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class SocketCliente {
	
	// Declaramos las constantes para la IP y Puerto a la que nos vamos a conectar
	public static final int PUERTO = 2023;
	public static final String IP_SERVIDOR = "localHost";

	public static void main(String[] args) {
		
		System.out.println("APLICACION CLIENTE CONECTANDO");
		System.out.println("------------------------------");
		
		// Encapsulamos la direccion del servidor en un objeto de tipo InetSocketAddress
		InetSocketAddress direccionServidor = new InetSocketAddress(IP_SERVIDOR, PUERTO);
		
		/* 
		 	El resto del código irá en un bloque try-catch para capturar las excepciones UnknownHostException, IOException y Exception
		 	En el mismo try (es un try with resources, inicializamos un objeto de tipo Scanner para que se cierre automaticamente al 
		 	final del bloque.
		*/
		try(Scanner sc = new Scanner(System.in)){
			
			System.out.println("CLIENTE: Esperando a que el servidor acepte la conexion");
			
			//Aqui creamos el socket que conectara con el sevidor.
			Socket socketAlServidor = new Socket();
			
			//Usamos el metodo connect del objeto socket para conectar con el servidor (le pasamos el objeto direccionServidor por parámetro).
			socketAlServidor.connect(direccionServidor);
			
			System.out.println("CLIENTE: Conexion establecida... a " + IP_SERVIDOR + 
					" por el puerto " + PUERTO + "\n");
			
			//Creamos el objeto InputStreamReader que usara el socket como entrada de datos.
			InputStreamReader entrada = new InputStreamReader(socketAlServidor.getInputStream());
			
			//Creamos el objeto BufferedReader que sirve para ayudarnos a leer datos recibidos. Esta clase lee los datos del servidor
			// linea a linea, mientras que InputStreamRreader lo hace caracter a caracter. Le pasamos el objeto entrada por parametro al constructor.
			BufferedReader entradaBuffer = new BufferedReader(entrada);
			
			//Creamos el objeto PrintStream que usara el socket como salida de datos.
			PrintStream salida = new PrintStream(socketAlServidor.getOutputStream());
			
			// Creamos variables necesarias para el código.
			String opcion = "";
			String datos = "";
			String respuesta = "";
			boolean continuar = true;
			
			do {
				System.out.println("Elige el numero de la opcion que desea:\n");
				System.out.println("1*Consultar libro por ISBN");
				System.out.println("2*Consultar libro por titulo");
				System.out.println("3*Consultar libros por autor");
				System.out.println("4*Anadir libro");
				System.out.println("5*Salir de la aplicacion\n");
				System.out.println("Introduce el numero de la opcion que desea:");
				
				opcion = sc.nextLine();	// La variable "opcion" contendrá lo que introduzca el usuario por teclado.
				
				// En función de lo que elija, se ejecutará un bloque try.
				switch (opcion) {
				
					case "1":
						System.out.println("Por favor, escribe el ISBN del libro que desea consultar: ");
					    String isbn = sc.nextLine();
					    
					    // 	Creamos un String que almacenará en primer lugar el contenido de la variable opcion, en segundo lugar un guion
					    // y entercer lugar, el isbn introducido por el usuario. Esto es para poder enviarlo todo y hacer un split en el hilo
					    // creado por el servidor y tratar los datos por separado.
					    String mensaje = opcion + "-" + isbn;
					    
					    // Enviamos el contenido de mensaje por la carretera de salida.
					    salida.println(mensaje);
					    
					    // Esperamos la respuesta que llegará por la carretera de entrada
					    respuesta = entradaBuffer.readLine();
					    
					    // Imprimimos la respuesta.
					    System.out.println(respuesta);
						break;
						
					case "2":
						// Caso similar al anterior
						System.out.println("Por favor, escribe el nombre del titulo del libro que desea consultar: ");
					    String titulo = sc.nextLine();
					    mensaje = opcion + "-" + titulo;
					    salida.println(mensaje);
					    respuesta = entradaBuffer.readLine();
					    System.out.println(respuesta);
					    break;
					    
					case "3":
						System.out.println("Por favor, escribe el nombre del autor del libro que desea consultar: ");
					    String autor = sc.nextLine();
					    mensaje = opcion + "-" + autor;
					    salida.println(mensaje);
					    respuesta = entradaBuffer.readLine();
					    
					    /* 
					      En este caso, queríamos hacer un salto de línea después de cada libro para mayor legibilidad pero el método readLine() no
					      puede leer más que una linea (cuando llega a un salto de línea ya no lee lo siguiente). 
					      Lo que nos llega por la entrada son los toString de los libros separados por guiones, por lo que usamos el método split() para
					      almacenarlos en un array y poder imprimirlos despues con sus respectivos saltos de linea.
					      */
					     
					    String [] aRespuesta = respuesta.split("-");
					    respuesta = "";
					    
					    for(int i=0; i < aRespuesta.length ; i++) {
					    	respuesta += aRespuesta[i] + "\n";
					    }
					    
					    System.out.println(respuesta);
					    break;
					    
					case "4":
						
						// En primer lugar solicitamos los datos al usuario y lo guardamos en las respectivas variables:
						
						System.out.println("Por favor, escribe el ISBN del libro a introducir: ");
					    isbn = sc.nextLine();
					 	
					    System.out.println("Por favor, escribe el titulo del libro a introducir: ");
					    titulo = sc.nextLine();
	
					    System.out.println("Por favor, escribe el autor del libro a introducir: ");
					    autor = sc.nextLine();
						
						System.out.println("Por favor, escribe el precio del libro a introducir: ");
						String precio = sc.nextLine();
						
						// Separamos los datos por guiones para que el hilo que lo reciba pueda leerlos y separarlos
						datos = isbn + "-" + titulo + "-" + autor + "-" + precio;
						
						salida.println(opcion + "-" + datos);
						
						System.out.println("CLIENTE: Esperando respuesta ...... \n");
						respuesta = entradaBuffer.readLine();
						
						System.out.println(respuesta);
						break;
						
					case "5":
						// En el caso de que el usuario escoja la opción 5, se cerrará la conexión y se envía al servidor que se ha cerrado la conexion.
						datos = "Cerrada la conexion con el usuario: ";
						salida.println(opcion+"-"+ datos);
						continuar = false;
						System.out.println("Fin de conexion");
						break;
	
						// En caso de escoger una opcion incorrecta, se indicará al usuario.
					default:
						System.out.println("Opcion incorrecta.");
						break;
				}
		       
			}while(continuar);
			
			//Cerramos la conexion fuera del bucle 'do'
			socketAlServidor.close();
			
		//Recogemos las excepciones con los 'catch'	
		} catch (UnknownHostException e) {
			System.err.println("CLIENTE: No encuentro el servidor en la direccion" + IP_SERVIDOR);
			e.printStackTrace();
			
		} catch (IOException e) {
			System.err.println("CLIENTE: Error de entrada/salida");
			e.printStackTrace();	
			
		} catch (Exception e) {
				System.out.println("Error en la conexion con el servidor");
				e.printStackTrace();
				
		}
	
  }
}
