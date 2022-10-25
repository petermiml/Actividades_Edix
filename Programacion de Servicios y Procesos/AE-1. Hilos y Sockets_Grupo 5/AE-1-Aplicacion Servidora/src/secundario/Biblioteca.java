package secundario;

	import java.io.BufferedReader;
	import java.io.InputStreamReader;
	import java.io.PrintStream;
	import java.net.Socket;
	import java.util.ArrayList;

	public class Biblioteca implements Runnable{
		Thread hilo;
		Socket socketAlCliente;
		private Libro libro;
		private ArrayList <Libro> libros;
		
		
		Libro libro1 = new Libro("23568525","El senor de los anillos","John Ronald Reuel Tolkien", 20);
		Libro libro2 = new Libro("55258771"," El Silmarillion","John Ronald Reuel Tolkien", 23);
		Libro libro3 = new Libro("85857895","1984", "George Orwell", 10);
		Libro libro4 = new Libro("32125455","Rebelion en la granja", "George Orwell", 15);
		Libro libro5 = new Libro("96584325","Los pilares de la Tierra", "Ken Follett", 30);
		
		public Biblioteca(ArrayList<Libro> libros) {
			super();
			this.libros = libros;
		}

		public ArrayList <Libro> getLibros() {
			return libros;
		}

		public void setLibros(ArrayList <Libro> libros) {
			this.libros = libros;
		}

		public Libro getLibro() {
			return libro;
		}

		public void setLibro(Libro libro) {
			this.libro = libro;
		} 
		
		public synchronized void anadirLibro(Libro libro) {
			 libros.add(libro);	 
		}

		public Biblioteca(Socket socketAlCliente){
			hilo = new Thread(this);
			this.socketAlCliente = socketAlCliente;
			hilo.start();
		}
		
		@Override
		public void run() {	
			InputStreamReader entrada = null;
			PrintStream salida = null;
			BufferedReader bf ;
		
		try {
			
			entrada = new InputStreamReader(socketAlCliente.getInputStream());
			bf = new BufferedReader(entrada);
			
			salida = new PrintStream(socketAlCliente.getOutputStream());
			
			String opcionRecibida = "";
			boolean continuar = true;
			
			while (continuar) {
			opcionRecibida = bf.readLine();
			System.out.println("El sevidor le ha llegado la opcion: "+ opcionRecibida);
			salida.println("El sevidor le ha llegado la opcion: "+ opcionRecibida);
			
			switch(opcionRecibida) {
			case "1":
				break;
				
			case "2":
				break;
				
			case "3":
				break;
				
			case "4":
				anadirLibro(libro);
				//libros.add(new Libro (opcionRecibida, opcionRecibida, opcionRecibida, 0)) ;
				break;	
				
			case "5":/*(opcionRecibida.trim().equalsIgnoreCase("5")) {*/
				System.out.println("Prueba case 5");
				salida.println("Fin de conexion");
				continuar = false;
				break;
			}
		
		   socketAlCliente.close();
			}
		}catch(Exception e){
			System.out.println("Error de conexion en GestionLibros");
		}
	}

}
