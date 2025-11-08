package tpfinalpoo2;

import java.time.LocalDate;
import java.util.List;

public class FiltroFechaSalida implements Filtro {

	private LocalDate fechaSalida;

	public FiltroFechaSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	@Override
	public List<Viaje> filtrar(List<Viaje> viajes) {
		return viajes.stream().filter(viaje -> viaje.getFechaSalida().isEqual(fechaSalida)).toList();
	}
}
