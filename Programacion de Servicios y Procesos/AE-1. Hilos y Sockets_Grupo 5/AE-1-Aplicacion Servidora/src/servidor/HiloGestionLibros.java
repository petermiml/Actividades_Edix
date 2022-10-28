package servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

public class HiloGestionLibros implements Runnable {
	
	Thread hilo;
	Socket socketAlCliente;
	private Libro libro;
	
	//Estaba creada una lista de Libros vacia
	//Hay que instanciar una variable de la clase Biblioteca para que coga todos los libros
	
	private Biblioteca biblioteca; 
		
	public HiloGestionLibros(Socket socketAlCliente){
		biblioteca = new Biblioteca(); //Al iniciar el socket, tenemos que inicializar la biblioteca.
		hilo = new Thread(this);
		this.socketAlCliente = socketAlCliente;
		hilo.start();
	}
	
	@Override
	public void run() {
		InputStreamReader entrada = null;
		PrintStream salida = null;
		BufferedReader bf = null;
	
	try {
		
		entrada = new InputStreamReader(socketAlCliente.getInputStream());
		bf = new BufferedReader(entrada);
		
		salida = new PrintStream(socketAlCliente.getOutputStream());
		
		String opcionRecibida = "";
		String opcion = "";
		String datos = "";
		
		boolean continuar = true;
		
		while (continuar) {
			// Creamos un array unidimensional con lo que nos llega del cliente separandolo por guion
			String[] opcionDatos = bf.readLine().split("-");
			opcion = opcionDatos[0];
			datos = opcionDatos[1];
		
			switch(opcion) {
				case "1":
					
					//Recorro la lista de biblioteca, cojo los libros y comparo el ISBN con lo que me manda 
					//el usuario.
					
					for(Libro l:biblioteca.getLibros()) {
						if (l.getIsbn().equals(datos)) {
							salida.println( l.toString());
						}
					}
					salida.println("El ISBN no existe");
					break;
					
				case "2":
					
					for (Libro l:biblioteca.getLibros()) {
						if(l.getTitulo().equals(datos)) {
							salida.println( l.toString());
						}
					}
					salida.println("El titulo no existe");
					break;
					
				case "3":
					
					
					salida.println("case 3");
					break;
					
				case "4":
					
					String isbn = opcionDatos[1];
					String titulo = opcionDatos[2];
					String autor = opcionDatos[3];
					String precio = opcionDatos[4];
					
					String devolver = "Has elegido la opcion " + opcion + 			
							".-El isbn del libro introducido es: " + isbn + 
							".-El titulo del libro introducido es: " + titulo +
							".-El autor del libro introducido es: " + autor +
							".-El precio del libro introducido es: " + precio;
					
					salida.println(devolver);
					
					break;	
					
				default:
					salida.println("La opcion no es correcta");
			}
			
		}
		
		//Estaba dando error porque cerrabamos el socket dentro del bucle, 
		//hay que cerrarlo despues del bucle, cuando el cliente cierra la conexion.
		
		socketAlCliente.close();

	}catch(NullPointerException e) {
		System.out.println("Cada vez que un usuario pulsa en el 5 se genera esta excepcion.");
		e.printStackTrace();
	}catch(Exception e){
		System.out.println("Error de conexion en GestionLibros");
		e.printStackTrace();
	}
  }
}
