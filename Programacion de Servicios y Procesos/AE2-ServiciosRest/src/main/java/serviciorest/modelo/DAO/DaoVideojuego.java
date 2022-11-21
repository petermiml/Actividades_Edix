package serviciorest.modelo.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import serviciorest.modelo.entidad.VideoJuego;

//Aprovechando la explicación en clases, de lo que es un patrón DAO (data access Object), y en relación con lo
//que pide el ejercicio de que nuestra app tiene que tener 5 videojuegos preestablecidos con todos los datos
//rellenos, simularemos que los datos están guardados en una BBDD.


/*
 * @Component nos permite dar de alta un unico objeto de esta clase (DaoVideojuego) dentro del contexto 
 * de Spring.
 * 
 */

@Component
public class DaoVideojuego {
	
	public List<VideoJuego> listaVideojuegos;
	public int contador;
	
public DaoVideojuego() {
	
	System.out.println("DaoVideojuego ---> Se está creando la lista de nuestros videojuegos");
	listaVideojuegos = new ArrayList<VideoJuego>();
	
	VideoJuego game1 = new VideoJuego(contador++, "League of Legends", "Riot Games", 8);
	VideoJuego game2 = new VideoJuego(contador++, "Call of duty", "Activision", 7);
	VideoJuego game3 = new VideoJuego(contador++, "Pokemon", "Nintendo", 8);
	VideoJuego game4 = new VideoJuego(contador++, "Zelda", "Nintendo", 6);
	VideoJuego game5 = new VideoJuego(contador++, "Finla Fantasy", "Square Enix", 6);
	
	listaVideojuegos.add(game1);
	listaVideojuegos.add(game2);
	listaVideojuegos.add(game3);
	listaVideojuegos.add(game4);
	listaVideojuegos.add(game5);
	
}

// Método para dar de alta un videojuego en la lista. V es el videojuego que queremos añadir.

public  void addGame(VideoJuego v) {

				v.setId(contador++);
				listaVideojuegos.add(v);
}

 // Método para dar de baja un videjuego por ID. Sabemos que el id es igual a la posicion en el array,
 //por lo tanto pasandole el indice, borraremos el juego que se encuentre en éste.

public VideoJuego deleteGame(int posicion) {
	try {
		return listaVideojuegos.remove(posicion);
	}catch(IndexOutOfBoundsException e) {
		System.out.println("Posicion fuera de rango");
		return null;
	}
}
	
// Método para modificar un videojuego por ID. Pasamos el ID y modificamos el videojuego que esté en esa posición.
public VideoJuego update (VideoJuego v) {
	try {
		VideoJuego gAux = listaVideojuegos.get(v.getId());
		gAux.setNombre(v.getNombre());
		gAux.setCompañia(v.getCompañia());
		gAux.setNota(v.getNota());
		
		return gAux;
	} catch (IndexOutOfBoundsException ee) {
		
		System.out.println("No se puede modificar, videojuego fuera de rango");
		return null;
	}
}

//Método para obtener un videojuego por ID.

public VideoJuego get (int posicion) {
	try {
		return listaVideojuegos.get(posicion);
	}catch (IndexOutOfBoundsException e) {
		System.out.println("Videojuego fuera de rango");
		return null;
	}

}

//Método para listar todos los videojuegos.

public List<VideoJuego> list(){
return listaVideojuegos;	
}

}
