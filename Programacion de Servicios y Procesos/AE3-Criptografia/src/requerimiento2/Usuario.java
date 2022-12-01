package requerimiento2;

import java.io.Serializable;

//Tenemos que crear 3 usuarios con nombre y contraseña hasheada. Para ello debemos encriptar el objeto Usuario.

public class Usuario implements Serializable {
	
	private String nombre, contraseña;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public Usuario(String nombre, String contraseña) {
		super();
		this.nombre = nombre;
		this.contraseña = contraseña;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", contraseña=" + contraseña + "]";
	}
	
	
	
	
	

	
}
