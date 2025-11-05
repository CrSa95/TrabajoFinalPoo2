package tpfinalpoo2;

import java.time.LocalDate;

public class Viaje {
	
	private LocalDate fechaLlegada;
	private LocalDate fechaSalida;
	private OtraTerminal terminalDestino;
	
	public LocalDate getFechaLlegada() {
		// TODO Auto-generated method stub
		return this.fechaLlegada;
	}

	public LocalDate getFechaSalida() {
		// TODO Auto-generated method stub
		return this.fechaSalida;
	}

	public OtraTerminal getTerminalDestino() {
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

}
