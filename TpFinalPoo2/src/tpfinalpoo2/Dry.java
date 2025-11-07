package tpfinalpoo2;

import java.awt.List;
import java.util.ArrayList;

import org.mockito.ArgumentMatchers;

public class Dry extends Container {
	private ArrayList<Dry> bls = new ArrayList<Dry>();
	
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
	

}
