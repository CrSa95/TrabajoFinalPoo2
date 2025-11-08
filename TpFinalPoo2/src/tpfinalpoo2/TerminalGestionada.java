package tpfinalpoo2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TerminalGestionada implements Terminal{

	private String nombre;
	private Coordenadas coordenadas;
	private IBusquedaCircuito estrategiaBusquedaMejorCircuito;
	private List<Naviera> navieras = new ArrayList<>();
	
	public TerminalGestionada(String nombre, Coordenadas coordenadas) {
		this.nombre = nombre;
		this.coordenadas = coordenadas;
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
	
	public void setEstrategiaDeBusqueda(IBusquedaCircuito strategyCircuitos) {
		this.estrategiaBusquedaMejorCircuito = strategyCircuitos;
	}
	
	public IBusquedaCircuito getEstrategiaDeBusqueda() {
		return this.estrategiaBusquedaMejorCircuito;
	}
	
	public List<Viaje> busquedaDeRutasMaritimasQueCumplan(List<Filtro> filtros){
		return this.getNavieras().stream()
	        	  				 .flatMap(naviera -> naviera.buscarViajesQueCumplan(filtros).stream())
	        	  				 .collect(Collectors.toList());
	}
	
	public Circuito buscarMejorCircuitoParaLLegarA(Terminal terminalDestino) {

		return this.getEstrategiaDeBusqueda().seleccionarMejor(this.getNavieras(), this, terminalDestino);
	}

	public Coordenadas coordenadas() {
		return this.coordenadas;
	}
}
