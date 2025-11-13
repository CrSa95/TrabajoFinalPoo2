package tpfinalpoo2;

import java.time.LocalDateTime;

public class Viaje {
	private LocalDateTime fechaSalida;
	private Circuito circuito;

	public Viaje(Circuito circuito, LocalDateTime fechaSalida) {
		this.circuito = circuito;
		this.fechaSalida = fechaSalida;
	}

	public Double costo() {
		return this.circuito.costoCircuito();
	}

	public Double costoEntre(Terminal origen, Terminal destino) {
		return this.circuito.costoTotalDesdeHasta(origen, destino);
	}

	public LocalDateTime fechaLlegada(Terminal terminal) {
		return this.fechaSalida.plusDays(this.circuito.tiempoHaciaTerminalDesdeOrigen(terminal));
	}

	public LocalDateTime getFechaLlegada() {
		return fechaSalida.plusDays((long) circuito.tiempoTotal());
	}

	public LocalDateTime getFechaSalida() {
		return this.fechaSalida;
	}

	public Terminal getTerminalDestino() {
		return this.circuito.destinoActual();
	}

	public LocalDateTime proximaFecha(Terminal origen, Terminal destino) {
		return this.getFechaSalida().plusDays((long) this.circuito.tiempoTotalDesdeHasta(origen, destino));

	}

	public Tramo siguienteTramo(Tramo tramoActual) {
		return this.circuito.siguienteTramo(tramoActual);
	}

	public Tramo tramoInicial() {
		return this.circuito.tramoInicial();
	}

}
