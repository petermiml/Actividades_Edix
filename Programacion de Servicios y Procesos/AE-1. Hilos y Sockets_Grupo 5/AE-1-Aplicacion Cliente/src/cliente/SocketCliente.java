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
	public static final int PUERTO = 2022;
	public static final String IP_SERVIDOR = "localHost";

	public static void main(String[] args) {
		
		System.out.println("APLICACION CLIENTE CONECTANDO");
		System.out.println("------------------------------");
		
		// Encapsulamos la direccion del servidor.
		InetSocketAddress direccionServidor = new InetSocketAddress(IP_SERVIDOR, PUERTO);
		
		try(Scanner sc = new Scanner(System.in)){
			
			System.out.println("CLIENTE: Esperando a que el servidor acepte la conexion");
			//Aquí creamos el socket que conectará con el sevidor.
			Socket socketAlServidor = new Socket();
			//Usamos el método connect del objeto socket para conectar.
			socketAlServidor.connect(direccionServidor);
			
			System.out.println("CLIENTE: Conexion establecida... a " + IP_SERVIDOR + 
					" por el puerto " + PUERTO + "\n");
			//Creamos el objeto InputStreamReader que usará el socket como entrada de datos.
			InputStreamReader entrada = new InputStreamReader(socketAlServidor.getInputStream());
			//Creamos el objeto BufferedReader que sirve para leer datos recibidos.
			BufferedReader entradaBuffer = new BufferedReader(entrada);
			//Creamos el objeto PrintStream que usará el socket como slida de datos.
			PrintStream salida = new PrintStream(socketAlServidor.getOutputStream());
			
			String opcion = "";
			String datos = "";
			boolean continuar = true;
			do {
				System.out.println("Elige el número de la opcion que desea y luego añade los datos de la opción:\n");
				System.out.println("1*Consultar libro por ISBN");
				System.out.println("2*Consultar libro por titulo");
				System.out.println("3*Consultar libros por autor");
				System.out.println("4*Anadir libro");
				System.out.println("5*Salir de la aplicacion\n");
				System.out.println("Introduce el número de la opción que desea:");
				opcion = sc.nextLine();
				if("5".equalsIgnoreCase(opcion)) {
					continuar = false;
					System.out.println("Fin de conexion");
				}else {
				System.out.println("Introduce los datos:");
				datos = sc.nextLine();
				
				String datosEnviados = opcion +"-"+datos;
				
				salida.println(datosEnviados);
				
				System.out.println("CLIENTE: Esperando respuesta ...... ");
				String respuesta = entradaBuffer.readLine();
				
				System.out.println(respuesta);
				}
			}while(continuar);
			socketAlServidor.close();
		} catch (UnknownHostException e) {
			System.err.println("CLIENTE: No encuentro el servidor en la dirección" + IP_SERVIDOR);
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
