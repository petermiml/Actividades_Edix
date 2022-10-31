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
	
	// IP y Puerto a la que nos vamos a conectar
	public static final int PUERTO = 2023;
	public static final String IP_SERVIDOR = "localHost";

	public static void main(String[] args) {
		
		System.out.println("APLICACION CLIENTE CONECTANDO");
		System.out.println("------------------------------");
		
		// Encapsulamos la direccion del servidor..
		InetSocketAddress direccionServidor = new InetSocketAddress(IP_SERVIDOR, PUERTO);
		
		try(Scanner sc = new Scanner(System.in)){
			
			System.out.println("CLIENTE: Esperando a que el servidor acepte la conexion");
			//Aqui creamos el socket que conectara con el sevidor.
			Socket socketAlServidor = new Socket();
			//Usamos el metodo connect del objeto socket para conectar.
			socketAlServidor.connect(direccionServidor);
			
			System.out.println("CLIENTE: Conexion establecida... a " + IP_SERVIDOR + 
					" por el puerto " + PUERTO + "\n");
			
			//Creamos el objeto InputStreamReader que usara el socket como entrada de datos.
			InputStreamReader entrada = new InputStreamReader(socketAlServidor.getInputStream());
			
			//Creamos el objeto BufferedReader que sirve para leer datos recibidos.
			BufferedReader entradaBuffer = new BufferedReader(entrada);
			
			//Creamos el objeto PrintStream que usara el socket como salida de datos.
			PrintStream salida = new PrintStream(socketAlServidor.getOutputStream());
			
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
				
				opcion = sc.nextLine();
				
				switch (opcion) {
				case "1":
					System.out.println("Por favor, escribe el ISBN del libro que desea consultar :");
				    String isbn = sc.nextLine();
				    String mensaje = opcion + "-" + isbn;
				    salida.println(mensaje);
				    respuesta = entradaBuffer.readLine();
				    System.out.println(respuesta);
					break;
				case "2":
					 System.out.println("Por favor, escribe el nombre del titulo del libro que desea consultar :");
				    String titulo = sc.nextLine();
				    mensaje = opcion + "-" + titulo;
				    salida.println(mensaje);
				    respuesta = entradaBuffer.readLine();
				    System.out.println(respuesta);
				    break;
				case "4":
					System.out.println("Por favor, escribe el ISBN del libro a introducir:");
				    isbn = sc.nextLine();
				 	
				    System.out.println("Por favor, escribe el titulo del libro a introducir:");
				    titulo = sc.nextLine();

				    System.out.println("Por favor, escribe el autor del libro a introducir:");
				    String autor = sc.nextLine();
					
					System.out.println("Por favor, escribe el precio del libro a introducir:");
					String precio = sc.nextLine();
					
					datos = isbn + "-" + titulo + "-" + autor + "-" + precio;
					
					salida.println(opcion + "-" + datos);
					
					System.out.println("CLIENTE: Esperando respuesta ...... \n");
					respuesta = entradaBuffer.readLine();
					
					System.out.println(respuesta);
					break;
					
				case "5":
					continuar = false;
					System.out.println("Fin de conexion");
					break;

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
