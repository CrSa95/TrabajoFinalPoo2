package tpfinalpoo2;

import java.util.List;

public interface IStrategyBusquedaCircuito {
	
	public Circuito seleccionarMejor(List<Circuito> circuitos, Terminal terminalDestino); 
}
