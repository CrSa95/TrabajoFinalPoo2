package tpfinalpoo2;

import java.util.ArrayList;
import java.util.List;
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

	public void exportar(Orden orden) {
		this.ordenes.add(orden);
	}

	public void importar(Orden orden) {
		this.ordenes.add(orden);
	}

	@Override
	public String getNombre() {
		return this.nombre;
	}

	public void agregarNaviera(Naviera naviera) {
		this.getNavieras().add(naviera);
	}

	public List<Naviera> getNavieras() {
		return this.navieras;
	}

	public void setEstrategiaDeBusqueda(IBusquedaCircuito strategyCircuitos) {
		this.estrategiaBusquedaMejorCircuito = strategyCircuitos;
	}

	public IBusquedaCircuito getEstrategiaDeBusqueda() {
		return this.estrategiaBusquedaMejorCircuito;
	}

	public List<Viaje> busquedaDeRutasMaritimasQueCumplan(List<Filtro> filtros) {
		return this.getNavieras().stream().flatMap(naviera -> naviera.buscarViajesQueCumplan(filtros).stream())
				.collect(Collectors.toList());
	}

	public Circuito buscarMejorCircuitoParaLLegarA(Terminal terminalDestino) {

		return this.getEstrategiaDeBusqueda().seleccionarMejor(this.getNavieras(), this, terminalDestino);
	}

	@Override
	public Coordenadas coordenadas() {
		return this.coordenadas;
	}

	public void retirarCarga(Container container, Camion camion, Chofer chofer) {
		this.ordenes.stream().forEach(orden -> orden.verificar(camion, chofer, container));
	}

	public void ingresarCarga(Container container, Camion camion, Chofer chofer) {
		this.ordenes.stream().forEach(orden -> orden.verificar(camion, chofer, container));
	}

	@Override
	public void notificarClientes(Buque buque) {
		this.ordenes.forEach(orden -> orden.notificarLlegada(buque));
	}

	@Override
	public void avisarPartida(Buque buque) {
		this.ordenes.forEach(orden -> orden.notificarPartida(buque));
		
	}

	@Override
	public void avisarLlegada(Buque buque) {
		// TODO Auto-generated method stub
		
	}

}
