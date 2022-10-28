package servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class HiloGestionLibros implements Runnable {
	
	Thread hilo;
	Socket socketAlCliente;
	
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
			// Creamos un array unidimensional con lo que nos llega del cliente separandolo por guion
			String[] opcionDatos = bf.readLine().split("-");
			opcion = opcionDatos[0];
			datos = opcionDatos[1];
		
			switch(opcion) {
		
				case "1":
					salida.println("case 1 ");
					break;
					
				case "2":
					salida.println("case 2");
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
							"\n.El isbn del libro introducido es: " + isbn + 
							"\n.El titulo del libro introducido es: " + titulo +
							"\n.El autor del libro introducido es: " + autor +
							"\n.El precio del libro introducido es: " + precio;
					
					salida.println(devolver);
					
					break;	
					
				default:
					salida.println("La opcion no es correcta");
			}
	
			socketAlCliente.close();
		}
	}catch(Exception e){
		System.out.println("Error de conexion en GestionLibros");
		e.printStackTrace();
	}
  }
}
