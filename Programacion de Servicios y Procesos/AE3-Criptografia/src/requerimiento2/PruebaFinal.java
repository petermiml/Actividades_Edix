package requerimiento2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import CriptografiaHashing.Encriptador;

public class PruebaFinal {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		//vARIABLES QUE VAMOS A UTILIZAR PARA EL MENÚ
		Scanner sc = new Scanner(System.in);
		KeyGenerator generador = null;
		SecretKey claveSimetrica = null;
		String frase = "";
		String fraseCifrada = "";
		byte[] byteFraseCifrada = null;
		String opc = "";
		String nombreUsuario = "";
		String contraseña = "";
		int numeroIntentos = 3;
		int intentos = 0;
		int intentosContraseña = 0;
		
		
		
		// ================ Creamos la clave simetrica ======================
		try {
		// Creamos el objeto que genera la clave simetrica e indicamos que usara el algoritmo DES
			generador = KeyGenerator.getInstance("DES");
						
		// Creamos la clave simetrica y la almacenamos en "claveSimetrica" que es un objeto de tipo SecretKey
			claveSimetrica = generador.generateKey();
						
			}catch(NoSuchAlgorithmException e) {
				System.out.println("Error al crear y configurar el descifrador.");
			}
		
		
		//Creamos los usuarios que queremos tener guardados en la aplicación ,con su nombre de usuario y la contraseña hasheada.
		
				Usuario u1 = new Usuario("Pedro Gomez","null"); //Vamos a usar las contraseñas que hasheamos, podemos cambiar el método por el que ha hecho ahmed.
				Usuario u2 = new Usuario("Ahmed Daali", "null");
				Usuario u3 = new Usuario("Daniel Rodriguez", "null");
				
		//Queremos hashear las contraseñas que hemos establecido a nuestros usuarios. Para ello tenemos que pasarlas a bytes.
				
				byte[] contraseña1 = "123".getBytes();
				byte[] contraseña2 = "1234".getBytes();
				byte[] contraseña3 = "12345".getBytes();
						
		//Creamos un objeto MessageDigest al que le pasamos el tipo de algoritmo que vamos a utilizar.
						
		//RESUMEN HASH DE CONTRASEÑA DEL USUARIO 1
				MessageDigest md1 = MessageDigest.getInstance("SHA-512");
				md1.update(contraseña1);
				byte[] resumenHash1 = md1.digest();
				String mensaje1 = new String(resumenHash1);
						
		//RESUMEN HASH DE CONTRASEÑA DEL USUARIO 2
				MessageDigest md2 = MessageDigest.getInstance("SHA-512");
				md2.update(contraseña2);
				byte[] resumenHash2 = md2.digest();
				String mensaje2 = new String(resumenHash2);
						
		//RESUMEN HASH DE CONTRASEÑA DEL USUARIO 3
				MessageDigest md3 = MessageDigest.getInstance("SHA-512");
				md3.update(contraseña3);
				byte[] resumenHash3 = md3.digest();
				String mensaje3 = new String(resumenHash3);
						
						
		//LOGICA DEL PROGRAMA:
				//1º: Queremos que nos pida el nombre de usuario, mientras que el numero de intentos sea menor que 3.
						
				do {
					
					//intentos++;
					
					System.out.println("Introduce el nombre de usuario:");
					nombreUsuario = sc.nextLine();
					
						if(nombreUsuario.equals(u1.getNombre()) || nombreUsuario.equals(u2.getNombre()) || nombreUsuario.equals(u3.getNombre())) {
							
							do {
							System.out.println("introduce contraseña:");
							contraseña = sc.nextLine();
							
							// Hasheamos la contraseña que nos dan por consola.
							byte[] contraseñaHash = contraseña.getBytes();
							MessageDigest md4 = MessageDigest.getInstance("SHA-512");
							md4.update(contraseñaHash);
							byte[] resumenHashPwd = md4.digest();
							String mensaje = new String(resumenHashPwd);
							
							
							
								if((mensaje.equals(mensaje1) || mensaje.equals(mensaje2) || mensaje.equals(mensaje3))) {
								
									System.out.println("Contraseña correcta,has entrado en el sistema");
									
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
											    System.out.print("Indica a continuación la frase que deseas encriptar: \n\n>> ");
											    frase = sc.nextLine();
												System.out.println("");
													
												// Encriptamos la frase y la almacenamos en byteFraseCifrada que es de tipo byte[]
												byteFraseCifrada = Encriptador.encriptarFrase(frase, claveSimetrica);
													
												// Lo pasamos a String para poder mostrarlo por pantalla.
												fraseCifrada = new String(byteFraseCifrada);
												System.out.println("La frase introducida se ha encriptado correctamente.\n\nLa frase encriptada quedaría así: \n    " + fraseCifrada + "\n\n");
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
												System.out.println("Programa finalizado");
												//sc.close();
												break;	
													
												default:
												System.out.println("Por favor, elige una opción válida.");
												break;
											}
										}while(!opc.equals("0"));
									
								}else{
									intentosContraseña++;
									System.out.println("Contraseña incorrecta, quedan" + " " + --numeroIntentos + " " + "intentos restantes");
									}
						}while(intentosContraseña<3);
						}else {
							intentos++;
							System.out.println("Usuario incorrecto, te quedan" + " " + --numeroIntentos + " " + "intentos restantes");
							
						}
						
				}while(intentosContraseña!=3);
				System.out.println("Intentalo más tarde, has agotado el número de intentos");
				sc.close();
		}
	
				
				
	}


