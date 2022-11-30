package CriptografiaHashing;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;


import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;


public class Aplicacion {

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException{
		
	// ================ Declaramos e inicializamos los objetos que vamos a necesitar ================
			Scanner sc = new Scanner(System.in);	
			KeyGenerator generador = null;
			SecretKey claveSimetrica = null;
			String frase = "";
			String fraseCifrada = "";
			byte[] byteFraseCifrada = null;
			String opc = "";
			String nombreUsuario = "";
			String contraUsuario = "";
			int intentos=0;
			int numeroIntentos=3;
				
			// ================ Creamos la clave simetrica ======================
			try {
				// Creamos el objeto que genera la clave simetrica e indicamos que usara el algoritmo DES
				generador = KeyGenerator.getInstance("DES");
					
				// Creamos la clave simetrica y la almacenamos en "claveSimetrica" que es un objeto de tipo SecretKey
				claveSimetrica = generador.generateKey();
					
			}catch(NoSuchAlgorithmException e) {
				System.out.println("Error al crear y configurar el descifrador.");
			}
				
			// ================== Creamos los usuarios ====================

			Usuario usuario1 = new Usuario("Tony","1111" );
			Usuario usuario2 = new Usuario("Jhon","2222" );
			Usuario usuario3 = new Usuario("Rob","3333" );

				
			//Queremos hashear las contraseñas que hemos creado de nuestros usuarios. Para ello tenemos que pasarlas a bytes.
			byte[] usu1Nohash = usuario1 .getNombreUser().getBytes();
			byte[] usu1Cohash = usuario1.getContraseña() .getBytes();
			byte[] usu2Nohash = usuario2 .getNombreUser().getBytes();
			byte[] usu2Cohash = usuario2.getContraseña() .getBytes();
			byte[] usu3Nohash = usuario3 .getNombreUser().getBytes();
			byte[] usu3Cohash = usuario3.getContraseña() .getBytes();
				
			//Creamos un objeto MessageDigest al que le pasamos el tipo de algoritmo que vamos a utilizar.	
			MessageDigest md1 = MessageDigest.getInstance("SHA-512");
			MessageDigest md2 = MessageDigest.getInstance("SHA-512");
			MessageDigest md3 = MessageDigest.getInstance("SHA-512");
			MessageDigest md4 = MessageDigest.getInstance("SHA-512");
			MessageDigest md5 = MessageDigest.getInstance("SHA-512");
			MessageDigest md6 = MessageDigest.getInstance("SHA-512");
			md1.update(usu1Nohash);
			md2.update(usu1Cohash);
			md3.update(usu2Nohash);
			md4.update(usu2Cohash);
			md5.update(usu3Nohash);
			md6.update(usu3Cohash);
			
			// Ejecutamos el metodo "digest()" del objeto MessageDigest para obtener el resumen, que sera una cadena de bytes.
			byte[] resumen1 = md1.digest();
			byte[] resumen2 = md2.digest();
			byte[] resumen3 = md3.digest();
			byte[] resumen4 = md4.digest();
			byte[] resumen5 = md5.digest();
			byte[] resumen6 = md6.digest();
			String mensaje1 = new String(resumen1);
			String mensaje2 = new String(resumen2);
			String mensaje3 = new String(resumen3);
			String mensaje4 = new String(resumen4);
			String mensaje5 = new String(resumen5);
			String mensaje6 = new String(resumen6);
			
		// ================== Creamos el menu =================================
			
			do {
				System.out.println("Introduce el nombre de usuario: ");
				nombreUsuario = sc.nextLine();
				intentos++;
				
				//Hasheamos el nombre de uauario que nos entra por el scanner
				byte[] nombreUsHash = nombreUsuario.getBytes();
				MessageDigest md7 = MessageDigest.getInstance("SHA-512");
				md1.update(nombreUsHash);
				byte[] resumen7 = md7.digest();
				String mensaje7 = new String(resumen7);
				
				if(intentos <= numeroIntentos && nombreUsuario.equals(usuario1 .getNombreUser())|| mensaje7.equals(mensaje2) || mensaje7.equals(mensaje3) ) {
					
					System.out.println("Introduce la contraseña: ");
					contraUsuario = sc.nextLine();
					
					//Hasheamos la contraseña del uauario que nos entra por el scanner
					byte[] contUsHash = contraUsuario.getBytes();
					MessageDigest md8 = MessageDigest.getInstance("SHA-512");
					md1.update(contUsHash);
					byte[] resumen8 = md8.digest();
					String mensaje8 = new String(resumen8);
					
					if(intentos <= numeroIntentos && contraUsuario.equals(usuario1.getContraseña()) || mensaje8.equals(mensaje5) || mensaje8.equals(mensaje6) ) {
					
					//Si todo esto es correcto, sacamos el menú creado para el requerimiento 1.
					System.out.println("Hola " + nombreUsuario + " estás en el menú de encriptado");
					System.out.println("Indica que deseas hacer:\n");
					System.out.println("	0. Salir del programa.");
					System.out.println("	1. Encriptar frase.");
					System.out.println("	2. Desencriptar frase.");
					System.out.print("\n>> ");
					
					opc = sc.nextLine();
					
					
					System.out.println("");
				
					
					switch (opc) {
						case "1":
							
							// Pedimos al usuario la frase.
							System.out.print("Indica a continuaci�n la frase que deseas encriptar: \n\n>> ");
							frase = sc.nextLine();
							System.out.println("");
							
							// Encriptamos la frase y la almacenamos en byteFraseCifrada que es de tipo byte[]
							byteFraseCifrada = Encriptador.encriptarFrase(frase, claveSimetrica);
							
							// Lo pasamos a String para poder mostrarlo por pantalla.
							fraseCifrada = new String(byteFraseCifrada);
							System.out.println("La frase introducida se ha encriptado correctamente.\n\nLa frase encriptada quedar�a as�: \n    " + fraseCifrada + "\n\n");
							break;
							
						case "2":
							// Si el usuario pulsa el 2 y a�n no se ha encriptado nada se le indicar� al usuario que no hay ninguna frase almacenada en memoria.
							if(fraseCifrada.equals("")) {
								System.out.println("No hay ninguna frase almacenada en memoria.");
							}else {
								// Desencriptamos la frase y la almacenamos en fraseDescifrada que es de tipo String para mostrarlo al usuario
								String fraseDescifrada = Encriptador.desencriptarFrase(byteFraseCifrada, claveSimetrica);
								System.out.println("La frase se ha desencriptado correctamente.\n\nLa frase en memoria desencriptada es: " + fraseDescifrada);
							}
							
							break;
						case "0":
							System.out.println("Saliendo del programa...");
							break;	
							
						default:
							System.out.println("Por favor, elige una opci�n v�lida.");
							break;
					}
						}else {
							System.out.println("Contraseña incorrecta");
							intentos++;
						}
				
				}else {
					System.out.println("Nombre de usuario incorrecto");
					System.out.println("Te quedan" + " " + --numeroIntentos + " " + "intentos disponibles");
					intentos++;
				}	
			
			}while(intentos <= numeroIntentos &&!opc.equals("0"));
		
	  
	}
	
}
