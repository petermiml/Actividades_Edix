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
		String mensaje4 ="";
		int intentos = 0;
		int intentCont = 0;
		int numeroIntentos=3;
		int numIntentCont=3;
				
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

		Usuario usuario1 = new Usuario("Tony","�_0�,��E/9�V�3�B");//1111
		Usuario usuario2 = new Usuario("Jhon","���W�j*D��Ե5�^'��=�" );//2222
		Usuario usuario3 = new Usuario("Rob","�mcQ�q��޾��5%� 6z" );//3333

				
	//Queremos hashear las contraseñas que hemos creado de nuestros usuarios. Para ello tenemos que pasarlas a bytes.
			
		byte[] usu1Cohash = usuario1.getContraseña() .getBytes();
		byte[] usu2Cohash = usuario2.getContraseña() .getBytes();
		byte[] usu3Cohash = usuario3.getContraseña() .getBytes();
				
	//Creamos un objeto MessageDigest al que le pasamos el tipo de algoritmo que vamos a utilizar.	
		MessageDigest md1 = MessageDigest.getInstance("SHA");
		MessageDigest md2 = MessageDigest.getInstance("SHA");
		MessageDigest md3 = MessageDigest.getInstance("SHA");
		md1.update(usu1Cohash);
		md2.update(usu2Cohash);
		md3.update(usu3Cohash);
			
	// Ejecutamos el metodo "digest()" del objeto MessageDigest para obtener el resumen, que sera una cadena de bytes.
			
		byte[] resumen1 = md1.digest();
		byte[] resumen2 = md2.digest();
		byte[] resumen3 = md3.digest();
		String mensaje1 = new String(resumen1);
		String mensaje2 = new String(resumen2);
		String mensaje3 = new String(resumen3);
			
	// ================== Creamos el menu =================================
			
		do {
		    System.out.println("Introduce el usuario: ");	
			nombreUsuario = sc.nextLine();
			intentos++;

				
			if(nombreUsuario.equals(usuario1.getNombreUser())||
			    nombreUsuario.equals(usuario2.getNombreUser())||
			    nombreUsuario.equals(usuario3.getNombreUser())){
			    intentos = 3;
			   do{
				System.out.println("Introduce la contraseña: ");
				contraUsuario = sc.nextLine();
		        //Hasheamos la contraseña del uauario que nos entra por el scanner
				byte[] contUsHash = contraUsuario.getBytes();
				MessageDigest md4 = MessageDigest.getInstance("SHA");
				md4.update(contUsHash);
				byte[] resumen4 = md4.digest();
				mensaje4 = new String(resumen4);
	
				//Si todo esto es correcto, sacamos el menú creado para el requerimiento 1.
				if( mensaje4.equals(usuario1.getContraseña())||
					mensaje4.equals(usuario2.getContraseña())||
					mensaje4.equals(usuario3.getContraseña())){
					intentCont=3;
					do {
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
							    System.out.print("Indica a continuacion la frase que deseas encriptar: \n\n>> ");
							    frase = sc.nextLine();
								System.out.println("");
									
								// Encriptamos la frase y la almacenamos en byteFraseCifrada que es de tipo byte[]
								byteFraseCifrada = Encriptador.encriptarFrase(frase, claveSimetrica);
									
								// Lo pasamos a String para poder mostrarlo por pantalla.
								fraseCifrada = new String(byteFraseCifrada);
								System.out.println("La frase introducida se ha encriptado correctamente.\n\nLa frase encriptada quedara asi: \n    " + fraseCifrada + "\n\n");
								break;
								
							case "2":
								// Si el usuario pulsa el 2 y a�n no se ha encriptado nada se le indicara al usuario que no hay ninguna frase almacenada en memoria.
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
								//sc.close();
								break;	
									
								default:
								System.out.println("Por favor, elige una opcion valida.");
								break;
							}
						}while(!opc.equals("0"));
					
				}else {
					System.out.println("Contraseña incorrecta");
					System.out.println("Te quedan" + " " + --numIntentCont + " " + "intentos disponibles\n");
					intentCont++;
				}
				}while(intentCont!=3);
			} else {
				System.out.println("Nombre de usuario incorrecto\n");
				System.out.println("Te quedan" + " " + --numeroIntentos + " " + "intentos disponibles\n");	
			}			
	      }while(intentos!=3);
			
			sc.close();
			System.out.println("Fin de sesión");
	}
}

