package tpfinalpoo2;

import java.util.ArrayList;
import java.util.List;

public class Terminal {

	private String nombre;
	private IStrategyBusquedaCircuito estrategiaBusquedaMejorCircuito;
	private List<Naviera> navieras = new ArrayList<>();
	
	public Terminal(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void agregarNaviera(Naviera naviera) {
		this.getNavieras().add(naviera);
	}
	
	public List<Naviera> getNavieras(){
		return this.navieras;
	}
	
	public void setEstrategiaDeBusqueda(IStrategyBusquedaCircuito strategyCircuitos) {
		this.estrategiaBusquedaMejorCircuito = strategyCircuitos;
	}
	
	public IStrategyBusquedaCircuito getEstrategiaDeBusqueda() {
		return this.estrategiaBusquedaMejorCircuito;
	}
	
	public Circuito buscarMejorCircuitoParaLLegarA(Terminal terminalDestino) {

		return this.getEstrategiaDeBusqueda().seleccionarMejor(this.filtrarCircuitosDeEstaTerminal(), terminalDestino);
	}
	
	public List <Circuito> filtrarCircuitosDeEstaTerminal(){
		return this.getNavieras().stream()
				 .map(Naviera::getCircuitos)
				 .flatMap(List::stream)
				 .filter(circuito -> circuito.parteDesde(this))
				 .toList();
	}
}
