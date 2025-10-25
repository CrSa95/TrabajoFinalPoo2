package tpfinalpoo2;

public class Tramo {
	
	private double costo;
	private double duracion;
	private Terminal origen;
	private Terminal destino;
	
	public Tramo(double duracion, double costo, Terminal origen, Terminal destino) {
		this.costo = costo;
		this.duracion = duracion;
		this.origen = origen;
		this.destino = destino;
	}

	public double getCosto() {
		return this.costo;
	}

	public double getDuracion() {
		return this.duracion;
	}

	public Terminal getTerminalOrigen() {
		return this.origen;
	}

	public Terminal getTerminalDestino() {
		return this.destino;
	}
	
}
