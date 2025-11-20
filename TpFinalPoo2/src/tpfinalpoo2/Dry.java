package tpfinalpoo2;

import java.util.ArrayList;

public class Dry extends Container {
	private ArrayList<Dry> bls = new ArrayList<>();

	public Dry(String ownerID, Double altura, Double ancho, Double largo, Double peso) {
		super(ownerID, altura, ancho, largo, peso);
	}

	public void agregarProducto(Dry dry) {
		bls.add(dry);
	}

	@Override
	public Double peso() {
		return this.peso + bls.stream().mapToDouble(Dry::peso).sum();
	}

	@Override
	public String tipo() {
		return "Seco";
	}
	
	@Override 
	public boolean desconsolidable() { return true;	}

}
