package tpfinalpoo2;

public class Arrived extends EstadoGPS{

	@Override
	protected EstadoGPS siguienteEstado(Buque buque) {
		if(buque.trabajando()) return new Working();
		return this;
	}

}
