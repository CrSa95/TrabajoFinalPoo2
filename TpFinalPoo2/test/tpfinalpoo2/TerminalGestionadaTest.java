package tpfinalpoo2;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TerminalGestionadaTest {

	private TerminalGestionada terminalGestionada;
	private TerminalGestionada otraTerminal;
	private Naviera naviera;
	private Naviera otraNaviera;
	private MenorCantidadTerminalesIntermedias menorCantTerminalesIntermedias;
	private Circuito circuitoUno;
	private Circuito circuitoDos;
	private Circuito circuitoTres;
	private Circuito circuitoCuatro;
	private Viaje viaje;
	private Viaje unViaje;
	private Viaje otroViaje;
	private AND filtroANDMock;
	private OR filtroORMock;
	private FiltroFechaSalida filtroFechaSalida;
	private FiltroFechaLlegada filtroFechaLlegada;
	private FiltroPuertoDestino filtroPuertoDestino;

	Container container;
	Camion camion;
	Chofer chofer;
	Orden orden;
	Cliente cliente;

	@BeforeEach
	public void setUp() {
		terminalGestionada = new TerminalGestionada("Buenos Aires", null);
		otraTerminal = new TerminalGestionada("Montevideo", null);
		naviera = mock(Naviera.class);
		otraNaviera = mock(Naviera.class);
		menorCantTerminalesIntermedias = mock(MenorCantidadTerminalesIntermedias.class);
		circuitoUno = mock(Circuito.class);
		circuitoDos = mock(Circuito.class);
		circuitoTres = mock(Circuito.class);
		circuitoCuatro = mock(Circuito.class);
		viaje = mock(Viaje.class);
		unViaje = mock(Viaje.class);
		otroViaje = mock(Viaje.class);
		filtroANDMock = mock(AND.class);
		filtroORMock = mock(OR.class);
		filtroFechaSalida = mock(FiltroFechaSalida.class);
		filtroFechaLlegada = mock(FiltroFechaLlegada.class);
		filtroPuertoDestino = mock(FiltroPuertoDestino.class);
		container = new Tanque("FakeID", 1d, 1d, 1d, 1d);
		chofer = new Chofer("44444444");
		camion = new Camion("ABBDDF");
		cliente = new Cliente("Cliente");
		orden = new Orden(container, camion, chofer, cliente);
	}

	@Test
	void retirarUnaCarga() {
		terminalGestionada.exportar(orden);
		Assertions.assertDoesNotThrow(() -> terminalGestionada.ingresarCarga(container, camion, chofer));
	}

	@Test
	void personalNoAutorizadoNoPuedorIngresarCarga() {
		Chofer otroChofer = new Chofer("333333");
		Camion otroCamion = new Camion("ZZZZZZ");
		terminalGestionada.exportar(orden);
		Assertions.assertThrows(RuntimeException.class,
				() -> terminalGestionada.ingresarCarga(container, camion, otroChofer));
		Assertions.assertThrows(RuntimeException.class,
				() -> terminalGestionada.ingresarCarga(container, otroCamion, chofer));
	}

	@Test
	void personalNoAutorizadoNoPuedorRetirarCarga() {
		Chofer otroChofer = new Chofer("333333");
		terminalGestionada.importar(orden);
		Assertions.assertThrows(RuntimeException.class,
				() -> terminalGestionada.retirarCarga(container, camion, otroChofer));
		Camion otroCamion = new Camion("ZZZZZZ");
		Assertions.assertThrows(RuntimeException.class,
				() -> terminalGestionada.retirarCarga(container, otroCamion, chofer));
	}

	@Test
	void ingresarCarga() {
		terminalGestionada.exportar(orden);
		Assertions.assertDoesNotThrow(() -> terminalGestionada.retirarCarga(container, camion, chofer));
	}

	@Test
	void testLaTerminalConoceSuNombre() {
		Assertions.assertEquals("Buenos Aires", terminalGestionada.getNombre());
	}

	@Test
	void testLaTerminalPuedeAgregarNavieras() {
		Assertions.assertEquals(0, terminalGestionada.getNavieras().size());
		terminalGestionada.agregarNaviera(naviera);
		Assertions.assertEquals(1, terminalGestionada.getNavieras().size());
	}

	@Test
	void testLaTerminalPuedeCambiarDeEstrategiaParaLaBusquedaDeCircuitos() {
		Assertions.assertEquals(null, terminalGestionada.getEstrategiaDeBusqueda());
		terminalGestionada.setEstrategiaDeBusqueda(menorCantTerminalesIntermedias);
		Assertions.assertTrue(terminalGestionada.getEstrategiaDeBusqueda().equals(menorCantTerminalesIntermedias));
	}

	@Test
	void testUnaTerminalPuedeBuscarViajes() {

		List<Viaje> viajes = Arrays.asList(viaje, unViaje, otroViaje);

		List<Filtro> filtrosAND = Arrays.asList(filtroFechaSalida, filtroFechaLlegada);

		List<Filtro> filtrosOR = Arrays.asList(filtroPuertoDestino);

		when(filtroANDMock.getFiltros()).thenReturn(filtrosAND);
		when(filtroORMock.getFiltros()).thenReturn(filtrosOR);

		when(filtroANDMock.filtrar(viajes)).thenReturn(Arrays.asList(viaje));
		when(filtroORMock.filtrar(viajes)).thenReturn(Arrays.asList(viaje, unViaje));

		List<Filtro> filtros = Arrays.asList(filtroANDMock, filtroORMock);

		when(naviera.getViajes()).thenReturn(Arrays.asList(viaje, unViaje, otroViaje));
		when(otraNaviera.getViajes()).thenReturn(Arrays.asList(viaje, otroViaje));

		when(naviera.buscarViajesQueCumplan(filtros)).thenReturn(Arrays.asList(viaje, unViaje));
		when(otraNaviera.buscarViajesQueCumplan(filtros)).thenReturn(Arrays.asList(viaje));

		terminalGestionada.agregarNaviera(naviera);
		terminalGestionada.agregarNaviera(otraNaviera);

		List<Viaje> resultado = terminalGestionada.busquedaDeRutasMaritimasQueCumplan(filtros);

		Assertions.assertTrue(resultado.contains(viaje));
		Assertions.assertTrue(resultado.contains(unViaje));
		Assertions.assertFalse(resultado.contains(otroViaje));
	}

	@Test
	void testLaTerminalPuedeBuscarUnMejorCircuito() {

		terminalGestionada.setEstrategiaDeBusqueda(menorCantTerminalesIntermedias);
		terminalGestionada.agregarNaviera(naviera);
		terminalGestionada.agregarNaviera(otraNaviera);

		List<Circuito> circuitos = Arrays.asList(circuitoUno, circuitoDos);
		List<Circuito> otrosCircuitos = Arrays.asList(circuitoTres, circuitoCuatro);

		when(naviera.getCircuitos()).thenReturn(circuitos);
		when(otraNaviera.getCircuitos()).thenReturn(otrosCircuitos);

		when(menorCantTerminalesIntermedias.seleccionarMejor(terminalGestionada.getNavieras(), terminalGestionada,
				otraTerminal)).thenReturn(circuitoUno);

		Circuito mejorCircuito = terminalGestionada.buscarMejorCircuitoParaLLegarA(otraTerminal);

		Assertions.assertTrue(mejorCircuito.equals(circuitoUno));

		Assertions.assertFalse(mejorCircuito.equals(circuitoDos));
	}
}
