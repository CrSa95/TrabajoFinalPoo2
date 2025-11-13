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
	}

	public void agregarNaviera(Naviera naviera) {
		this.getNavieras().add(naviera);
	}

	@Override
	public void avisarLlegada(Buque buque) {
		this.ordenes.forEach(orden -> orden.notificarLlegada(buque));
	}

	@Override
	public void avisarPartida(Buque buque) {
		this.ordenes.forEach(orden -> orden.notificarPartida(buque));
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
		return filtrarCargas(o -> viaje.equals(o.viaje()) && o.esExportacion());
	}

	public List<Container> cargasImportadasEnViaje(Viaje viaje) {
		return filtrarCargas(o -> viaje.equals(o.viaje()) && o.esImportacion());
	}

	@Override
	public Coordenadas coordenadas() {
		return this.coordenadas;
	}

	public void exportar(Orden orden) {
		orden.terminalOrigen(this);
		this.ordenes.add(orden);
	}

	@Override
	public void facturar(Buque buque) {
		this.ordenes.forEach(orden -> orden.facturar(buque));
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

	public void importar(Orden orden) {
		orden.terminalDestino(this);
		this.ordenes.add(orden);
	}

	public void ingresarCarga(Container container, Camion camion, Chofer chofer) {
		this.ordenes.stream().forEach(orden -> orden.verificar(camion, chofer, container));
	}

	@Override
	public void avisarPartida(Buque buque) {
		this.ordenes.forEach(orden -> {
			orden.notificarPartida(buque);
			orden.facturar(buque);
		});
	}

	@Override
	public void avisarLlegada(Buque buque) {
		this.ordenes.forEach(orden->{
			orden.notificarLlegada(buque);
			orden.facturar(buque);
		});

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
