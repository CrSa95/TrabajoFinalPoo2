package tpfinalpoo2;

import java.util.List;

public interface IBusquedaCircuito {
	
	public Circuito seleccionarMejor(List<Naviera> navieras, Terminal terminalOrigen, Terminal terminalDestino); 
}
