package tpfinalpoo2;

import java.time.LocalDate;
import java.util.ArrayList;

public class Factura {
	private ArrayList<Servicio> servicios;
	private Orden orden;
	public Factura(LocalDate fecha_facturacion, ArrayList<Servicio> servicios, double costo_recorrido, Orden orden) {
		this.servicios = servicios;
		this.orden = orden;
	}

	public Double costoTotal() {
		return 50d + this.costoDeServicios();
	}
	
	private Double costoDeServicios() {
		return this.servicios.stream().mapToDouble(servicio -> servicio.costo(orden)).sum();
}

}
