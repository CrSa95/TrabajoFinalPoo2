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
		
		//En primer instancia se filtran todos los circuitos de todas las navieras 
		//siempre y cuando el circuito parta desde la terminal gestionada
		//ya que en Navieras podriamos llegar a tener circuitos que no partan desde 
		//la terminal gestionada
		List<Circuito> todosLosCircuitos = this.getNavieras().stream()
															 .map(Naviera::getCircuitos)
															 .flatMap(List::stream)
															 .filter(circuito -> circuito.parteDesde(this))
															 .toList(); 
		
		//Una vez que se obtiene la lista se busca el mejor circuito dependiendo 
		//la estrategia seteada con anterioridad 
		return this.getEstrategiaDeBusqueda().seleccionarMejor(todosLosCircuitos, terminalDestino);
	}
}
