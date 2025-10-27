package tpfinalpoo2;

public class Pesado extends Servicio{

	public Pesado(double precio_fijo) {
		super(precio_fijo);
	}

	@Override
	public double costo(Container container) {
		return this.precio_fijo;
	}

}
