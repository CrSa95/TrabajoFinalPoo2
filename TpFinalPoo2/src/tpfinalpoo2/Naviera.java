package tpfinalpoo2;

import java.util.ArrayList;
import java.util.List;

public class Naviera {
	
	private List<Circuito> circuitos = new ArrayList<>();
	private List<Buque> buques = new ArrayList<>();
	private List<Viaje> viajes = new ArrayList<>();
	
	public void agregarBuque(Buque buque) {
		this.getBuques().add(buque);
	}
	
	public List<Buque> getBuques(){
		return this.buques;
	}
	
	public void agregarViaje(Viaje viaje) {
		this.getViajes().add(viaje);
	}
	
	public List<Viaje> getViajes(){
		return this.viajes;
	}
	
	public void agregarCircuito(Circuito circuito) {
		this.getCircuitos().add(circuito);
	}
	
	public List<Circuito> getCircuitos(){
		return this.circuitos;
	}
	
	public List<Viaje> buscarViajes(Filtro busqueda) {
		return busqueda.filtrar(this.getViajes());
	}
	

}
