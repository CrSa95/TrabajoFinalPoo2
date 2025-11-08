package tpfinalpoo2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Terminal {

	private String nombre;
	private IBusquedaCircuito estrategiaBusquedaMejorCircuito;
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
}
