package tpfinalpoo2;

import java.time.LocalDate;
import java.util.List;

public class FiltroFechaLlegada implements Filtro {
	
	private LocalDate fechaLlegada;
	
	public FiltroFechaLlegada(LocalDate fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    @Override
    public List<Viaje> filtrar(List<Viaje> viajes) {
        return viajes.stream()
                .filter(viaje -> viaje.getFechaLlegada().isEqual(fechaLlegada))
                .toList();
    }
}
