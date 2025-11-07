package tpfinalpoo2;

public class Cliente {
	
	private String nombre;
	private String dni;
	
	public Cliente(String nombre, String dni) {
		this.nombre = nombre;
		this.dni = dni;
	}
	
	public String nombre() {
		return this.nombre;
	}
	
	public String dni() {
		return this.dni;
	}
}