package tpfinalpoo2;

public interface Terminal {
	public void avisarLlegada(Buque buque);

	public void avisarPartida(Buque buque);

	public Coordenadas coordenadas();

	void facturar(Buque buque);

	public String getNombre();

}
