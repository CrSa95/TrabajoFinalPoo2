package tpfinalpoo2;

public class Outbound extends EstadoGPS {	
	private boolean se_aviso_partida = false;
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
		
		if( !se_aviso_partida && buque.distanciaHaciaOrigen() >= 1d){
			buque.avisarPartida();
			se_aviso_partida = true;
		}
		
	}
}
