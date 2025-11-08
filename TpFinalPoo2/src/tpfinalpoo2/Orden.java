package tpfinalpoo2;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Orden {
	private Container carga;
	private Chofer chofer;
	private Camion camion;
	public Orden(Container container, Camion camion, Chofer chofer) {
		this.carga = container;
		this.camion = camion;
		this.chofer = chofer;
	}

	public Container carga() {
		// TODO Auto-generated method stub
		return null;
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
		if(this.carga.isEqual(container)) {
			this.verificarCamion(camion);
			this.verificarChofer(chofer);
		}

	}
	
	private void verificarChofer(Chofer chofer) {
		if(!this.chofer.dni().equals(chofer.dni())) throw new RuntimeException();
	}
	private void verificarCamion(Camion camion) {
		if(!this.camion.patente().equals(camion.patente())) throw new RuntimeException();
	}

}
