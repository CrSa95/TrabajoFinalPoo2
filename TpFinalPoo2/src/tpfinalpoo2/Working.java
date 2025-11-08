package tpfinalpoo2;

public class Working extends EstadoGPS {
	private boolean se_inicio_partida = false;

	@Override
	public EstadoGPS actualizarGPS(Buque buque) {
		if (se_inicio_partida) {
			return new Departing();
		}
		return this;
	}

	@Override
	public void permitirSalida(Buque buque) {
		this.se_inicio_partida = true;
		buque.siguienteDestino();
	}

}
