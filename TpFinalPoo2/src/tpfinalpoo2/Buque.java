package tpfinalpoo2;

public class Buque {
	private EstadoGPS estado_gps;
	private Viaje viaje_asignado;
	public Buque() {
		this.estado_gps = new Outbound();
	}
	public void asignar(Viaje viaje) {
		this.viaje_asignado = viaje;
	}

	public void actualizarGPS() {
		this.estado_gps = this.estado_gps.actualizarGPS(this);
	}

	public void avisarLlegada() {
		this.estado_gps.avisarLlegada(this);
	}
	public Double distanciaHaciaDestino() {
		return this.viaje_asignado.distanciaHaciaDestino();
	}
	public void empezarTrabajo() {
		this.estado_gps.empezarTrabajo(this);
	}

	
	public Terminal destinoActual() {
		return this.viaje_asignado.terminalDestino();
	}
	public void permitirSalida() {
		// TODO Auto-generated method stub
		this.estado_gps.permitirSalida(this);
	}
}
