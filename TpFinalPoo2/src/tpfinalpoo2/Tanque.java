package tpfinalpoo2;

public class Tanque extends Container {
	public Tanque(String ownerID, Double altura, Double ancho, Double largo, Double peso) {
		super(ownerID, altura, ancho, largo, peso);
	}

	@Override
	public String tipo() {
		return "Tanque";
	}
}
