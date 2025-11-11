package tpfinalpoo2;

import java.time.LocalDate;
import java.util.ArrayList;

public class Factura {
	private Orden orden;
	private Double costo_recorrido;
	private Double costo_servicios;
	public Factura(Orden orden) {
		this.costo_recorrido = orden.costoRecorrido();
		this.costo_servicios = orden.costoEnServicios();
	}
	
	public Double costoTotal() {
		return this.costo_recorrido + this.costo_servicios;
	}

}
