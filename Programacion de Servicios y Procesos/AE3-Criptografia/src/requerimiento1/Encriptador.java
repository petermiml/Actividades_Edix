package requerimiento1;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/* 
 	He creado esta clase por tener el código más organizado. Esta clase tiene dos métodos estáticos (no es necesario instanciarla
 	para utilizarlos) cuya función es encriptar y desencriptar la frase que se le pase por parámetro (junto con una clave simétrica).
 
 */

public class Encriptador {
	
	// Método para encriptar una frase. Recibirá una frase como String y una clave simétrica por parámetros y devolverá
	// la misma frase pasada a bytes y encriptada.
	public static byte[] encriptarFrase(String frase, SecretKey claveSimetrica) {
		
		
		byte[] byteFrase;
		byte[] byteMensajeCifrado = null;
				
		try {
			// Creamos el objeto cifrador indicando que usaremos el algoritmo AES
			Cipher cifrador = Cipher.getInstance("AES");
			
			// Inicializamos y configuramos el cifrador en modo encriptación y le pasamos la clave simétrica que nos llega por parámetro
			cifrador.init(Cipher.ENCRYPT_MODE,  claveSimetrica);
			
			
			byteFrase = frase.getBytes();
			
			// Encriptamos la frase previamente convertida a bytes[] y lo almacenamos en byteMensajeCifrado (
			byteMensajeCifrado = cifrador.doFinal(byteFrase);
			
			
			
		}catch(NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
			System.out.println("Error al crear y configurar el descifrador.");
			System.out.println(e.getMessage());
			
		}catch(IllegalBlockSizeException | BadPaddingException e) {
			System.out.println("Error al cifrar el mensaje");
		}
		
		// El método devolverá el mensaje cifrado convertido a bytes[]
		return byteMensajeCifrado;
	}
	
	// Método para desencriptar una frase. Recibirá una frase encriptada como bytes[] y una clave simétrica por parámetros y devolverá
	// la misma frase pasada a bytes y encriptada.
	public static String desencriptarFrase(byte[] byteFrase, SecretKey claveSimetrica) {
		String fraseDescifrada = "";
		
		try {
			
			// Creamos el objeto cifrador indicando que usaremos el algoritmo AES		
			Cipher descifrador = Cipher.getInstance("AES");
			
			// Inicializamos y configuramos el cifrador en modo desencriptación y le pasamos la clave simétrica que nos llega por parámetro
			descifrador.init(Cipher.DECRYPT_MODE,  claveSimetrica);
			
			// Desciframos la frase en bytes que nos llega por parámetro y lo alamacenamos en byteFraseDescifrada
			byte[] byteFraseDescifrada = descifrador.doFinal(byteFrase);
			
			// Lo convertimos a String
			fraseDescifrada = new String(byteFraseDescifrada);
					
		}catch(NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
			System.out.println("Error al crear y configurar el descifrador.");
			System.out.println(e.getMessage());
			
		}catch(IllegalBlockSizeException | BadPaddingException e) {
			System.out.println("Error al cifrar el mensaje");
				}
		
		// El método devolverá la String de la frase desencriptada.
		return fraseDescifrada;
	}

}
