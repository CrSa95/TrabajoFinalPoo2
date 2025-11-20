package tpfinalpoo2;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Orden {

	private Terminal origen;
	private Terminal destino;
	private Viaje viaje_seleccionado;
	private Set<Servicio> servicios_contratados;
	private Container carga;
	private Camion camion;
	private Chofer chofer;
	private Cliente cliente;

	public Orden(Cliente cliente, Camion camion, Chofer chofer, Container carga, Viaje viaje_seleccionado) {
		this.viaje_seleccionado = viaje_seleccionado;
		this.carga = carga;
		this.camion = camion;
		this.chofer = chofer;
		this.cliente = cliente;
		this.servicios_contratados = new HashSet<>();
	}

	public void agregar(Servicio servicio) {
		this.servicios_contratados.add(servicio);
	}

	public Container carga() {
		return this.carga;
	}

	public double costoEnServicios() {
		return this.servicios_contratados.stream().mapToDouble(servicio -> servicio.costo(this)).sum();
	}

	public Double costoRecorrido() {
		return this.viaje().costoEntre(origen, destino);
	}

	public boolean esExportacion(Terminal terminal) {
		return this.origen == terminal;
	}

	public boolean esImportacion(Terminal terminal) {
		return this.destino == terminal;
	}

	public void facturar(Terminal terminal, Buque buque) {
		if (this.mismoViaje(buque)) {
			if (esExportacion(terminal)) {
				this.cliente.enviar(new FacturaShipper(this));
				return;
			} else {
				this.cliente.enviar(new FacturaConsignee(this));
				return;
			}
		}
		
		throw new IllegalArgumentException("El buque realiza un viaje distinto al de la Órden");
	}

	public LocalDateTime fechaLlegada() {
		return this.viaje().fechaLlegada(destino);
	}

	public LocalDateTime fechaRetiro() {
		return this.fechaLlegada().plusDays(1L).withHour(8);
	}

	public LocalDateTime fechaSalida() {
		return this.viaje_seleccionado.getFechaSalida();
	}

	private boolean mismoViaje(Buque buque) {
		return buque.viaje().equals(viaje_seleccionado);
	}

	public void notificarLlegada(Terminal terminal, Buque buque) {
		if (this.mismoViaje(buque) && this.esImportacion(terminal)) {
			this.cliente.notificarLlegada(buque);
		}
	}

	public void notificarPartida(Terminal terminal, Buque buque) {

		if (this.mismoViaje(buque) && this.esExportacion(terminal)) {
			this.cliente.notificarPartida(buque);
		}
	}

	public void terminalDestino(Terminal terminal) {
		this.destino = terminal;
	}

	public void terminalOrigen(Terminal terminal) {
		this.origen = terminal;
	}

	public void verificar(Camion camion, Chofer chofer, Container container) {
		if (this.verificarCarga(container)) {
			this.verificarCamion(camion);
			this.verificarChofer(chofer);
		}
	}

	private void verificarCamion(Camion camion) {
		if (!this.camion.patente().equals(camion.patente())) {
			throw new RuntimeException("Camión no autorizado");
		}
	}

	private boolean verificarCarga(Container container) {
		return this.carga.id().equals(container.id());
	}

	private void verificarChofer(Chofer chofer) {
		if (!this.chofer.dni().equals(chofer.dni())) {
			throw new RuntimeException("Chofer no autorizado");
		}
	}

	public Viaje viaje() {
		return this.viaje_seleccionado;
	}
}

