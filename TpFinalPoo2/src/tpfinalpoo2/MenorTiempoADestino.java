package tpfinalpoo2;

import java.util.List;

public class MenorTiempoADestino implements IBusquedaCircuito {

	@Override
	public Circuito seleccionarMejor(List<Naviera> navieras, Terminal terminalOrigen, Terminal terminalDestino) {

		List<Circuito> todosLosCircuitos = navieras.stream()
				.map(Naviera::getCircuitos)
				.flatMap(List::stream)
				.toList();
		
		Circuito mejorCircuito = null;
		
		double menorTiempo = Double.MAX_VALUE;
		
		for(Circuito circuito : todosLosCircuitos) {
			
			double menorTiempoActual = circuito.tiempoTotalDesdeHasta(terminalOrigen, terminalDestino);

			if(menorTiempoActual < menorTiempo && menorTiempoActual > 0) {
				menorTiempo = menorTiempoActual;
				mejorCircuito = circuito;
			}
		}
		
		return mejorCircuito;
	}
}