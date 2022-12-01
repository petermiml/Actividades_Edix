package CriptografiaHashing;
//Creamos una clase Usuario para poder crea e instanciar objetos en la aplicación 
public class Usuario {
	
	private String nombreUser, contraseña;
	
	public Usuario(String nombreUser,String contraseña) {
		super();
		this.nombreUser = nombreUser;
		this.contraseña = contraseña;
	}

	public String getNombreUser() {
		return nombreUser;
	}

	public String getContraseña() {
		return contraseña;
	}

}
