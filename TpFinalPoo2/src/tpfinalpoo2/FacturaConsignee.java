package tpfinalpoo2;

public class FacturaConsignee extends Factura {

	public FacturaConsignee(Orden orden) {
		super(orden);
	}

	@Override
	public Double costoTotal() {
		return orden.costoRecorrido() + orden.costoEnServicios();
	}

}
