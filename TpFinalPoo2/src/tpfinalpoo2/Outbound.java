package tpfinalpoo2;

public class Outbound extends EstadoGPS {	
	@Override
	public void avisarPartida(Buque buque) {
		buque.terminalOrigen().avisarPartida(buque);
	}

	@Override
	public void avanzar(Buque buque) {
		if(buque.distanciaHaciaDestino() < 50d) {
			buque.cambiarEstado(new Inbound());
			buque.avisarLlegada();
		}
		
	}
}
