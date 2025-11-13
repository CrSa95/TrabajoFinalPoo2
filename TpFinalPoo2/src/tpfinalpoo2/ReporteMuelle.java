package tpfinalpoo2;

public class ReporteMuelle implements Reporte {

	TerminalGestionada terminal;

	public ReporteMuelle(TerminalGestionada terminal) {
		this.terminal = terminal;
	}

	@Override
	public String emitir(Buque buque) {
		Viaje viaje = buque.viaje();

		return String.format("""
				Nombre del buque: %s
				Fecha de arribo: %s
				Fecha de partida: %s
				Cantidad de contenedores operados: %d
				""", buque.nombre(), viaje.fechaLlegada(terminal), viaje.getFechaSalida(),
				terminal.cantCargasEnViaje(viaje));
	}

}
