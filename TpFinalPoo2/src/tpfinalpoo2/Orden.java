package tpfinalpoo2;

import java.time.LocalDateTime;
import java.util.Set;

public class Orden {
	private Container carga;
	private Chofer chofer;
	private Camion camion;
	private Cliente cliente;
	private Viaje viaje_seleccionado;
	private Set<Servicio> servicios_contratados;
	public Orden(Container container, Camion camion, Chofer chofer, Cliente cliente, Set<Servicio> servicios_contratados, Viaje viaje_seleccionado) {
		this.carga = container;
		this.camion = camion;
		this.chofer = chofer;
		this.cliente = cliente;
		this.servicios_contratados = servicios_contratados;
		this.viaje_seleccionado = viaje_seleccionado;
	}
	
	public double costoEnServicios() {
		return this.servicios_contratados
		        .stream()
		        .mapToDouble(servicio -> servicio.costo(this))
		        .sum();
	}
	public Container carga() {
		return this.carga;
	}

	public LocalDateTime fechaRetiro() {
		return null;
	}
	
	public LocalDateTime fechaIngreso() {
		return null;
	}

	public boolean perteneceAlContainer(Container container) {
		return this.carga.isEqual(container);
	}

	public void verificar(Container container, Camion camion, Chofer chofer) {
		if (this.carga.isEqual(container)) {
			this.verificarCamion(camion);
			this.verificarChofer(chofer);
		}

	}

	private void verificarChofer(Chofer chofer) {
		if (!this.chofer.dni().equals(chofer.dni())) {
			throw new RuntimeException();
		}
	}

	private void verificarCamion(Camion camion) {
		if (!this.camion.patente().equals(camion.patente())) {
			throw new RuntimeException();
		}
	}

	public void notificarLlegada(Buque buque) {
		if (this.mismoViaje(buque.viaje())) {
			this.cliente.notificarLlegada(buque);
		}
	}


	private boolean mismoViaje(Viaje viaje) {
		return viaje.equals(viaje);
	}

	public void notificarPartida(Buque buque) {
		if (this.mismoViaje(buque.viaje())) {
			this.cliente.notificarPartida(buque);
		}
	}

	public Double costoRecorrido() {
		return this.viaje_seleccionado.costo();
	}

}
