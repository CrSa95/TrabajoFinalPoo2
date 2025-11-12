package tpfinalpoo2;

public class ReporteAduana implements Reporte {
	TerminalGestionada terminal;
	
	public ReporteAduana(TerminalGestionada terminal) {
		this.terminal = terminal;
	}

	@Override
	public String emitir(Buque buque) {
		ConstructorHtml html = new ConstructorHtml();
		Viaje viaje = buque.viaje();
		
		html.abrir("html")
				.abrir("body")
					.elemento("p", "Nombre del buque: " + buque.nombre())
					.elemento("p", "Fecha de arribo: " + viaje.fechaLlegada(terminal))
					.elemento("p", "Fecha de partida: " + viaje.getFechaSalida())
					.abrir("ul");
					
		terminal.cargasEnViaje(viaje).stream().forEach(
				carga -> html.elemento("li", carga.tipo() + " | " + carga.id())
		);
		
		html.cerrar("ul").cerrar("body").cerrar("html");
		
		return html.construir();
	}

}
