package servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class HiloGestionLibros extends Thread {
	
	Thread hilo;
	Socket socketAlCliente;
	public Biblioteca biblioteca; 
	public SocketServidor socketServidor;
	public int usuario;
	public HiloGestionLibros(Socket socketAlCliente, Biblioteca biblioteca, int usuario){
		
		hilo = new Thread(this);
		this.socketAlCliente = socketAlCliente;
		this.biblioteca = biblioteca;
		this.usuario = usuario;
		usuario++;
	}
	
	public synchronized void addLibro(String isbn, String titulo, String autor, String precio) {
		
		Libro nlibro = new Libro(isbn, titulo, autor, precio);
		biblioteca.addLibro(nlibro);
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
					
					salida.println(biblioteca.buscarLibroIsbn(datos));
					break;
					
				case "2":
					
					salida.println(biblioteca.buscarLibroTitulo(datos));
					break;
					
				case "3":
					salida.println(biblioteca.buscarLibroAutor(datos));
					break;
					
				case "4":
					
					String isbn = opcionDatos[1];
					String titulo = opcionDatos[2];
					String autor = opcionDatos[3];
					String precio = opcionDatos[4];
					
					// Añade el libro a la biblioteca
					addLibro(isbn, titulo, autor, precio);	

					// Devolvemos cadena "[TITULO_LIBRO_AÑADIDO] añadido"
					salida.println("El libro: "+ titulo + " se ha añadido.");	// SALTA ERROR
					
					break;
				case "5":
				System.out.println(datos + usuario);
					continuar = false;
					break;
			}	
		}
		socketAlCliente.close();

	}catch(NullPointerException e) {
		
	}catch(Exception e){
		System.out.println("Error de conexion en GestionLibros");
		e.printStackTrace();
	}
  }
}
