package tpfinalpoo2;

public abstract class EstadoGPS {

	public abstract EstadoGPS actualizarGPS(Buque buque);
	
	public void empezarTrabajo(Buque buque) {}
	public void informar(Buque buque) {}
	public void permitirSalida(Buque buque) {}

	public void avisarLlegada(Buque buque) {}
}
