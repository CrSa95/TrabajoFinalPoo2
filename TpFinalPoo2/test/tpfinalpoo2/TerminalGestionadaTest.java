package tpfinalpoo2;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
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
	private Terminal terminalOrigen;
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
	private Viaje viaje;
	Chofer otroChofer;
	Camion otroCamion;

	@BeforeEach
	public void setUp() {
		coordenadas = mock(Coordenadas.class);
		terminalGestionada = new TerminalGestionada("Buenos Aires", coordenadas);
		terminalOrigen = mock(Terminal.class);
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
		viaje = mock(Viaje.class);
		orden = new Orden(cliente, camion, chofer, container, viaje);
		when(chofer.dni()).thenReturn("123456");
		when(camion.patente()).thenReturn("ABCDFGH");
		when(container.id()).thenReturn("TEST1234567");
		when(otroChofer.dni()).thenReturn("333443");
		when(otroCamion.patente()).thenReturn("AAAAAA");
		when(ordenMock.viaje()).thenReturn(viaje);
		when(naviera.tieneElViaje(any(Viaje.class))).thenReturn(true);
	}

	@Test
	void exportarOrdenLaValida() {
		terminalGestionada.agregarNaviera(naviera);
		when(ordenMock.viaje()).thenReturn(null);
		assertThrows(RuntimeException.class, () -> {
			terminalGestionada.exportarHacia(ordenMock, terminalDestino);
		}, "Orden invalida");

		when(ordenMock.viaje()).thenReturn(viaje);
		when(naviera.tieneElViaje(viaje)).thenReturn(false);
		assertThrows(RuntimeException.class, () -> terminalGestionada.exportarHacia(orden, terminalDestino),
				"Orden invalida");
		when(naviera.tieneElViaje(viaje)).thenReturn(true);
		assertDoesNotThrow(() -> terminalGestionada.exportarHacia(ordenMock, terminalDestino));
	} 
	

	@Test
	void importarOrdenLaValida() {
		terminalGestionada.agregarNaviera(naviera);
		when(ordenMock.viaje()).thenReturn(null);
		assertThrows(RuntimeException.class, () -> {
			terminalGestionada.importarDesde(ordenMock, terminalDestino);
		}, "Orden invalida");

		when(ordenMock.viaje()).thenReturn(viaje);
		when(naviera.tieneElViaje(viaje)).thenReturn(false);
		assertThrows(RuntimeException.class, () -> {
			terminalGestionada.importarDesde(ordenMock, terminalDestino);
		}, "Orden invalida");

		when(naviera.tieneElViaje(viaje)).thenReturn(true);
		assertDoesNotThrow(() -> terminalGestionada.importarDesde(ordenMock, terminalDestino));
	}

	@Test
	void proximaFecha() {
		terminalGestionada.agregarNaviera(naviera);
		LocalDateTime fecha_esperada = LocalDateTime.now();
		Terminal terminal = mock(Terminal.class);
		when(naviera.proximaFecha(terminalGestionada, terminal)).thenReturn(fecha_esperada);
		assertEquals(fecha_esperada, terminalGestionada.proximaFecha(terminal));
		verify(naviera).proximaFecha(terminalGestionada, terminal);
	}

	@Test
	void proximaFechaDeUnaTerminalSinViajesDevuelveLaFechaMaxima() {
		LocalDateTime fecha_esperada = LocalDateTime.MAX;
		Terminal terminal = mock(Terminal.class);
		when(naviera.proximaFecha(eq(terminalGestionada), any(Terminal.class))).thenReturn(fecha_esperada);
		assertEquals(fecha_esperada, terminalGestionada.proximaFecha(terminal));
	}

	@Test
	void proximaFechaDeUnaTerminalSinNavierasDevuelveLaFechaMaxima() {
		LocalDateTime fecha_esperada = LocalDateTime.MAX;
		Terminal terminal = mock(Terminal.class);
		when(viaje.proximaFecha(terminalGestionada, terminal)).thenReturn(fecha_esperada);
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
		terminalGestionada.agregarNaviera(naviera);

		when(naviera.tieneElViaje(any(Viaje.class))).thenReturn(true);
		terminalGestionada.importarDesde(importacion, terminalOrigen);
		terminalGestionada.exportarHacia(exportacion, terminalDestino);
		terminalGestionada.importarDesde(otraImport, terminalOrigen);

		List<Container> resultado = terminalGestionada.cargasImportadasEnViaje(viaje);

		Assertions.assertEquals(1, resultado.size());
		Assertions.assertTrue(resultado.contains(contImport));
	}

	@Test
	void cargasExportadasEnViajeDevuelveCargasEsperadas() {
		terminalGestionada.agregarNaviera(naviera);
		Viaje viaje = mock(Viaje.class);
		Container contImport = mock(Container.class);
		Container contExport = mock(Container.class);
		Container contOtroViaje = mock(Container.class);

		Orden importacion = new Orden(cliente, camion, chofer, contImport, viaje);

		Orden exportacion = new Orden(cliente, camion, chofer, contExport, viaje);

		Viaje otroViaje = mock(Viaje.class);
		Orden otraImport = new Orden(cliente, camion, chofer, contOtroViaje, otroViaje);

		terminalGestionada.importarDesde(importacion, terminalOrigen);
		terminalGestionada.exportarHacia(exportacion, terminalDestino);
		terminalGestionada.importarDesde(otraImport, terminalOrigen);

		List<Container> resultado = terminalGestionada.cargasExportadasEnViaje(viaje);

		Assertions.assertEquals(1, resultado.size());
		Assertions.assertTrue(resultado.contains(contExport));
	}

	@Test
	void testCantCargasEnViaje() {
		terminalGestionada.agregarNaviera(naviera);
		Viaje viaje = mock(Viaje.class);
		Orden orden1 = mock(Orden.class);
		Orden orden2 = mock(Orden.class);

		when(orden1.viaje()).thenReturn(viaje);
		when(orden2.viaje()).thenReturn(viaje);

		terminalGestionada.exportarHacia(orden1, terminalDestino);
		terminalGestionada.exportarHacia(orden2, terminalDestino);

		Assertions.assertEquals(2, terminalGestionada.cantCargasEnViaje(viaje));
	}

	@Test
	void ingresarCarga() {
		terminalGestionada.agregarNaviera(naviera);
		terminalGestionada.exportarHacia(ordenMock, terminalDestino);
		Assertions.assertDoesNotThrow(() -> terminalGestionada.retirarCarga(container, camion, chofer));
	}

	@Test
	void retirarUnaCarga() {
		terminalGestionada.agregarNaviera(naviera);
		terminalGestionada.exportarHacia(ordenMock, terminalDestino);
		Assertions.assertDoesNotThrow(() -> terminalGestionada.ingresarCarga(container, camion, chofer));
	}

	@Test
	void personalNoAutorizadoNoPuedorIngresarCarga() {
		terminalGestionada.agregarNaviera(naviera);
		terminalGestionada.exportarHacia(ordenMock, terminalDestino);
		doThrow(new RuntimeException("Chofer no autorizado")).when(ordenMock).verificar(camion, otroChofer, container);
		when(container.isEqual(any(Container.class))).thenReturn(true);
		assertThrows(RuntimeException.class, () -> terminalGestionada.ingresarCarga(container, camion, otroChofer),
				"Chofer no autorizado");

		doThrow(new RuntimeException("Camión no autorizado")).when(ordenMock).verificar(otroCamion, chofer, container);
		assertThrows(RuntimeException.class, () -> terminalGestionada.ingresarCarga(container, otroCamion, chofer),
				"Camión no autorizado");
	}

	@Test
	void personalNoAutorizadoNoPuedorRetirarCarga() {
		terminalGestionada.agregarNaviera(naviera);
		terminalGestionada.importarDesde(ordenMock, terminalOrigen);
		when(container.isEqual(any(Container.class))).thenReturn(true);
		doThrow(new RuntimeException("Chofer no autorizado")).when(ordenMock).verificar(camion, otroChofer, container);
		assertThrows(RuntimeException.class, () -> terminalGestionada.retirarCarga(container, camion, otroChofer),
				"Chofer no autorizado");

		doThrow(new RuntimeException("Camión no autorizado")).when(ordenMock).verificar(otroCamion, chofer, container);

		assertThrows(RuntimeException.class, () -> terminalGestionada.retirarCarga(container, otroCamion, chofer),
				"Camión no autorizado");
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
		terminalGestionada.agregarNaviera(naviera);
		terminalGestionada.exportarHacia(ordenMock, terminalDestino);
		terminalGestionada.facturar(buque);
		verify(ordenMock).facturar(terminalGestionada, buque);
	}

	@Test
	void notificarPartidaYFacturacionEnAvisarPartida() {
		terminalGestionada.agregarNaviera(naviera);
		terminalGestionada.exportarHacia(ordenMock, terminalDestino);
		terminalGestionada.avisarPartida(buque);
		verify(ordenMock).notificarPartida(terminalGestionada, buque);
		verify(ordenMock).facturar(terminalGestionada, buque);
	}

	@Test
	void notificarLlegada() {
		terminalGestionada.agregarNaviera(naviera);
		terminalGestionada.importarDesde(ordenMock, terminalOrigen);
		terminalGestionada.avisarLlegada(buque);
		verify(ordenMock).notificarLlegada(terminalGestionada, buque);
		verify(ordenMock).facturar(terminalGestionada, buque);
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
