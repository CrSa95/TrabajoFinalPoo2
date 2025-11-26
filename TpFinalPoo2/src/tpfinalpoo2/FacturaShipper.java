package tpfinalpoo2;

import java.time.LocalDate;

public class FacturaShipper extends Factura {

	public FacturaShipper(Orden orden) {
		super(orden);
	}

	public FacturaShipper(Orden orden, LocalDate fecha) {
		super(orden, fecha);
	}

	@Override
	public Double costoTotal() {
		return this.orden().costoEnServicios();
	}

}
