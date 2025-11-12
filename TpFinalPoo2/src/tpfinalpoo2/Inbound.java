package tpfinalpoo2;

public class Inbound extends EstadoGPS {
	@Override
	public void avisarLlegada(Buque buque) {
		buque.terminalDestino().avisarLlegada(buque);
	}

	@Override
	public void avanzar(Buque buque) {
		if(buque.distanciaHaciaDestino() <= 0d) {
			buque.cambiarEstado(new Arrived());
		}
	}
}
