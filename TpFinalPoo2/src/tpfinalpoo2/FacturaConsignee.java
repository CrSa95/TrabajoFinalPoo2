package tpfinalpoo2;

import java.time.LocalDate;

public class FacturaConsignee extends Factura {

	public FacturaConsignee(Orden orden) {
		super(orden);
	}

	public FacturaConsignee(Orden orden, LocalDate fecha) {
		super(orden,fecha);
	}

	@Override
	public Double costoTotal() {
		return this.orden().costoRecorrido() + this.orden().costoEnServicios();
	}

}
