package tpfinalpoo2;

import java.time.LocalDateTime;
import java.util.List;

public class FiltroFechaSalida implements Filtro {

	private LocalDateTime fechaSalida;

	public FiltroFechaSalida(LocalDateTime fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	@Override
	public List<Viaje> filtrar(List<Viaje> viajes) {
		return viajes.stream().filter(viaje -> viaje.getFechaSalida().isEqual(fechaSalida)).toList();
	}
}
