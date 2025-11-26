package tpfinalpoo2;

public class Outbound extends EstadoGPS {
	private boolean se_aviso_partida = false;

	@Override
	public void avanzar(Buque buque) {
		this.avanzarAvisandoLlegada(buque);
		this.avanzarAvisandoPartida(buque);
		buque.avanzarADestino();
	}

	private void avanzarAvisandoLlegada(Buque buque) {
		if (buque.distanciaHaciaDestino() < 50d) {
			buque.cambiarEstado(new Inbound());
			buque.avisarLlegada();
		}
	}

	private void avanzarAvisandoPartida(Buque buque) {
		if (!se_aviso_partida && buque.distanciaHaciaOrigen() >= 1d) {
			buque.avisarPartida();
			se_aviso_partida = true;
		}
	}

	@Override
	public void avisarPartida(Buque buque) {
		buque.terminalOrigen().avisarPartida(buque);
	}
}
