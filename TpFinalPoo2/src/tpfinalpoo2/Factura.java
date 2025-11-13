package tpfinalpoo2;

import java.time.LocalDate;

public abstract class Factura {
	protected Orden orden;
	protected LocalDate fecha_factura;
	public Factura(Orden orden) {
		fecha_factura = LocalDate.now();
		this.orden = orden;
	}
	
	public Factura(Orden orden, LocalDate fecha) {
		fecha_factura = fecha;
		this.orden = orden;
	}
	
	abstract public Double costoTotal();
	
	public Orden orden() { return this.orden; }
}
