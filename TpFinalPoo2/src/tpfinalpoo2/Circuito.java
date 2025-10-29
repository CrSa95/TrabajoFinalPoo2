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
	
	//Su funcion es realizar una lista de sub tramos hasta la terminal destino indicada
	//esto se debe a que una lista de tramos en un circuito puede tener nuestra terminal 
	//destino en medio de la lista, es decir, siendo la lista de tramos
	//[(A->B),(B->C),(C->A)] si nuestra terminal destino es C no tenemos porque recorrer
	//el circuito completo 
	public List<Tramo> subTramosHasta(Terminal terminalDestino){
		
		List<Tramo> listaFiltrada = new ArrayList<>();

	    for (Tramo tramo : this.getTramos()) {
	    	listaFiltrada.add(tramo);
	        if (tramo.getTerminalDestino().getNombre().equals(terminalDestino.getNombre())) {
	            return listaFiltrada; // encontramos el destino
	        }
	    }

	    // Si no se encontró el destino, devolvemos una lista vacia
	    listaFiltrada.clear();
	    return listaFiltrada;
	}
	
	//Su funcion es calcular el costo total hasta una determinada terminal destino
	public double costoTotalHasta(Terminal terminalDestino) {
		return this.subTramosHasta(terminalDestino).stream().mapToDouble(Tramo::getCosto).sum();
	}
	
	//Su funcion es calcular el tiempo total hasta una determinada terminal destino
	public double tiempoTotalHasta(Terminal terminalDestino) {
		return this.subTramosHasta(terminalDestino).stream().mapToDouble(Tramo::getDuracion).sum();
	}
	
	//Su funcion es informar si el circuito parte desde la terminal indicada por parametro
	//esto nos sirve para verificar si el circuito parte desde nuestra terminal gestionada
	public boolean parteDesde(Terminal terminal) {
	    return !tramos.isEmpty() && tramos.get(0).getTerminalOrigen().getNombre().equals(terminal.getNombre());
	}
	
}
