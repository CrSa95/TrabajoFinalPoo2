package tpfinalpoo2;

public class Outbound extends EstadoGPS {

	@Override
	public EstadoGPS actualizarGPS(Buque buque) {
		if(buque.distanciaHaciaDestino() < 50d) {
			return new Inbound();
		}
		
		return this;
	}
}
