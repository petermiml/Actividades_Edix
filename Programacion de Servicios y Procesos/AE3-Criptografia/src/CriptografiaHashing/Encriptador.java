package CriptografiaHashing;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/* 
 	He creado esta clase por tener el codigo mas organizado. Esta clase tiene dos metodos estaticos (no es necesario instanciarla
 	para utilizarlos) cuya funcion es encriptar y desencriptar la frase que se le pase por parametro (junto con una clave simetrica).
 
 */

public class Encriptador {
	
	// Metodo para encriptar una frase. Recibira una frase como String y una clave simetrica por parametros y devolvera
	// la misma frase pasada a bytes y encriptada.
	public static byte[] encriptarFrase(String frase, SecretKey claveSimetrica) {
		
		
		byte[] byteFrase;
		byte[] byteMensajeCifrado = null;
				
		try {
			// Creamos el objeto cifrador indicando que usaremos el algoritmo DES
			Cipher cifrador = Cipher.getInstance("DES");
			
			// Inicializamos y configuramos el cifrador en modo encriptacion y le pasamos la clave simetrica que nos llega por parametro
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
		
		// El m�todo devolvera el mensaje cifrado convertido a bytes[]
		return byteMensajeCifrado;
	}
	
	// Metodo para desencriptar una frase. Recibira una frase encriptada como bytes[] y una clave simetrica por parametros y devolvera
	// la misma frase pasada a bytes y encriptada.
	public static String desencriptarFrase(byte[] byteFrase, SecretKey claveSimetrica) {
		String fraseDescifrada = "";
		
		try {
			
			// Creamos el objeto descifrador indicando que usaremos el algoritmo DES		
			Cipher descifrador = Cipher.getInstance("DES");
			
			// Inicializamos y configuramos el descifrador en modo desencriptacion y le pasamos la clave simetrica que nos llega por parametro
			descifrador.init(Cipher.DECRYPT_MODE,  claveSimetrica);
			
			// Desciframos la frase en bytes que nos llega por paremetro y lo alamacenamos en byteFraseDescifrada
			byte[] byteFraseDescifrada = descifrador.doFinal(byteFrase);
			
			// Lo convertimos a String
			fraseDescifrada = new String(byteFraseDescifrada);
					
		}catch(NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
			System.out.println("Error al crear y configurar el descifrador.");
			System.out.println(e.getMessage());
			
		}catch(IllegalBlockSizeException | BadPaddingException e) {
			System.out.println("Error al cifrar el mensaje");
				}
		
		// El metodo devolvera la String de la frase desencriptada.
		return fraseDescifrada;
	}

}
