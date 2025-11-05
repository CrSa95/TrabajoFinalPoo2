package tpfinalpoo2;

public class Buque {
	private EstadoGPS estado_gps;
	public Buque() {
		this.estado_gps = new Outbound();
	}
	public void asignar(Viaje viaje) {
		// TODO Auto-generated method stub
		
	}

	public void actualizarGPS() {
		this.estado_gps = this.estado_gps.actualizarGPS(this);
		
	}

	public void avisarLlegada() {
		// TODO Auto-generated method stub
		
	}
	public Double distanciaHaciaDestino() {
		// TODO Auto-generated method stub
		return 0d;
	}

}
