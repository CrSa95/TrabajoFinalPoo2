package tpfinalpoo2;

public class Pesado extends Servicio {

	public Pesado(double precio_fijo) {
		super(precio_fijo);
	}

	@Override
	public double costo(Orden orden) {
		return this.precio_fijo;
	}

}
