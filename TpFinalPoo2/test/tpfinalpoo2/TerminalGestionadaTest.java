package tpfinalpoo2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
	private Naviera naviera;
	private Container container;
	private Camion camion;
	private Chofer chofer;
	private Orden orden;
	private Cliente cliente;
	private Coordenadas coordenadas;
	private Buque buque;
	private Orden ordenMock;
	Chofer otroChofer; 
	Camion otroCamion; 
	
	@BeforeEach
	public void setUp() {
		coordenadas = mock(Coordenadas.class);
		terminalGestionada = new TerminalGestionada("Buenos Aires", coordenadas);
		naviera = mock(Naviera.class);
		container = mock(Container.class);
		chofer = mock(Chofer.class);
		camion = mock(Camion.class);
		cliente = mock(Cliente.class);
		buque = mock(Buque.class);
		ordenMock = mock(Orden.class);
		otroChofer = mock(Chofer.class);
		otroCamion = mock(Camion.class);
		orden = new Orden(cliente, camion, chofer, container, null);
		when(chofer.dni()).thenReturn("123456");
		when(camion.patente()).thenReturn("ABCDFGH");
		when(container.id()).thenReturn("TEST1234567");
		when(otroChofer.dni()).thenReturn("333443");
		when(otroCamion.patente()).thenReturn("AAAAAA");
	}


	@Test
	void ingresarCarga() {
		terminalGestionada.exportar(orden);
		Assertions.assertDoesNotThrow(() -> terminalGestionada.retirarCarga(container, camion, chofer));
	}
	
	@Test
	void retirarUnaCarga() {
		terminalGestionada.exportar(orden);
		Assertions.assertDoesNotThrow(() -> terminalGestionada.ingresarCarga(container, camion, chofer));
	}

	@Test
	void personalNoAutorizadoNoPuedorIngresarCarga() {
		RuntimeException exception;
		terminalGestionada.exportar(orden);
	
		when(container.isEqual(any(Container.class))).thenReturn(true);
		exception = Assertions.assertThrows(
				RuntimeException.class,
				() -> terminalGestionada.ingresarCarga(container, camion, otroChofer));
		
		assertEquals("Chofer no autorizado", exception.getMessage());
		
		exception = Assertions.assertThrows(RuntimeException.class,
				() -> terminalGestionada.ingresarCarga(container, otroCamion, chofer));
		
		assertEquals("Camión no autorizado", exception.getMessage());
	}

	@Test
	void personalNoAutorizadoNoPuedorRetirarCarga() {
		RuntimeException exception;
		terminalGestionada.importar(orden);
		when(container.isEqual(any(Container.class))).thenReturn(true);

		exception = Assertions.assertThrows(RuntimeException.class,
				() -> terminalGestionada.retirarCarga(container, camion, otroChofer));
		assertEquals("Chofer no autorizado", exception.getMessage());
		
		exception = Assertions.assertThrows(RuntimeException.class,
				() -> terminalGestionada.retirarCarga(container, otroCamion, chofer));
		assertEquals("Camión no autorizado", exception.getMessage());
	}



	@Test
	void seCambiaLaEstrategiaDeBusquedaDeCircuitos() {
		TerminalGestionada otraTerminal = new TerminalGestionada("Montevideo", null);
		IBusquedaCircuito estrategia1 = spy(IBusquedaCircuito.class);
		IBusquedaCircuito estrategia2 = spy(IBusquedaCircuito.class);
		IBusquedaCircuito estrategia3 = spy(IBusquedaCircuito.class);

		terminalGestionada.setEstrategiaDeBusqueda(estrategia1);
		terminalGestionada.buscarMejorCircuitoParaLLegarA(otraTerminal);
		verify(estrategia1).seleccionarMejor(terminalGestionada.getNavieras(), terminalGestionada, otraTerminal);

		terminalGestionada.setEstrategiaDeBusqueda(estrategia2);
		terminalGestionada.buscarMejorCircuitoParaLLegarA(otraTerminal);
		verify(estrategia2).seleccionarMejor(terminalGestionada.getNavieras(), terminalGestionada, otraTerminal);

		terminalGestionada.setEstrategiaDeBusqueda(estrategia3);
		terminalGestionada.buscarMejorCircuitoParaLLegarA(otraTerminal);
		verify(estrategia3).seleccionarMejor(terminalGestionada.getNavieras(), terminalGestionada, otraTerminal);
	}


	@Test
	void terminalGestionadaPuedeNotificarASusClientes() {
		terminalGestionada.exportar(ordenMock);
		terminalGestionada.notificarClientes(buque);
		verify(ordenMock).notificarLlegada(buque);
	}
	
	@Test 
	void notificarPartida() {
		terminalGestionada.exportar(ordenMock);
		terminalGestionada.avisarPartida(buque);
		verify(ordenMock).notificarPartida(buque);
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
	void testUnaTerminalPuedeBuscarViajes() {
		Filtro filtro = spy(Filtro.class);
		List<Filtro> filtros = Arrays.asList(filtro);
		Naviera naviera = spy(Naviera.class);
		terminalGestionada.agregarNaviera(naviera);
		terminalGestionada.busquedaDeRutasMaritimasQueCumplan(filtros);
		verify(naviera).buscarViajesQueCumplan(filtros);
	}

	@Test
	void unaTerminalConoceSusCoordenadas() {
		when(coordenadas.coordX()).thenReturn(0d);
		when(coordenadas.coordY()).thenReturn(0d);

		Assertions.assertEquals(coordenadas.coordX(), terminalGestionada.coordenadas().coordX());
		Assertions.assertEquals(coordenadas.coordY(), terminalGestionada.coordenadas().coordY());
	}

}
