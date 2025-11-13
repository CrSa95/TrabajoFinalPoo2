package tpfinalpoo2;

public class Desconsolidado extends Servicio {

	public Desconsolidado(double precio_fijo) {
		super(precio_fijo);
	}

	@Override
	public double costo(Orden orden) {
		return this.precio_fijo;
	}

}
