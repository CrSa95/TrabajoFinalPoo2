package tpfinalpoo2;

public class Cliente {
	
	private String nombre;
	private String dni;
  private String correo;
	
	public Cliente(String nombre, String dni, String correo) {
		this.nombre = nombre;
		this.dni = dni;
    this.correo = correo;
	}
	
	public String nombre() {
		return this.nombre;
	}
	
	public String dni() {
		return this.dni;
	} 

	public String correo() {
		return this.correo;
	}

	public Cliente() {
		this.correo = correo; // TO DO: Mover lo que use este constructor al otro
	}

	public void notificar() {
		// TODO Auto-generated method stub

	}

}
