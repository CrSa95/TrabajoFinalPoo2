package tpfinalpoo2;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
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
	private Container container;
	private Camion camion;
	private Chofer chofer;
	private Orden orden;
	private Cliente cliente;
	private Coordenadas coordenadas;
	private Buque buque;
	private Orden ordenMock;

	@BeforeEach
	public void setUp() {
		coordenadas = mock(Coordenadas.class);
		terminalGestionada = new TerminalGestionada("Buenos Aires", coordenadas);
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
		orden = new Orden(container, camion, chofer, cliente, null, null);
		buque = mock(Buque.class);
		ordenMock = mock(Orden.class);
	}
	
	@Test
	void unaTerminalConoceSusCoordenadas() {
		when(coordenadas.coordX()).thenReturn(0d);
		when(coordenadas.coordY()).thenReturn(0d);
		
		Assertions.assertEquals(coordenadas.coordX(), terminalGestionada.coordenadas().coordX());
		Assertions.assertEquals(coordenadas.coordY(), terminalGestionada.coordenadas().coordY());
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
	void terminalGestionadaPuedeNotificarASusClientes() {
		
		terminalGestionada.exportar(ordenMock);
		
		terminalGestionada.notificarClientes(buque);
		
		verify(ordenMock).notificarLlegada(buque);
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
		Filtro filtro = spy(Filtro.class); 
		List<Filtro> filtros = Arrays.asList(filtro);
		Naviera naviera = spy(Naviera.class);
		terminalGestionada.agregarNaviera(naviera);
		terminalGestionada.busquedaDeRutasMaritimasQueCumplan(filtros);
		verify(naviera).buscarViajesQueCumplan(filtros);
	}

	
	@Test 
	void seCambiaLaEstrategiaDeBusquedaDeCircuitos() {
		IBusquedaCircuito estrategia1 = spy(IBusquedaCircuito.class);
		IBusquedaCircuito estrategia2 = spy(IBusquedaCircuito.class);
		IBusquedaCircuito estrategia3 = spy(IBusquedaCircuito.class);


		terminalGestionada.setEstrategiaDeBusqueda(estrategia1);
		terminalGestionada.buscarMejorCircuitoParaLLegarA(otraTerminal);
		verify(estrategia1).seleccionarMejor(terminalGestionada.getNavieras(),terminalGestionada, otraTerminal);
		
		terminalGestionada.setEstrategiaDeBusqueda(estrategia2);
		terminalGestionada.buscarMejorCircuitoParaLLegarA(otraTerminal);
		verify(estrategia2).seleccionarMejor(terminalGestionada.getNavieras(),terminalGestionada, otraTerminal);
		
		terminalGestionada.setEstrategiaDeBusqueda(estrategia3);
		terminalGestionada.buscarMejorCircuitoParaLLegarA(otraTerminal);
		verify(estrategia3).seleccionarMejor(terminalGestionada.getNavieras(),terminalGestionada, otraTerminal);
	}

}
