package tpfinalpoo2;

public class Inbound extends EstadoGPS {
	@Override
	public EstadoGPS actualizarGPS(Buque buque) {
		if (buque.distanciaHaciaDestino() <= 0d) {
			return new Arrived();
		}

		return this;
	}

	@Override
	public void avisarLlegada(Buque buque) {
		buque.avisarLlegada();
	}
}
