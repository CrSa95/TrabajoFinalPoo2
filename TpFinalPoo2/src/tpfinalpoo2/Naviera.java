package tpfinalpoo2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Naviera {

	private List<Circuito> circuitos = new ArrayList<>();
	private List<Buque> buques = new ArrayList<>();
	private List<Viaje> viajes = new ArrayList<>();

	public void agregarBuque(Buque buque) {
		this.getBuques().add(buque);
	}

	public List<Buque> getBuques() {
		return this.buques;
	}

	public void agregarViaje(Viaje viaje) {
		this.getViajes().add(viaje);
	}

	public List<Viaje> getViajes() {
		return this.viajes;
	}

	public void agregarCircuito(Circuito circuito) {
		this.getCircuitos().add(circuito);
	}

	public List<Circuito> getCircuitos() {
		return this.circuitos;
	}

	public List<Viaje> buscarViajesQueCumplan(List<Filtro> filtros) {
		return filtros.stream().flatMap(filtro -> filtro.filtrar(this.getViajes()).stream()).distinct() // elimina
																										// viajes
																										// duplicados
				.collect(Collectors.toList());
	}
}
