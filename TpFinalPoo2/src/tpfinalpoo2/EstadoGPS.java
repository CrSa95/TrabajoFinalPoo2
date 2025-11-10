package tpfinalpoo2;

public abstract class EstadoGPS {

	public abstract EstadoGPS actualizarGPS(Buque buque);

	public void empezarTrabajo(Buque buque) {
		throw new RuntimeException("No se puede iniciar trabajo actualmente; muy lejos de la terminal");
	}
	
	/*
	public void informar(Buque buque) {
		throw new RuntimeException("No se puede informar a la terminal actualmente: muy lejos de la terminal");
	}
	 */
	
	public void permitirSalida(Buque buque) {
		throw new RuntimeException("El buque ya esta en un viaje");
	}

	public void avisarLlegada(Buque buque) {
		throw new RuntimeException("El buque no puede avisarLlegada actualmente");
	}

	public void salir(Buque buque) {
		throw new RuntimeException("El buque no puede salir actualmente");
	}
}
