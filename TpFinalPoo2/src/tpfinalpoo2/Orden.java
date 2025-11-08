package tpfinalpoo2;

import java.time.LocalDateTime;
import java.util.Set;

public class Orden {
	

	private LocalDateTime fecha_llegada;
	private LocalDateTime fecha_salida;
	private Terminal origen;
	private Terminal destino;
	private Viaje viaje_seleccionado;
	private Set<Servicio> servicios_contratados;
	private Container carga;
	private Camion camion;
	private Chofer chofer;
	private Cliente dueno;
	
	public Orden(LocalDateTime fecha_llegada, LocalDateTime fecha_salida, Terminal origen, Terminal destino,
			Viaje viaje_seleccionado, Set<Servicio> servicios_contratados, Container carga, Camion camion,
			Chofer chofer, Cliente dueno) {
		this.fecha_llegada = fecha_llegada;
		this.fecha_salida = fecha_salida;
		this.origen = origen;
		this.destino = destino;
		this.viaje_seleccionado = viaje_seleccionado;
		this.servicios_contratados = servicios_contratados;
		this.carga = carga;
		this.camion = camion;
		this.chofer = chofer;
		this.dueno = dueno;
	}
	
	
	public boolean verificar(Camion camionAVerificar, Chofer choferAVerificar, Container cargaAVerificar) {
		return this.verificarCamion(camionAVerificar) && this.verificarChofer(choferAVerificar) && this.verificarCarga(cargaAVerificar);
	}
	
	public double costoEnServicios() {
		return this.servicios_contratados
		        .stream()
		        .mapToDouble(servicio -> servicio.costo(this.carga))
		        .sum();
	}
	
	public LocalDateTime fechaSalida() {
		return this.fecha_salida;
	}
	
	public LocalDateTime fechaLlegada() {
		return this.fecha_llegada;
	}
	
	public Viaje viaje() {
    return this.viaje_seleccionado.equals(viaje);
	}
	
	private boolean verificarCamion(Camion camionAVerificar) {
	    return this.camion.patente().equals(camionAVerificar.patente());
	}

	private boolean verificarChofer(Chofer choferAVerificar) {
	    return this.chofer.dni().equals(choferAVerificar.dni());
	}

	private boolean verificarCarga(Container cargaAVerificar) {
		return true; // TO DO: Forma de identificar container
	}

	public Container carga() {
		return this.carga;
	}

	public boolean perteneceAlContainer(Container container) {
		return this.carga.isEqual(container);
	}

	public void notificarLlegada(Buque buque) {
		if (this.mismoViaje(buque.viaje())) {
			this.dueno.notificar();
		}
	}

	private boolean mismoViaje(Viaje viaje) {
		return this.viaje_seleccionado.equals(viaje);
	}

}
