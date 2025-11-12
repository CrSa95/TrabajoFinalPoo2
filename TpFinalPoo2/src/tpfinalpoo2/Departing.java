package tpfinalpoo2;

public class Departing extends EstadoGPS {

	
	@Override 
	public void salir(Buque buque) {
		buque.cambiarEstado(new Outbound());
		buque.siguienteDestino();
	}
}
