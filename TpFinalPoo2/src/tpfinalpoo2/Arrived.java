package tpfinalpoo2;

public class Arrived extends EstadoGPS {
	private boolean se_inicio_trabajo = false;

	@Override
	public void empezarTrabajo(Buque buque) {
		se_inicio_trabajo = true;
	}

	@Override
	public EstadoGPS actualizarGPS(Buque buque) {
		if (se_inicio_trabajo) {
			return new Working();
		}
		return this;
	}

}
