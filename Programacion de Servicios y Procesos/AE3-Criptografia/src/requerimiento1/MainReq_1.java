package requerimiento1;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;


import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class MainReq_1 {

	public static void main(String[] args) throws IOException{
		
		// ================ Declaramos e inicializamos los objetos que vamos a necesitar ================
		Scanner lector = new Scanner(System.in);	
		KeyGenerator generador = null;
		SecretKey claveSimetrica = null;
		String frase = "";
		String fraseCifrada = "";
		byte[] byteFraseCifrada = null;
		String opc = "";
		
		// ================ Creamos la clave simétrica ======================
		try {
			// Creamos el objeto que genera la clave simétrica e indicamos que usará el algoritmo DES
			generador = KeyGenerator.getInstance("DES");
			
			// Creamos la clave simétrica y la almacenamos en "claveSimetrica" que es un objeto de tipo SecretKey
			claveSimetrica = generador.generateKey();
			
		}catch(NoSuchAlgorithmException e) {
			System.out.println("Error al crear y configurar el descifrador.");
		}
		
		// ================== Creamos el menu =================================
		
		do {
			System.out.println("======================== Bienvenido al encriptador/desencriptador de frases ========================\n");
			System.out.println("Indica qué deseas hacer:\n");
			System.out.println("	0. Salir del programa.");
			System.out.println("	1. Encriptar frase.");
			System.out.println("	2. Desencriptar frase.");
			System.out.print("\n>> ");
			
			opc = lector.nextLine();
			
			
			System.out.println("");
		
			
			switch (opc) {
				case "1":
					
					// Pedimos al usuario la frase.
					System.out.print("Indica a continuación la frase que deseas encriptar: \n\n>> ");
					frase = lector.nextLine();
					System.out.println("");
					
					// Encriptamos la frase y la almacenamos en byteFraseCifrada que es de tipo byte[]
					byteFraseCifrada = Encriptador.encriptarFrase(frase, claveSimetrica);
					
					// Lo pasamos a String para poder mostrarlo por pantalla.
					fraseCifrada = new String(byteFraseCifrada);
					System.out.println("La frase introducida se ha encriptado correctamente.\n\nLa frase encriptada quedaría así: \n    " + fraseCifrada + "\n\n");
					break;
					
				case "2":
					// Si el usuario pulsa el 2 y aún no se ha encriptado nada se le indicará al usuario que no hay ninguna frase almacenada en memoria.
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
					System.out.println("Por favor, elige una opción válida.");
					break;
			}
			
			
		}while(!opc.equals("0"));

	}

}
