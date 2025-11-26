package tpfinalpoo2;

public class Desconsolidado extends Servicio {

	public Desconsolidado(double precio_fijo) {
		super(precio_fijo);
	}

	@Override
	public double costo(Orden orden) {
		if (orden.carga().desconsolidable()) {
			return this.precio_fijo;
		}

		return 0;
	}

}
