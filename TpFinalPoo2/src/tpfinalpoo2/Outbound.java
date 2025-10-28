package tpfinalpoo2;

public class Outbound extends EstadoGPS {

	@Override
	protected EstadoGPS siguienteEstado(Buque buque) {
		if(buque.distanciaAdestino() > 0) return this;
		
		return new Arrived();
	}

}
