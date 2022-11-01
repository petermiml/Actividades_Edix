package servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class HiloGestionLibros extends Thread {
	
	// Propiedades de la clase
	
	Socket socketAlCliente;
	public Biblioteca biblioteca; 
	public SocketServidor socketServidor;
	public int usuario;
	
	// Constructor de la clase (se le pasa el socket al cliente, una biblioteca y un usuario por parámetro.
	public HiloGestionLibros(Socket socketAlCliente, Biblioteca biblioteca, int usuario){
		this.socketAlCliente = socketAlCliente;
		this.biblioteca = biblioteca;
		this.usuario = usuario;
		usuario++;
	}
	
	// Creamos el método run(), que será lo que se ejecute al entrar en el hilo.
	
	@Override
	public void run() {
		
		// Declaramos nuestra carretera de entrada, de salida y nuestro BufferedReader
		InputStreamReader entrada = null;
		PrintStream salida = null;
		BufferedReader bf = null;
	
		try {
			
			// Lo que nos llega por la carretera de entrada lo guardamos en nuestro objeto creado para tal efecto, y posteriormente se lo pasamos a 
			// nuestro BufferedReader por parametro para poder leerlo correctamente.
			entrada = new InputStreamReader(socketAlCliente.getInputStream());
			bf = new BufferedReader(entrada);
			
			//Creamos el objeto PrintStream que usara el socket como salida de datos.
			salida = new PrintStream(socketAlCliente.getOutputStream());
		
			String opcion = "";
			String datos = "";
			boolean continuar = true;
			
			// Mientras la variable continuar sea true, se ejecutará lo siguiente
			while (continuar) {
				
				// Creamos un array unidimensional con lo que nos llega del cliente separandolo por guion
				String[] opcionDatos = bf.readLine().split("-");
				
				// Almacenamos la opcion elegida en una variable y los datos en otra.
				opcion = opcionDatos[0];
				datos = opcionDatos[1];
			
				// Dependiendo de la opcion elgida se ejecutará una cosa u otra.
				switch(opcion) {
					case "1":
						// Devuelve al cliente la devolucion de datos del metodo buscarLibroIsbn de la biblioteca.
						salida.println(biblioteca.buscarLibroIsbn(datos)); 
						break;
						
					case "2":
						// Devuelve al cliente la devolucion de datos del metodo buscarLibroTitulo de la biblioteca.
						salida.println(biblioteca.buscarLibroTitulo(datos));
						break;
						
					case "3":
						// Devuelve al cliente la devolucion de datos del metodo buscarLibroAutor de la biblioteca.
						salida.println(biblioteca.buscarLibroAutor(datos));
						break;
						
					case "4":
						// Separamos los datos que nos han llegado en diferentes variables
						String isbn = opcionDatos[1];
						String titulo = opcionDatos[2];
						String autor = opcionDatos[3];
						String precio = opcionDatos[4];
						
						// Añade el libro a la biblioteca
						biblioteca.addLibro(isbn, titulo, autor, precio);	
	
						// Devolvemos cadena "[TITULO_LIBRO_AÑADIDO] añadido a la biblioteca"
						salida.println("El libro: \""+ titulo + "\" se ha añadido a la biblioteca.");	
						
						break;
					case "5":
						// Si la opcion es la quinta (que era la de cerrar)
						System.out.println(datos + usuario);
						continuar = false;						// Sale del bucle.
						break;
				}	
			}
			
			socketAlCliente.close();	// Cuando sale del bucle, cierra la conexion con el cliente.
	
		}catch(Exception e){
			System.out.println("Error de conexion en GestionLibros");
			e.printStackTrace();
		}
  }
}
