package tpfinalpoo2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OR implements Filtro {

	private List<Filtro> filtros = new ArrayList<>();

	public void agregarFiltro(Filtro filtro) {
		this.filtros.add(filtro);
	}

	@Override
	public List<Viaje> filtrar(List<Viaje> viajes) {
		return this.getFiltros().stream().flatMap(f -> f.filtrar(viajes).stream()).distinct()
				.collect(Collectors.toList());
	}

	public List<Filtro> getFiltros() {
		return this.filtros;
	}
}
