package requerimiento2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class MainReq_2 {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		//Creamos los usuarios que queremos tener guardados en la aplicación ,con su nombre de usuario y la contraseña hasheada.
		
		Usuario u1 = new Usuario("Pedro Gomez", "123");
		Usuario u2 = new Usuario("Ahmed Daali", "1234");
		Usuario u3 = new Usuario("Daniel Rodriguez", "12345");
		
		//Queremos hashear las contraseñas que hemos creado de nuestros usuarios. Para ello tenemos que pasarlas a bytes.
		
		byte[] contraseña1 = u1.getContraseña().getBytes();
		byte[] contraseña2 = u2.getContraseña().getBytes();
		byte[] contraseña3 = u3.getContraseña().getBytes();
		
		//Creamos un objeto MessageDigest al que le pasamos el tipo de algoritmo que vamos a utilizar.
		
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.update(contraseña1);
		md.update(contraseña2);
		md.update(contraseña3);
		
		
		
		
		Scanner sc = new Scanner(System.in);
		String nombreUsuario = "";
		String contraseña = "";
		int numeroIntentos = 3;
		int intentos = 0;
		
		
		
		
		do {
			System.out.println("Introduce el nombre de usuario: ");
			nombreUsuario = sc.nextLine();
			
			if(nombreUsuario.equals(u1.getNombre()) || nombreUsuario.equals(u2.getNombre()) || nombreUsuario.equals(u3.getNombre()) ) {
				
				System.out.println("Introduce la contraseña: ");
				contraseña = sc.nextLine();
			
				if(contraseña.equals(u1.getContraseña())|| contraseña.equals(u2.getContraseña()) || contraseña.equals(u3.getContraseña())) {
				
				//Si todo esto es correcto, sacamos el menú creado para el requerimiento 1.
				System.out.println("Hola " + nombreUsuario + " estás en el menú de encriptado");
				
					}else {
						System.out.println("Contraseña incorrecta");
						intentos++;
					}
			
			}else {
				System.out.println("Nombre de usuario incorrecto");
				System.out.println("Te quedan" + " " + --numeroIntentos + " " + "intentos disponibles");
				intentos++;
			}	
		
		}while(intentos <= numeroIntentos);
			
}
}

			
			

			
		

