package tpfinalpoo2;

import java.util.List;

public class FiltroPuertoDestino implements Filtro {
	
	private Terminal destino;
	
	public FiltroPuertoDestino(Terminal destino) {
        this.destino = destino;
    }

    @Override
    public List<Viaje> filtrar(List<Viaje> viajes) {
        return viajes.stream()
                .filter(viaje -> viaje.getTerminalDestino().getNombre() == destino.getNombre())
                .toList();
    }
}
