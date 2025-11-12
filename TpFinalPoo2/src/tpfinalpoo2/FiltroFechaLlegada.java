package tpfinalpoo2;

import java.time.LocalDateTime;
import java.util.List;

public class FiltroFechaLlegada implements Filtro {

	private LocalDateTime fechaLlegada;

	public FiltroFechaLlegada(LocalDateTime fechaLlegada) {
		this.fechaLlegada = fechaLlegada;
	}

	@Override
	public List<Viaje> filtrar(List<Viaje> viajes) {
		return viajes.stream().filter(viaje -> viaje.getFechaLlegada().isEqual(fechaLlegada)).toList();
	}
}
