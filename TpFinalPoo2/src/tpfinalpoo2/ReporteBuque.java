package tpfinalpoo2;

public class ReporteBuque implements Reporte {
	TerminalGestionada terminal;

	public ReporteBuque(TerminalGestionada terminal) {
		this.terminal = terminal;
	}

	@Override
	public String emitir(Buque buque) {
		ConstructorHtml html = new ConstructorHtml();
		Viaje viaje = buque.viaje();

		html.abrir("reporte").abrir("importaciones");

		terminal.cargasImportadasEnViaje(viaje).stream().forEach(carga -> html.elemento("item", carga.id()));

		html.cerrar("importaciones").abrir("exportaciones");

		terminal.cargasExportadasEnViaje(viaje).stream().forEach(carga -> html.elemento("item", carga.id()));

		html.cerrar("exportaciones").cerrar("reporte");

		return html.construir();
	}

}
