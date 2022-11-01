package servidor;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServidor {

	public static final int PUERTO = 2023;
	public static int usuario;
	
	// Creamos un objeto de tipo biblioteca en el servidor para que todos los hilos trabajen con la misma.
	public static Biblioteca biblioteca = new Biblioteca();
	
	public static void main(String[] args) {
		
		System.out.println("Servidor conectando");
		
		// Creamos objeto socket para establecer la conexion posteriormente
		Socket socketAlCliente = null;
		
		
		try (ServerSocket servidor = new ServerSocket()){
			
			// Encapsulamos el puerto en un objeto de tipo InetSocketAddress
			InetSocketAddress direccionAlCliente = new InetSocketAddress(PUERTO);
			
			// Con este metodo, mantenemos atento el sevidor para atender la peticiones de los clientes.
			servidor.bind(direccionAlCliente);
			
			// Creamos un while(true) para que el servidor se mantenga siempre a la escucha
			while(true) {
				System.out.println("SERVIDOR: Esperando peticion por el puerto " + PUERTO);
				
				//Con este metodo acceptamos la conexion al cliente y el servidor para y queda a la espera de que entre una peticion de 
				// un cliente, y en ese momento se creara un objeto de tipo socket.
				socketAlCliente = servidor.accept();
				usuario++;
				System.out.println("SERVIDOR: Usuario numero " + usuario + " recibido");
				
				/*
				 * Aqui creamos un hilo para cada peticion de los clientes y lo arrancamos. Le pasamos por parámetro al constructor el socket 
				 * con el cliente, la biblioteca que hemos creado y el usuario.
				 */
				
				Thread h1 = new HiloGestionLibros(socketAlCliente, biblioteca, usuario);	
				h1.start();
			
			}
		} catch (Exception e) {
			System.out.println("Error de conexion");
		}

	}

}


