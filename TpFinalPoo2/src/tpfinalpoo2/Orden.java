package tpfinalpoo2;

import java.time.LocalDateTime;

public class Orden {
	private Container carga;
	private Chofer chofer;
	private Camion camion;
	private Cliente cliente;

	public Orden(Container container, Camion camion, Chofer chofer, Cliente cliente) {
		this.carga = container;
		this.camion = camion;
		this.chofer = chofer;
		this.cliente = cliente;
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
			this.cliente.notificar();
		}
	}

	private boolean mismoViaje(Viaje viaje) {
		return viaje.equals(viaje);
	}

}
