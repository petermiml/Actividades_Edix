package secundario;

public class Coche {
	private int id;
    private String matricula, modelo, color;

    public Coche(int id, String matricula, String modelo, String color){
        this.id = id;
        this.matricula = matricula;
        this.modelo = modelo;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ", Matrícula: " + matricula + 
                ", Modelo: " + modelo + 
                ", Color: " + color;
    }
}
