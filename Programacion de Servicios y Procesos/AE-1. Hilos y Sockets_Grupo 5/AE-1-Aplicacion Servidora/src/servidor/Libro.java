package servidor;

public class Libro {
	
	// Declaramos las propiedades de la clase

	private String isbn, titulo, autor, precio;
	
	// Constructor de la clase
	public Libro(String isbn, String titulo, String autor, String precio) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.precio = precio;
	}
	
	// Getters y Setters
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}

	// Método toString()
	@Override
	public String toString() {
		return "Libro -> || ISBN: " + isbn + " || Titulo: " + titulo + " || Autor: " + autor + " || Precio: " + precio + " ||";
	}
	
	
	
}