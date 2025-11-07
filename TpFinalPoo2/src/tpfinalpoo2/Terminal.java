package tpfinalpoo2;

import java.util.ArrayList;
import java.util.List;

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
	
	public Circuito buscarMejorCircuitoParaLLegarA(Terminal terminalDestino) {

		return this.getEstrategiaDeBusqueda().seleccionarMejor(this.getNavieras(), this, terminalDestino);
	}
	
	//Considerar los Circuitos que tengan como terminal de origen la terminal gestionada
	//en alguna parte del Circuito 
	//Y calcular desde ahi hasta la terminal destino 
	//[(A->B),(B->C),(C->D),(E->A)] Terminal gestionada es B terminal destino A
	
}
