package tpfinalpoo2;

public class Refrigerado extends Container implements Refrigerable {
	private Double temperatura_requerida;
	private Double consumo_kw_hora;

	public Refrigerado(String ownerID, Double altura, Double ancho, Double largo, Double peso, Double consumoKwHora,
			Double tempRequerida) {
		super(ownerID, altura, ancho, largo, peso);
		this.temperatura_requerida = tempRequerida;
		this.consumo_kw_hora = consumoKwHora;
	}
	
	@Override
 	public boolean aplicaServicioElectrico() {
		return true;
	}

	@Override
	public Double consumoKwHora() {
		return this.consumo_kw_hora;
	}

	public Double temperaturaRequerida() {
		return this.temperatura_requerida;
	}

	@Override
	public String tipo() {
		return "Refrigerado";
	}
}
