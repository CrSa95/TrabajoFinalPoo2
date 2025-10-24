package tpfinalpoo2;

public abstract class Servicio {
	protected double precio_fijo = 0;
	public Servicio(double precio_fijo){
		this.precio_fijo = precio_fijo;
	}
	
	public abstract double costo(Container container);
}
