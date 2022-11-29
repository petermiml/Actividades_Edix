package requerimiento1;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/* 
 	He creado esta clase por tener el c�digo m�s organizado. Esta clase tiene dos m�todos est�ticos (no es necesario instanciarla
 	para utilizarlos) cuya funci�n es encriptar y desencriptar la frase que se le pase por par�metro (junto con una clave sim�trica).
 
 */

public class Encriptador {
	
	// M�todo para encriptar una frase. Recibir� una frase como String y una clave sim�trica por par�metros y devolver�
	// la misma frase pasada a bytes y encriptada.
	public static byte[] encriptarFrase(String frase, SecretKey claveSimetrica) {
		
		
		byte[] byteFrase;
		byte[] byteMensajeCifrado = null;
				
		try {
			// Creamos el objeto cifrador indicando que usaremos el algoritmo DES
			Cipher cifrador = Cipher.getInstance("DES");
			
			// Inicializamos y configuramos el cifrador en modo encriptaci�n y le pasamos la clave sim�trica que nos llega por par�metro
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
		
		// El m�todo devolver� el mensaje cifrado convertido a bytes[]
		return byteMensajeCifrado;
	}
	
	// M�todo para desencriptar una frase. Recibir� una frase encriptada como bytes[] y una clave sim�trica por par�metros y devolver�
	// la misma frase pasada a bytes y encriptada.
	public static String desencriptarFrase(byte[] byteFrase, SecretKey claveSimetrica) {
		String fraseDescifrada = "";
		
		try {
			
			// Creamos el objeto descifrador indicando que usaremos el algoritmo DES		
			Cipher descifrador = Cipher.getInstance("DES");
			
			// Inicializamos y configuramos el descifrador en modo desencriptaci�n y le pasamos la clave sim�trica que nos llega por par�metro
			descifrador.init(Cipher.DECRYPT_MODE,  claveSimetrica);
			
			// Desciframos la frase en bytes que nos llega por par�metro y lo alamacenamos en byteFraseDescifrada
			byte[] byteFraseDescifrada = descifrador.doFinal(byteFrase);
			
			// Lo convertimos a String
			fraseDescifrada = new String(byteFraseDescifrada);
					
		}catch(NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
			System.out.println("Error al crear y configurar el descifrador.");
			System.out.println(e.getMessage());
			
		}catch(IllegalBlockSizeException | BadPaddingException e) {
			System.out.println("Error al cifrar el mensaje");
				}
		
		// El m�todo devolver� la String de la frase desencriptada.
		return fraseDescifrada;
	}

}
