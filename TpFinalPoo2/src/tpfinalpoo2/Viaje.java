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
		// TODO Auto-generated method stub
		return this.fechaSalida;
	}

	public Terminal getTerminalDestino() {
		// TODO Auto-generated method stub
		return this.circuito.destinoActual();
	}

	public Terminal terminalDestino() {
		// TODO Auto-generated method stub
		return null;
	}

	public Double distanciaHaciaDestino() {
		// TODO Auto-generated method stub
		return 0d;
	}

	public void siguienteTramo() {

	}

	public Double costo() {
		// TODO Auto-generated method stub
		return this.circuito.costoCircuito();
	}

	public LocalDate fechaLlegada(Terminal terminal) {
		return this.fechaSalida.plusDays(
				this.circuito.tiempoHaciaTerminalDesdeOrigen(terminal)
		);
	}


}
