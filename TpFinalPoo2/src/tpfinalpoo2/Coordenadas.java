package tpfinalpoo2;

class Coordenadas {

	private Double coord_x;
	private Double coord_y;

	public Coordenadas(double x, double y) {
		this.coord_x = x;
		this.coord_y = y;
	}

	public Double distanciaHacia(Coordenadas coordenadas) {
		Double x = Math.pow(this.coord_x - coordenadas.coordX(), 2);
		Double y = Math.pow(this.coord_y - coordenadas.coordY(), 2);
		return Math.sqrt(x + y);
	}

	public Double coordX() {
		return this.coord_x;

	}

	public Double coordY() {
		return this.coord_y;
	}

	public void avanzarX(Double cant) {
		this.coord_x += cant;
	}

	public void avanzarY(Double cant) {
		this.coord_y += cant;
	}

}