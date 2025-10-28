package tpfinalpoo2;

public class Working extends EstadoGPS{

	@Override
	protected EstadoGPS siguienteEstado(Buque buque) {
		if(buque.trabajando()) return this;
		
		
		return new Departing();
	}

}
