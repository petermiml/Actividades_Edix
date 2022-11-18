package serviciorest.cliente.entidad;


//Nuestro servicio Cliente REST va a trabajar con la entidad VideoJuego.

public class VideoJuego {
	
	private int id;
	private String nombre;
	private String compañia;
	private int nota;
	
	public VideoJuego(int id, String nombre, String compañia, int nota) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.compañia = compañia;
		this.nota = nota;
	}

	public VideoJuego() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCompañia() {
		return compañia;
	}

	public void setCompañia(String compañia) {
		this.compañia = compañia;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	@Override
	public String toString() {
		return "VideoJuego [id=" + id + ", nombre=" + nombre + ", compañia=" + compañia + ", nota=" + nota + "]";
	}
	
	

}
