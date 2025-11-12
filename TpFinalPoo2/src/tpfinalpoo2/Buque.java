package tpfinalpoo2;

import java.time.LocalDateTime;

public class Buque implements Reportable {
	private EstadoGPS estado_gps;
	private Viaje viaje_asignado;
	private Tramo tramo_actual;
	private Coordenadas coordenadas;
	private String nombre;
	
	public Buque(String nombre) {
		this.nombre = nombre;
	}
	
	public String nombre() {
		return this.nombre;
	}
	
	public void asignar(Viaje viaje) {
		this.viaje_asignado = viaje;
		this.estado_gps = new Outbound();
		this.tramo_actual = this.viaje_asignado.tramoInicial();
	}
	
	public void iniciarViaje() {
		this.coordenadas = tramo_actual.getTerminalOrigen().coordenadas();
		this.estado_gps.avisarPartida(this);
	}

	
	protected void cambiarEstado(EstadoGPS estado) {
		this.estado_gps = estado;
	}
	
	public Double distanciaHaciaDestino() {
		return this.tramo_actual.distanciaHacia(coordenadas);
	}

	public void empezarTrabajo() {
		this.estado_gps.empezarTrabajo(this);
	}

	public void permitirSalida() {
		this.estado_gps.permitirSalida(this);
	}

	public void siguienteDestino() {
		this.tramo_actual = this.viaje_asignado.siguienteTramo(tramo_actual);
	}

	public void salir() {
		this.estado_gps.salir(this);
	}

	public Viaje viaje() {
		return this.viaje_asignado;
	}

	public void avisarLlegada() {
		this.estado_gps.avisarLlegada(this);
	}

	public void avisarPartida() {
		this.estado_gps.avisarPartida(this);
	}
	
	public Double distanciaHaciaOrigen() {
		return this.coordenadas.distanciaHacia(this.terminalOrigen().coordenadas());
	}
	
	public Terminal terminalOrigen() {
		return this.tramo_actual.getTerminalOrigen();
	}
	
	public Terminal terminalDestino() {
		return this.tramo_actual.getTerminalDestino();
	}

	public void avanzar() {
		this.estado_gps.avanzar(this);
	}
	
	@Override
	public String generar(Reporte reporte) {
		return reporte.emitir(this);
	}
}
