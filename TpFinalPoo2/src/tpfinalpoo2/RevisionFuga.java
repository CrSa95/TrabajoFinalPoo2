package tpfinalpoo2;

public class RevisionFuga extends Servicio {

	public RevisionFuga(double precio_fijo) {
		super(precio_fijo);
	}

	@Override
	public double costo(Orden orden) {
		return this.precio_fijo;
	}

}
