package tpfinalpoo2;

import java.util.List;

public class MenorPrecioADestino implements IBusquedaCircuito {

	@Override
	public Circuito seleccionarMejor(List<Naviera> navieras, Terminal terminalOrigen, Terminal terminalDestino) {
		// TODO Auto-generated method stub
		
		List<Circuito> todosLosCircuitos = navieras.stream()
				.map(Naviera::getCircuitos)
				.flatMap(List::stream)
				.toList();

		Circuito mejorCircuito = null;
		
		double menorCosto = Double.MAX_VALUE;

		for(Circuito circuito : todosLosCircuitos) {

			double menorCostoActual = circuito.costoTotalDesdeHasta(terminalOrigen, terminalDestino);

			if(menorCostoActual < menorCosto && menorCostoActual > 0) {
				menorCosto = menorCostoActual;
				mejorCircuito = circuito;
			}
		}
		
		return mejorCircuito;
	}
}