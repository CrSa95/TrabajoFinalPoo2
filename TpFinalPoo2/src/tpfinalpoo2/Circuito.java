package tpfinalpoo2;

import java.util.ArrayList;
import java.util.List;

public class Circuito {
	
	private List<Tramo> tramos = new ArrayList<>();
	
	public void agregarTramo(Tramo tramo) {
		this.getTramos().add(tramo);
	}
	
	public List<Tramo> getTramos() {
		return this.tramos;
	}
	
	public double costoCircuito() {
		return tramos.stream().mapToDouble(Tramo::getCosto).sum();
	}
	
	public double tiempoTotal() {
		return tramos.stream().mapToDouble(Tramo::getDuracion).sum();
	}
	
}
