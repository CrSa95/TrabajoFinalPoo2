package tpfinalpoo2;

import java.time.LocalDate;

public class Viaje {
	
	private LocalDate fechaLlegada;
	private LocalDate fechaSalida;
	private Terminal terminalDestino;
	private Circuito circuito;
	
	public Viaje(Circuito circuito) {
		this.circuito = circuito;
	}

	public LocalDate getFechaLlegada() {
		// TODO Auto-generated method stub
		return this.fechaLlegada;
	}

	public LocalDate getFechaSalida() {
		// TODO Auto-generated method stub
		return this.fechaSalida;
	}

	public Terminal getTerminalDestino() {
		// TODO Auto-generated method stub
		return this.terminalDestino;
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
		return 0d;
	}

}
