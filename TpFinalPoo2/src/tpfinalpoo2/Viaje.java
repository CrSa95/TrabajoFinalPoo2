package tpfinalpoo2;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Viaje {
	private LocalDate fechaSalida;
	private Circuito circuito;
	public Viaje(Circuito circuito, LocalDate fechaSalida2) {
		this.circuito = circuito;
		this.fechaSalida = fechaSalida2;
		
	}

	public LocalDate getFechaLlegada() {
		return fechaSalida.plusDays((long) circuito.tiempoTotal());
	}

	public LocalDate getFechaSalida() {
		return this.fechaSalida;
	}

	public Terminal getTerminalDestino() {
		return this.circuito.destinoActual();
	}


	public Double costo() {
		return this.circuito.costoCircuito();
	}

	public LocalDate fechaLlegada(Terminal terminal) {
		return this.fechaSalida.plusDays(
				this.circuito.tiempoHaciaTerminalDesdeOrigen(terminal)
		);
	}

	public Tramo tramoInicial() {
		return this.circuito.tramoInicial();
	}

	public Tramo siguienteTramo(Tramo tramoActual) {
		// TODO Auto-generated method stub
		return this.circuito.siguienteTramo(tramoActual);
	}


}
