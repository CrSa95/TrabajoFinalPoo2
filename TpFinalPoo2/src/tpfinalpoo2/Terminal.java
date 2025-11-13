package tpfinalpoo2;

public interface Terminal {
	public String getNombre();

	public Coordenadas coordenadas();

	public void avisarPartida(Buque buque);

	public void avisarLlegada(Buque buque);

	void facturar(Buque buque);
	
}
