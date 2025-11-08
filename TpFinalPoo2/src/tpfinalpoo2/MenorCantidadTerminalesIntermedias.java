package tpfinalpoo2;

import java.util.Comparator;
import java.util.List;

public class MenorCantidadTerminalesIntermedias implements IBusquedaCircuito {

	@Override
	public Circuito seleccionarMejor(List<Naviera> navieras, Terminal terminalOrigen, Terminal terminalDestino) {

		return navieras.stream().flatMap(naviera -> naviera.getCircuitos().stream()) // todos los circuitos
				.min(Comparator
						.comparingDouble(c -> c.terminalesIntermediasDesdeHasta(terminalOrigen, terminalDestino))) // el
																													// de
																													// menor
																													// tiempo
				.orElseThrow(() -> new IllegalArgumentException("Circuito no encontrado")); // si no hay ninguno válido
	}

}