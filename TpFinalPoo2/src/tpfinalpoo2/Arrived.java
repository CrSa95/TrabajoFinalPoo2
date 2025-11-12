package tpfinalpoo2;

public class Arrived extends EstadoGPS {
	@Override
	public void empezarTrabajo(Buque buque) {
		buque.cambiarEstado(new Working());
	}
}
