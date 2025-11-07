package tpfinalpoo2;

public class Lavado extends Servicio{
	
	public Lavado(double precio_fijo) {
		super(precio_fijo);
	}

	@Override
	public double costo(Orden orden) {
		if(orden.carga().metrosCubicos() > 70) {
			return precio_fijo + 20;
		}
		
		return precio_fijo;
	}

	
	
	

}
