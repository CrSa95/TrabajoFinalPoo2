package tpfinalpoo2;

public class Working extends EstadoGPS{

	@Override
	protected EstadoGPS siguienteEstado(Buque buque) {
		return new Departing();
	}

}
