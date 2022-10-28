package servidor;

import java.util.ArrayList;

public class Biblioteca{
		
		private Libro libro;
		private ArrayList<Libro> libros= null;
		
		
		public Biblioteca(ArrayList<Libro> libros) {
			super();
			this.libros = libros;
			/*Libro libro1= new Libro("23568525","El senor de los anillos","John Ronald Reuel Tolkien", 20));
		    this.libros.add(new Libro("55258771"," El Silmarillion","John Ronald Reuel Tolkien", 23));
			this.libros.add(new Libro("85857895","1984", "George Orwell", 10));
		    this.libros.add(new Libro("32125455","Rebelion en la granja", "George Orwell", 15));
			this.libros.add(new Libro("96584325","Los pilares de la Tierra", "Ken Follett", 30));*/
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
		
		public synchronized void anadirLibro(Libro libro) {
			 libros.add(libro);	 
		}

}