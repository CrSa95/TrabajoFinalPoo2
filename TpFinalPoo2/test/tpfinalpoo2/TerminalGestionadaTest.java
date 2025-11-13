package tpfinalpoo2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TerminalGestionadaTest {

	private TerminalGestionada terminalGestionada;
	private Terminal terminalDestino;
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
		terminalDestino = mock(Terminal.class);
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
	void proximaFecha() {
		LocalDateTime fecha_esperada = LocalDateTime.now();
		Terminal terminal = mock(Terminal.class);
		terminalGestionada.agregarNaviera(naviera);
		when(naviera.proximaFecha(terminalGestionada, terminal)).thenReturn(fecha_esperada);
		assertEquals(fecha_esperada, terminalGestionada.proximaFecha(terminal));
		verify(naviera).proximaFecha(terminalGestionada, terminal);
	}
	
	@Test 
	void proximaFechaDeUnaTerminalSinViajesDevuelveLaFechaMaxima() {
		LocalDateTime fecha_esperada = LocalDateTime.MAX;
		Terminal terminal = mock(Terminal.class);
		terminalGestionada.agregarNaviera(naviera);
		when(naviera.proximaFecha(eq(terminalGestionada), any(Terminal.class))).thenReturn(fecha_esperada);
		assertEquals(fecha_esperada, terminalGestionada.proximaFecha(terminal));
	}
	
	@Test 
	void proximaFechaDeUnaTerminalSinNavierasDevuelveLaFechaMaxima() {
		LocalDateTime fecha_esperada = LocalDateTime.MAX;
		Terminal terminal = mock(Terminal.class);
		assertEquals(fecha_esperada, terminalGestionada.proximaFecha(terminal));

	}

	@Test
	void cargasImportadasEnViajeDevuelveCargasEsperadas() {
	    Viaje viaje = mock(Viaje.class);
	    Container contImport = mock(Container.class);
	    Container contExport = mock(Container.class);
	    Container contOtroViaje = mock(Container.class);

	    Orden importacion = new Orden(cliente, camion, chofer, contImport, viaje);

	    Orden exportacion = new Orden(cliente, camion, chofer, contExport, viaje);

	    Viaje otroViaje = mock(Viaje.class);
	    Orden otraImport = new Orden(cliente, camion, chofer, contOtroViaje, otroViaje);

	    terminalGestionada.importar(importacion);
	    terminalGestionada.exportar(exportacion);
	    terminalGestionada.importar(otraImport);

	    List<Container> resultado = terminalGestionada.cargasImportadasEnViaje(viaje);

	    Assertions.assertEquals(1, resultado.size());
	    Assertions.assertTrue(resultado.contains(contImport));
	}
	
	@Test
	void cargasExportadasEnViajeDevuelveCargasEsperadas() {
	    Viaje viaje = mock(Viaje.class);
	    Container contImport = mock(Container.class);
	    Container contExport = mock(Container.class);
	    Container contOtroViaje = mock(Container.class);

	    Orden importacion = new Orden(cliente, camion, chofer, contImport, viaje);

	    Orden exportacion = new Orden(cliente, camion, chofer, contExport, viaje);

	    Viaje otroViaje = mock(Viaje.class);
	    Orden otraImport = new Orden(cliente, camion, chofer, contOtroViaje, otroViaje);

	    terminalGestionada.importar(importacion);
	    terminalGestionada.exportar(exportacion);
	    terminalGestionada.importar(otraImport);

	    List<Container> resultado = terminalGestionada.cargasExportadasEnViaje(viaje);

	    Assertions.assertEquals(1, resultado.size());
	    Assertions.assertTrue(resultado.contains(contExport));
	}


	@Test
	void testCantCargasEnViaje() {
	    Viaje viaje = mock(Viaje.class);
	    Orden orden1 = mock(Orden.class);
	    Orden orden2 = mock(Orden.class);

	    when(orden1.viaje()).thenReturn(viaje);
	    when(orden2.viaje()).thenReturn(viaje);

	    terminalGestionada.exportar(orden1);
	    terminalGestionada.exportar(orden2);

	    Assertions.assertEquals(2, terminalGestionada.cantCargasEnViaje(viaje));
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
	void terminalGestionadaPuedeFacturar() {
		terminalGestionada.exportar(ordenMock);
		terminalGestionada.facturar(buque);
		verify(ordenMock).facturar(buque);
	}
	
	@Test 
	void notificarPartidaYFacturacionEnAvisarPartida() {
		terminalGestionada.exportar(ordenMock);
		terminalGestionada.avisarPartida(buque);
		verify(ordenMock).notificarPartida(buque);
		verify(ordenMock).facturar(buque);
	}
	
	@Test 
	void notificarLlegada() {
		terminalGestionada.importar(ordenMock);
		terminalGestionada.avisarLlegada(buque);
		verify(ordenMock).notificarLlegada(buque);
		verify(ordenMock).facturar(buque);
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
	
	@Test
	void laTerminalConoceElTiempoQueLeTomaLlegarAOtraTerminalMedianteUnaNaviera() {

		terminalGestionada.agregarNaviera(naviera);
		terminalGestionada.tiempoHasta(naviera, terminalDestino);
		verify(naviera).tiempoDesdeHasta(terminalGestionada, terminalDestino);
	}
}
