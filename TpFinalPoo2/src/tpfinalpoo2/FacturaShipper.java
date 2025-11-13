package tpfinalpoo2;

public class FacturaShipper extends Factura {

	public FacturaShipper(Orden orden) {
		super(orden);
	}

	@Override
	public Double costoTotal() {
		return orden.costoEnServicios();
	}

}
