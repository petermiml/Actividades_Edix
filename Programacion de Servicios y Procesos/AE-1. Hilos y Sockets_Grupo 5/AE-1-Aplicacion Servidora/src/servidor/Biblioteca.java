package servidor;

import java.util.ArrayList;

public class Biblioteca{

		private Libro libro;
		private ArrayList <Libro> libros = new ArrayList<Libro>();

		Libro libro1 = new Libro("23568525","El senor de los anillos","John Ronald Reuel Tolkien", 20);
		Libro libro2 = new Libro("55258771"," El Silmarillion","John Ronald Reuel Tolkien", 23);
		Libro libro3 = new Libro("85857895","1984", "George Orwell", 10);
		Libro libro4 = new Libro("32125455","Rebelion en la granja", "George Orwell", 15);
		Libro libro5 = new Libro("96584325","Los pilares de la Tierra", "Ken Follett", 30);
		
		// Cuando se crea el objeto biblioteca, se añaden los libros especificados por el ejercidio al ArrayList
		public Biblioteca() {
			super();
			this.libros.add(libro1);
			this.libros.add(libro2);
			this.libros.add(libro3);
			this.libros.add(libro4);
			this.libros.add(libro5);
		}

		public ArrayList <Libro> getLibros() {
			return libros;
		}

		public void setLibros(ArrayList <Libro> libros) {
			this.libros = libros;
		}

		public Libro getLibro() {
			return libro;
		}

		public void setLibro(Libro libro) {
			this.libro = libro;
		} 
		
		public synchronized void addLibro(Libro libro) {
			 libros.add(libro);	 
		}
}