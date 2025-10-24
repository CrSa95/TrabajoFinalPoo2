package tpfinalpoo2;

public abstract class Servicio {
	protected double precio_fijo = 0;
	public Servicio(double precio_fijo){
		this.precio_fijo = precio_fijo;
	}
	
	
	public final double costo(Container container) {
		return this.precio_fijo + (this.condicion(container) ? this.adicional() : 0 );  
	}
	
	public abstract boolean condicion(Container container);
	public abstract double adicional();
}
