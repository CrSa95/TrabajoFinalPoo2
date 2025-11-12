package tpfinalpoo2;

public class Working extends EstadoGPS {

	@Override
	public void permitirSalida(Buque buque) {
		buque.cambiarEstado(new Departing());
	}
}
