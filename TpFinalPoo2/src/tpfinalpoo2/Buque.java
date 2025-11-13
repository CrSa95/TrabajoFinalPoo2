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

	public void asignar(Viaje viaje) {
		this.viaje_asignado = viaje;
		this.estado_gps = new Outbound();
		this.tramo_actual = this.viaje_asignado.tramoInicial();
	}

	public void avanzar() {
		this.estado_gps.avanzar(this);
	}

	public void avisarLlegada() {
		this.estado_gps.avisarLlegada(this);
	}

	public void avisarPartida() {
		this.estado_gps.avisarPartida(this);
	}

	protected void cambiarEstado(EstadoGPS estado) {
		this.estado_gps = estado;
	}

	public Double distanciaHaciaDestino() {
		return this.tramo_actual.distanciaHacia(coordenadas);
	}

	public Double distanciaHaciaOrigen() {
		return this.coordenadas.distanciaHacia(this.terminalOrigen().coordenadas());
	}

	public void empezarTrabajo() {
		this.estado_gps.empezarTrabajo(this);
	}

	@Override
	public String generar(Reporte reporte) {
		return reporte.emitir(this);
	}

	public void iniciarViaje() {
		this.coordenadas = tramo_actual.getTerminalOrigen().coordenadas();
		this.estado_gps.avisarPartida(this);
	}

	public String nombre() {
		return this.nombre;
	}

	public void permitirSalida() {
		this.estado_gps.permitirSalida(this);
	}

	public LocalDateTime proximaFecha(Terminal origen, Terminal destino) {
		try {
			if (!viajeAsignado()) {
				return LocalDateTime.MAX;
			}

			return this.viaje_asignado.proximaFecha(origen, destino);
		} catch (IllegalArgumentException e) {
			return LocalDateTime.MAX;
		}

	}

	private boolean viajeAsignado() {
		return viaje_asignado != null;
	}

	public void salir() {
		this.estado_gps.salir(this);
	}

	public void siguienteDestino() {
		this.tramo_actual = this.viaje_asignado.siguienteTramo(tramo_actual);
	}

	public Terminal terminalDestino() {
		return this.tramo_actual.getTerminalDestino();
	}

	public Terminal terminalOrigen() {
		return this.tramo_actual.getTerminalOrigen();
	}

	public Viaje viaje() {
		return this.viaje_asignado;
	}
}
