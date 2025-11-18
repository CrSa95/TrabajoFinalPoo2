package tpfinalpoo2;

class Coordenadas {

	private double coord_x;
	private double coord_y;

	public Coordenadas(double x, double y) {
		this.coord_x = x;
		this.coord_y = y;
	}

	public void avanzarHacia(double coordX, double coordY) {
		this.avanzarX(coordX);
		this.avanzarY(coordY);
	}
	
	private void avanzarX(double cant) {
		this.coord_x += cant;
	}

	private void avanzarY(double cant) {
		this.coord_y += cant;
	}

	public double coordX() {
		return this.coord_x;

	}

	public double coordY() {
		return this.coord_y;
	}

	public double distanciaHacia(Coordenadas coordenadas) {
		double x = Math.pow(this.coordX() - coordenadas.coordX(), 2);
		double y = Math.pow(this.coordY() - coordenadas.coordY(), 2);
		return Math.sqrt(x + y);
	}

}