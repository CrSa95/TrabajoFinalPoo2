package tpfinalpoo2;

public class Buque {
	EstadoGPS estado_gps;
	boolean working;
	public Buque() {
		this.estado_gps = new Inbound();
		this.working = false;
	}
	public double distanciaAdestino() {
		// TODO Auto-generated method stub
		return 0;
	}

	public EstadoGPS estadoActual() {
		// TODO Auto-generated method stub
		return this.estado_gps;
	}
	public boolean trabajando() {
		// TODO Auto-generated method stub
		return working;
	}

}
