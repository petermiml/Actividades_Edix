package servidor;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServidor {

	public static final int PUERTO = 2023;
	public static Biblioteca biblioteca = new Biblioteca();
	
	public static void main(String[] args) {
		
		//Biblioteca biblioteca = new Biblioteca();
		
		System.out.println("Servidor conectando");
		
		// Creamos objeto socket para establecer la conexion posteriormente
		Socket socketAlCliente = null;
		int usuario = 0;
		
		try (ServerSocket servidor = new ServerSocket()){
			
			InetSocketAddress direccionAlCliente = new InetSocketAddress(PUERTO);
			//Con este metodo, mantenemos atento el sevidor para atender la peticiones de los clientes.
			servidor.bind(direccionAlCliente);
			
			while(true) {
			System.out.println("SERVIDOR: Esperando peticion por el puerto " + PUERTO);
			
			//Con este metodo acceptamos la conexion al cliente.
			socketAlCliente = servidor.accept();
			System.out.println("SERVIDOR: Usuario numero " + ++usuario + " recibido");
			
			//Aqui creamos un hilo para cada peticion de los clientes.
			Thread h1 = new HiloGestionLibros(socketAlCliente, biblioteca);
			
			h1.start();
			
			}
		} catch (Exception e) {
			System.out.println("Error de conexion");
		}

	}

}


