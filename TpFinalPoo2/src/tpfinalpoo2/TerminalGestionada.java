package tpfinalpoo2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class TerminalGestionada implements Terminal {

	private String nombre;
	private Coordenadas coordenadas;
	private IBusquedaCircuito estrategiaBusquedaMejorCircuito;
	private List<Naviera> navieras = new ArrayList<>();
	private List<Orden> ordenes = new ArrayList<>();

	public TerminalGestionada(String nombre, Coordenadas coordenadas) {
		this.nombre = nombre;
		this.coordenadas = coordenadas;
		this.setEstrategiaDeBusqueda(new MenorPrecioADestino());
	}

	public void agregarNaviera(Naviera naviera) {
		this.getNavieras().add(naviera);
	}


	public Circuito buscarMejorCircuitoParaLLegarA(Terminal terminalDestino) {

		return this.getEstrategiaDeBusqueda().seleccionarMejor(this.getNavieras(), this, terminalDestino);
	}

	public List<Viaje> busquedaDeRutasMaritimasQueCumplan(List<Filtro> filtros) {
		return this.getNavieras().stream().flatMap(naviera -> naviera.buscarViajesQueCumplan(filtros).stream())
				.collect(Collectors.toList());
	}

	public int cantCargasEnViaje(Viaje viaje) {
		return this.cargasEnViaje(viaje).size();
	}

	public List<Container> cargasEnViaje(Viaje viaje) {
		return filtrarCargas(o -> viaje.equals(o.viaje()));
	}

	public List<Container> cargasExportadasEnViaje(Viaje viaje) {
		return filtrarCargas(o -> viaje.equals(o.viaje()) && o.esExportacion(this));
	}

	public List<Container> cargasImportadasEnViaje(Viaje viaje) {
		return filtrarCargas(o -> viaje.equals(o.viaje()) && o.esImportacion(this));
	}

	@Override
	public Coordenadas coordenadas() {
		return this.coordenadas;
	}

	public void exportarHacia(Orden orden, Terminal terminal) {
		orden.terminalOrigen(this);
		orden.terminalDestino(terminal);
		this.ordenes.add(orden);
	}

	public void importarDesde(Orden orden, Terminal terminal) {
		orden.terminalOrigen(terminal);
		orden.terminalDestino(this);
		this.ordenes.add(orden);
	}

	@Override
	public void facturar(Buque buque) {
		this.ordenes.forEach(orden -> orden.facturar(this, buque));
	}

	private List<Container> filtrarCargas(Predicate<Orden> criterio) {
		return this.ordenes.stream().filter(criterio).map(Orden::carga).toList();
	}

	public IBusquedaCircuito getEstrategiaDeBusqueda() {
		return this.estrategiaBusquedaMejorCircuito;
	}

	public List<Naviera> getNavieras() {
		return this.navieras;
	}

	@Override
	public String getNombre() {
		return this.nombre;
	}

	public void ingresarCarga(Container container, Camion camion, Chofer chofer) {
		this.ordenes.stream().forEach(orden -> orden.verificar(camion, chofer, container));
	}

	@Override
	public void avisarPartida(Buque buque) {
		this.ordenes.forEach(orden -> {
			orden.notificarPartida(this, buque);
			orden.facturar(this, buque);
		});
	}

	@Override
	public void avisarLlegada(Buque buque) {
		this.ordenes.forEach(orden->{
			orden.notificarLlegada(this, buque);
			orden.facturar(this, buque);
		});
	}
	public LocalDateTime proximaFecha(Terminal terminal) {
		return this.navieras.stream().map(nav -> nav.proximaFecha(this, terminal))
				.min((arg0, arg1) -> arg0.compareTo(arg1)).orElse(LocalDateTime.MAX);
	}

	public void retirarCarga(Container container, Camion camion, Chofer chofer) {
		this.ordenes.stream().forEach(orden -> orden.verificar(camion, chofer, container));

	}

	public void setEstrategiaDeBusqueda(IBusquedaCircuito strategyCircuitos) {
		this.estrategiaBusquedaMejorCircuito = strategyCircuitos;
	}

	public double tiempoHasta(Naviera naviera, Terminal terminalDestino) {
		return naviera.tiempoDesdeHasta(this, terminalDestino);
	}
}
