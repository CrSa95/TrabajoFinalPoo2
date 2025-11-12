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
		this.origen = null;
		this.destino = null;
		this.viaje_seleccionado = viaje_seleccionado;
		this.carga = carga;
		this.camion = camion;
		this.chofer = chofer;
		this.cliente = cliente;
		this.servicios_contratados = new HashSet<>();
	}
	
	public void terminalOrigen(Terminal terminal) {
		this.origen = terminal;
	}
	
	public void terminalDestino(Terminal terminal) {
		this.destino = terminal;
	}
	
	
	public void verificar(Camion camion, Chofer chofer, Container container) {
		this.verificarCamion(camion);
		this.verificarChofer(chofer);
		this.verificarCarga(container);

	}

	private void verificarChofer(Chofer chofer) {
		if (!this.chofer.dni().equals(chofer.dni())) {
			throw new RuntimeException("Chofer no autorizado");
		}
	}

	private void verificarCamion(Camion camion) {
		if (!this.camion.patente().equals(camion.patente())) {
			throw new RuntimeException("Camión no autorizado");
		}
	}
	
	private void verificarCarga(Container container) {
		if (!this.carga.id().equals(container.id())) {
			throw new RuntimeException("Carga no autorizada");
		}
	}
	
	public double costoEnServicios() {
		return this.servicios_contratados
		        .stream()
		        .mapToDouble(servicio -> servicio.costo(this))
		        .sum();
	}
	
	public Double costoRecorrido() {
		return this.viaje_seleccionado.costo();
	}
	
	public void agregar(Servicio servicio) {
		this.servicios_contratados.add(servicio);
	}
	
	public LocalDateTime fechaLlegada() {
		return this.viaje_seleccionado.fechaLlegada(destino);
	}
	
	public LocalDateTime fechaSalida() {
		return this.viaje_seleccionado.getFechaSalida();
	}
	
	public LocalDateTime fechaRetiro() {
		return this.fechaLlegada().plusDays(1L).withHour(8);
	}
	
	public Viaje viaje() {
		return this.viaje_seleccionado;
	}

	public Container carga() {
		return this.carga;
	}
	
	public void notificarPartida(Buque buque) {
		if(this.origen != null) this.cliente.notificarPartida(buque);
	}

	public void notificarLlegada(Buque buque) {
		if(this.destino != null) this.cliente.notificarLlegada(buque);
	}

}
