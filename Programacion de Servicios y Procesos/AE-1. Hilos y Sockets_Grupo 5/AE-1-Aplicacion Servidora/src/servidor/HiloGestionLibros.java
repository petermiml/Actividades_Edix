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
	private ArrayList<Libro> libros= null;
	
	public HiloGestionLibros(Socket socketAlCliente){
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
		opcionRecibida = bf.readLine();
		
		String[] opcionDatos = opcionRecibida.split("-");
		opcion = opcionDatos[0];
		datos = opcionDatos[1];
		
		switch(opcion) {
		
		case "1":
			salida.println(libros.get(2));
			break;
			
		case "2":
			salida.println("case 2");
			break;
			
		case "3":
			
			salida.println("case 3");
			break;
			
		case "4":
			//anadirLibro(libro);
			salida.println("case 4");
			break;	
			
			default:
			salida.println("La opción no es correcta");
		}
	
	   socketAlCliente.close();
		}
	}catch(Exception e){
		System.out.println("Error de conexion en GestionLibros");
	}
  }
}

