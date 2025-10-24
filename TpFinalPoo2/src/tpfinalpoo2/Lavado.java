package tpfinalpoo2;

public class Lavado extends Servicio{
	
	public Lavado(double precio_fijo) {
		super(precio_fijo);
	}

	@Override
	public boolean condicion(Container container) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double adicional() {
		// TODO Auto-generated method stub
		return 0;
	}

}
