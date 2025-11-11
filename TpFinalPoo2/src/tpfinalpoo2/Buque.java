package tpfinalpoo2;

public class Buque {
	private EstadoGPS estado_gps;
	private Viaje viaje_asignado;
	private Tramo tramo_actual;
	private Coordenadas coordenadas;

	public Buque() {
		this.estado_gps = new Outbound();
	}

	public void asignar(Viaje viaje) {
		this.viaje_asignado = viaje;
		this.tramo_actual = viaje.tramoInicial();
	}

	public void actualizarGPS() {
		this.estado_gps = this.estado_gps.actualizarGPS(this);
	}

	public void avisarLlegada() {
		this.estado_gps.avisarLlegada(this);
	}

	public Double distanciaHaciaDestino() {
		return this.tramo_actual.distanciaHacia(coordenadas);
	}

	public void empezarTrabajo() {
		this.estado_gps.empezarTrabajo(this);
		this.estado_gps = this.estado_gps.actualizarGPS(this);
	}

	public Terminal destinoActual() {
		return this.viaje_asignado.getTerminalDestino();
	}

	public void permitirSalida() {
		this.estado_gps.permitirSalida(this);
		this.estado_gps = this.estado_gps.actualizarGPS(this);

	}

	public void siguienteDestino() {
		this.tramo_actual = this.viaje_asignado.siguienteTramo(tramo_actual);
		this.coordenadas = this.tramo_actual.getTerminalDestinoCoordenadas();
		this.estado_gps = this.estado_gps.actualizarGPS(this);
	}

	public void salir() {
		this.estado_gps.salir(this);
	}

	public Viaje viaje() {
		return this.viaje_asignado;
	}

	public void avisarPartida() {
		this.tramo_actual.getTerminalDestino().avisarPartida(this);
	}
}
