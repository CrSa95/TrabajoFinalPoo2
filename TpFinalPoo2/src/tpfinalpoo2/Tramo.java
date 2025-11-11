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

	public boolean tieneDeOrigenA(Terminal terminalOrigen) {
		return this.getTerminalOrigen().getNombre().equals(terminalOrigen.getNombre());
	}

	public boolean tieneDeDestinoA(Terminal terminalDestino) {
		return this.getTerminalDestino().getNombre().equals(terminalDestino.getNombre());
	}

	public Coordenadas getTerminalDestinoCoordenadas() {
		return this.destino.coordenadas();
	}

	public Double distanciaHacia(Coordenadas coordenadas) {
		return this.getTerminalDestino().coordenadas().distanciaHacia(coordenadas);
	}

}
