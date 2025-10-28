package tpfinalpoo2;

public class Inbound extends EstadoGPS{

	@Override
	protected EstadoGPS siguienteEstado(Buque buque) {
		// TODO Auto-generated method stub
		if(buque.distanciaAdestino() < 50) {
			return new Outbound();
		}
		
		return this;
	}

}
