package servidor;

import java.util.ArrayList;

public class Biblioteca{

		// Declaramos las propiedades de la clase.
		public ArrayList <Libro> libros = new ArrayList<Libro>();	// Arraylist que almacenará los libros de la biblioteca

		// Creamos los libros iniciales que indica el ejercicio.
		Libro libro1 = new Libro("23568525","El senor de los anillos","John Ronald Reuel Tolkien", "20");
		Libro libro2 = new Libro("55258771","El Silmarillion","John Ronald Reuel Tolkien", "23");
		Libro libro3 = new Libro("85857895","1984", "George Orwell", "10");
		Libro libro4 = new Libro("32125455","Rebelion en la granja", "George Orwell", "15");
		Libro libro5 = new Libro("96584325","Los pilares de la Tierra", "Ken Follett", "30");
		
		// Cuando se crea el objeto biblioteca, se anaden los libros especificados por el ejercicio al ArrayList
		public Biblioteca() {
			super();
			this.libros.add(libro1);
			this.libros.add(libro2);
			this.libros.add(libro3);
			this.libros.add(libro4);
			this.libros.add(libro5);
		}

		// Getters y Setters
		
		public ArrayList <Libro> getLibros() {
			return libros;
		}

		public void setLibros(ArrayList <Libro> libros) {
			this.libros = libros;
		}
		
		
		// Métodos especificados en el ejercicio:
		
		// Como el ejercicio indicaba, solo un usuario puede añadir un libro a la vez, por lo que hicimos que el metodo para ello fuera sincronizado.
		// A este metodo se le pasan los datos necesarios para la creacion de un objeto de tipo Libro.
		
		public synchronized void addLibro(String isbn, String titulo, String autor, String precio) {
			Libro nLibro = new Libro(isbn, titulo, autor, precio);	// Se crea un objeto Libro con los datos pasados por parametro.
			this.libros.add(nLibro);								// se usa el metodo add del ArrayList libros.
		}
		
		// Recorremos el ArrayList, y de no estar el isbn, devolverá una cadena indicándolo, de lo contrario, devolverá el toString del libro con dicho ISBN.
		public String buscarLibroIsbn (String isbn) {
			for(Libro l:libros) {
				if (l.getIsbn().equalsIgnoreCase(isbn)) {
					return l.toString();
				}
			}
					return ("El ISBN no existe");
				
		}
		
		// Igual que el método anterior pero pasando un titulo como parametro
		public String buscarLibroTitulo (String titulo) {
			for(Libro l:libros) {
				if (l.getTitulo().equalsIgnoreCase(titulo)) {
					return l.toString();
				}
			}
				return ("El titulo no existe");		
		}
		
		/* Aquí la cosa se complicó un poco más, ya que solo nos devolvía el primer libro de los varios posibles. Lo que se nos ocurrió fue lo siguiente: 
		 * 
		 * En primer lugar, creamos 2 Strings
		 * F
		 * */
		
		public String buscarLibroAutor (String autor) {
			String listaLibros = "";				// String que almacenará los libros encontrados.
			String mensaje ="El autor no existe";	// Mensaje que devolverá el método.
			
			// Recorremos el ArrayList y si encontramos libros lo añadimos a listaLibros separado por un guion a lo que haya anteriormente.
			// En el hilo lo separaremos con el metodo split.
			// Finalmente igualamos el contenido de "mensaje" a "listaLibros". Si no hubiera encontrado ninguno, ya habia sido inicializado con
			// el String "El autor no existe".
			for(Libro l:libros) {
				if (l.getAutor().equalsIgnoreCase(autor)) {
					listaLibros += "-" + l.toString();
					mensaje = listaLibros;
				}
			}
			
			// Devolvemos el contenido de "mensaje"
			return mensaje;
		}
}