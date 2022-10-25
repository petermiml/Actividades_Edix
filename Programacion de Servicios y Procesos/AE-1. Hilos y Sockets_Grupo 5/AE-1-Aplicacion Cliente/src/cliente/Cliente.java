package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	
	public static final int PUERTO = 2022;
	public static final String IP_SERVIDOR = "localHost";

	public static void main(String[] args) {
		System.out.println("Aplicacion cliente conectando");

		InetSocketAddress direccionServidor = new InetSocketAddress(IP_SERVIDOR, PUERTO);
		
		try(Scanner sc = new Scanner(System.in)){
			
			System.out.println("CLIENTE: Esperando a que el servidor acepte la conexion");
			
			Socket socketAlServidor = new Socket();
			socketAlServidor.connect(direccionServidor);
			
			System.out.println("CLIENTE: Conexion establecida... a " + IP_SERVIDOR + 
					" por el puerto " + PUERTO + "\n");
			
			InputStreamReader entrada = new InputStreamReader(socketAlServidor.getInputStream());
			BufferedReader entradaBuffer = new BufferedReader(entrada);
			
			PrintStream salida = new PrintStream(socketAlServidor.getOutputStream());
			
			String opcion = "";
			boolean continuar = true;
			do {
				System.out.println("Elige el numero de la opcion que desea:\n");
				System.out.println("1*Consultar libro por ISBN");
				System.out.println("2*Consultar libro por titulo");
				System.out.println("3*Consultar libros por autor");
				System.out.println("4*Anadir libro");
				System.out.println("5*Salir de la aplicacion");
				
				opcion = sc.nextLine(); 
				
				salida.println(opcion);
				
				System.out.println("CLIENTE: Esperando respuesta ...... ");
				String respuesta = entradaBuffer.readLine();
				System.out.println(respuesta);
			
			}while(continuar);
			socketAlServidor.close();
		} catch (Exception e) {
				System.out.println("Error en la conexion con el servidor");
				e.printStackTrace();
				
		}
	
  }
}
