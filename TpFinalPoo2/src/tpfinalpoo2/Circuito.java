package tpfinalpoo2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToDoubleFunction;

public class Circuito {

	private List<Tramo> tramos = new ArrayList<>();

	public void agregarTramo(Tramo tramo) {
		this.getTramos().add(tramo);
	}

	public List<Tramo> getTramos() {
		return this.tramos;
	}

	public double costoCircuito() {
		return tramos.stream().mapToDouble(Tramo::getCosto).sum();
	}

	public double tiempoTotal() {
		return tramos.stream().mapToDouble(Tramo::getDuracion).sum();
	}

	public double costoTotalDesdeHasta(Terminal terminalOrigen, Terminal terminalDestino) {
		return this.totalDesdeHasta(terminalOrigen, terminalDestino, Tramo::getCosto);
	}

	public double tiempoTotalDesdeHasta(Terminal terminalOrigen, Terminal terminalDestino) {

		return this.totalDesdeHasta(terminalOrigen, terminalDestino, Tramo::getDuracion);
	}

	private double totalDesdeHasta(Terminal terminalOrigen, Terminal terminalDestino,
			ToDoubleFunction<Tramo> valorExtractor) {

		// se verifica que en algun tramo exista como terminal de origen la terminal
		// pasada por parametro
		// como asi tambien la terminal destino para que a la hora de calcular no haya
		// errores de calculo
		this.existeRecorridoEntre(terminalOrigen, terminalDestino);

		// Calcula el costo desde la terminal de origen hasta la terminal destino
		double costoDesdeHasta = this.tramos.stream().dropWhile(tramo -> !tramo.tieneDeOrigenA(terminalOrigen))
				.takeWhile(tramo -> !tramo.tieneDeDestinoA(terminalDestino)).mapToDouble(valorExtractor).sum();

		// TakeWhile descarta el ultimo tramo, por lo tanto hay que sumarlo
		double costoUltimoTramo = this.tramos.stream().filter(tramo -> tramo.tieneDeDestinoA(terminalDestino))
				.mapToDouble(valorExtractor).findFirst().orElse(0.0);

		return costoDesdeHasta + costoUltimoTramo;
	}

	public int terminalesIntermediasDesdeHasta(Terminal terminalOrigen, Terminal terminalDestino) {

		// se verifica que en algun tramo exista como terminal de origen la terminal
		// pasada por parametro
		// como asi tambien la terminal destino para que a la hora de calcular no haya
		// errores de calculo
		this.existeRecorridoEntre(terminalOrigen, terminalDestino);

		List<Tramo> tramosIntermedios = tramos.stream().dropWhile(tramo -> !tramo.tieneDeOrigenA(terminalOrigen))
				.takeWhile(tramo -> !tramo.tieneDeDestinoA(terminalDestino)).toList();

		// se utiliza Math.max en caso de que sea un unico tramo el que tenga
		// la terminal de origen y destino por que lo seria una lista vacia y al
		// restarle 1
		// daria como resultado -1
		return Math.max(0, tramosIntermedios.size() - 1);
	}

	public void existeRecorridoEntre(Terminal terminalOrigen, Terminal terminalDestino) {

		if (!this.existeAlgunTramoConOrigen(terminalOrigen) && !this.existeAlgunTramoConDestino(terminalDestino)) {

			throw new IllegalArgumentException("Origen y destino inexistentes en el circuito");
		}
	}

	private boolean existeAlgunTramoConOrigen(Terminal terminalOrigen) {
		return this.tramos.stream().anyMatch(tramo -> tramo.tieneDeOrigenA(terminalOrigen));
	}

	private boolean existeAlgunTramoConDestino(Terminal terminalDestino) {
		return this.tramos.stream().anyMatch(tramo -> tramo.tieneDeDestinoA(terminalDestino));
	}

	public Terminal destinoActual() {
		return this.tramoActual().getTerminalDestino();
	}

	private Tramo tramoActual() {
		return null;
	}

	public Object origenActual() {
		return null;
	}

	public Terminal terminalOrigen() {
		return this.tramos.getFirst().getTerminalOrigen();
	}

	public long tiempoHaciaTerminalDesdeOrigen(Terminal terminal) {
		return (long) this.costoTotalDesdeHasta(this.terminalOrigen(), terminal);
	}

	public Double tiempoHaciaDestinoActual(Terminal terminalOrigen) {
		return 0d;
	}

	public Tramo tramoInicial() {
		return this.getTramos().getFirst();
	}

	public Tramo siguienteTramo(Tramo tramo_actual) {
		Tramo siguiente = this.tramoInicial();
		try {
			int siguienteIndice = this.getTramos().indexOf(tramo_actual) + 1;
			return this.getTramos().get(siguienteIndice);
		} catch (IndexOutOfBoundsException e) {
			return this.tramoInicial();
		}
	}

}
