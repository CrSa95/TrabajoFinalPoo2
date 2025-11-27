package tpfinalpoo2;

public abstract class Container {
	private Double altura;
	private Double ancho;
	private Double largo;
	protected Double peso;
	private String id;

	public Container(String ownerID, Double altura, Double ancho, Double largo, Double peso) {
		this.setAltura(altura);
		this.setAncho(ancho);
		this.setLargo(largo);
		this.setPeso(peso);
		this.setOwner(ownerID);
	}

	public boolean desconsolidable() {
		return false;
	}
	
	public boolean aplicaServicioElectrico() {
		return false;
	}

	public String id() {
		return this.id;

	}

	public boolean isEqual(Container container) {
		return this.id().equals(container.id());
	}

	public double metrosCubicos() {
		return (altura * ancho * largo);
	}

	public Double peso() {
		return this.peso;
	}

	private void setAltura(Double altura) {
		if (altura < 0) {
			throw new RuntimeException("Container: Altura invalida");
		}
		this.altura = altura;
	}

	private void setAncho(Double ancho) {
		if (ancho < 0) {
			throw new RuntimeException("Container: Ancho invalido");
		}
		this.ancho = ancho;
	}

	private void setLargo(Double largo) {
		if (largo < 0) {
			throw new RuntimeException("Container: Largo invalido");
		}
		this.largo = largo;

	}

	private void setOwner(String ownerId) {
		if (ownerId == null || ownerId.isEmpty() || ownerId.isBlank()) {
			throw new RuntimeException("Container: Dueño invalido");
		}
		int numeroRandom = (int) (Math.random() * (9999998));
		this.id = ownerId.concat(String.format("%07d", numeroRandom));
	}

	private void setPeso(Double peso) {
		if (peso < 0) {
			throw new RuntimeException("Container: Peso invalido");
		}
		this.peso = peso;

	}

	public abstract String tipo();
}
