package requerimiento2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class MainReq_2 {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		//Creamos los usuarios que queremos tener guardados en la aplicación ,con su nombre de usuario y la contraseña hasheada.
		
		Usuario u1 = new Usuario("Pedro Gomez","null");
		Usuario u2 = new Usuario("Ahmed Daali", "null");
		Usuario u3 = new Usuario("Daniel Rodriguez", "null");
		
		//Queremos hashear las contraseñas que hemos creado de nuestros usuarios. Para ello tenemos que pasarlas a bytes.
		
				byte[] contraseña1 = "123".getBytes();
				byte[] contraseña2 = "1234".getBytes();
				byte[] contraseña3 = "12345".getBytes();
				
				//Creamos un objeto MessageDigest al que le pasamos el tipo de algoritmo que vamos a utilizar.
				
				MessageDigest md1 = MessageDigest.getInstance("SHA-512");
				md1.update(contraseña1);
				byte[] resumenHash1 = md1.digest();
				String mensaje1 = new String(resumenHash1);
				
				MessageDigest md2 = MessageDigest.getInstance("SHA-512");
				md2.update(contraseña2);
				byte[] resumenHash2 = md2.digest();
				String mensaje2 = new String(resumenHash2);
				
				MessageDigest md3 = MessageDigest.getInstance("SHA-512");
				md3.update(contraseña3);
				byte[] resumenHash3 = md3.digest();
				String mensaje3 = new String(resumenHash3);
				
		
		Scanner sc = new Scanner(System.in);
		String nombreUsuario = "";
		String contraseña = "";
		int numeroIntentos = 3;
		int intentos = 0;
		
		
		while (intentos != 3) {
			
			
			System.out.println("Introduce el nombre de usuario:");
			nombreUsuario = sc.nextLine();
			
				if(nombreUsuario.equals(u1.getNombre()) || nombreUsuario.equals(u2.getNombre()) || nombreUsuario.equals(u3.getNombre())) {
					
					while(intentos < 3) {
						
						System.out.println("introduce contraseña:");
						contraseña = sc.nextLine();
						
						// Hasheamos la contraseña que nos dan por consola.
						
						byte[] contraseñaHash = contraseña.getBytes();
						MessageDigest md4 = MessageDigest.getInstance("SHA-512");
						md4.update(contraseñaHash);
						byte[] resumenHashPwd = md4.digest();
						String mensaje = new String(resumenHashPwd);
						
						
						if (mensaje.equals(mensaje1) || mensaje.equals(mensaje2) || mensaje.equals(mensaje3)){
							System.out.println("Contraseña correcta");
						}
						intentos++;
					}
					
				}
				intentos++;
				if (intentos == 3) {
					System.out.println("Intentalo más tarde, has agotado los intentos");
				}
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
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
	*/		
}
}

			
			

			
		

