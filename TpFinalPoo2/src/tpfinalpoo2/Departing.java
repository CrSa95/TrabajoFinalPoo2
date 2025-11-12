package tpfinalpoo2;

public class Departing extends EstadoGPS {

	@Override
	public EstadoGPS actualizarGPS(Buque buque) {
		if (buque.distanciaHaciaOrigen() > 1d) {
			return new Outbound();
		}
		return this;
	}
	
	@Override 
	public void salir(Buque buque) {
		buque.siguienteDestino();
		buque.actualizarGPS();
	}
}
