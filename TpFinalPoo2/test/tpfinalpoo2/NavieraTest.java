package tpfinalpoo2;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NavieraTest {

	private Naviera naviera;
	private Buque buque;
	private Buque otroBuque;
	private Viaje viaje;
	private Viaje unViaje;
	private Viaje otroViaje;
	private Circuito circuito;
	private Circuito otroCircuito;
	private AND filtroANDMock;
	private OR filtroORMock;
	private FiltroFechaSalida filtroFechaSalida;
	private FiltroFechaLlegada filtroFechaLlegada;
	private FiltroPuertoDestino filtroPuertoDestino;
	private Terminal terminalOrigen;
	private Terminal terminalDestino;

	@BeforeEach
	public void setUp() {
		naviera = new Naviera();
		buque = mock(Buque.class);
		otroBuque = mock(Buque.class);
		viaje = mock(Viaje.class);
		unViaje = mock(Viaje.class);
		otroViaje = mock(Viaje.class);
		circuito = mock(Circuito.class);
		otroCircuito = mock(Circuito.class);
		filtroANDMock = mock(AND.class);
		filtroORMock = mock(OR.class);
		filtroFechaSalida = mock(FiltroFechaSalida.class);
		filtroFechaLlegada = mock(FiltroFechaLlegada.class);
		filtroPuertoDestino = mock(FiltroPuertoDestino.class);
		terminalOrigen = mock(Terminal.class);
		terminalDestino = mock(Terminal.class);

	}

	@Test
	void testUnaNavieraPuedeAgregarBuques() {
		Assertions.assertTrue(naviera.getBuques().isEmpty());
		naviera.agregarBuque(buque);
		Assertions.assertEquals(1, naviera.getBuques().size());
		naviera.agregarBuque(otroBuque);
		Assertions.assertEquals(2, naviera.getBuques().size());
		Assertions.assertFalse(naviera.getBuques().isEmpty());
	}

	@Test
	void testUnaNavieraPuedeAgregarViajes() {
		Assertions.assertTrue(naviera.getViajes().isEmpty());
		naviera.agregarViaje(viaje);
		Assertions.assertEquals(1, naviera.getViajes().size());
		naviera.agregarViaje(otroViaje);
		Assertions.assertEquals(2, naviera.getViajes().size());
		Assertions.assertFalse(naviera.getViajes().isEmpty());
	}

	@Test
	void testUnaNavieraPuedeAgregarCircuitos() {
		Assertions.assertTrue(naviera.getCircuitos().isEmpty());
		naviera.agregarCircuito(circuito);
		Assertions.assertEquals(1, naviera.getCircuitos().size());
		naviera.agregarCircuito(otroCircuito);
		Assertions.assertEquals(2, naviera.getCircuitos().size());
		Assertions.assertFalse(naviera.getCircuitos().isEmpty());
	}

	@Test
	void testUnaNavieraPuedeBuscarViajes() {

		List<Viaje> viajes = Arrays.asList(viaje, unViaje, otroViaje);

		List<Filtro> filtrosAND = Arrays.asList(filtroFechaSalida, filtroFechaLlegada);

		List<Filtro> filtrosOR = Arrays.asList(filtroPuertoDestino);

		when(filtroANDMock.getFiltros()).thenReturn(filtrosAND);
		when(filtroORMock.getFiltros()).thenReturn(filtrosOR);

		when(filtroANDMock.filtrar(viajes)).thenReturn(Arrays.asList(viaje));
		when(filtroORMock.filtrar(viajes)).thenReturn(Arrays.asList(viaje, unViaje));

		List<Filtro> filtros = Arrays.asList(filtroANDMock, filtroORMock);

		naviera.agregarViaje(viaje);
		naviera.agregarViaje(unViaje);
		naviera.agregarViaje(otroViaje);

		List<Viaje> resultado = naviera.buscarViajesQueCumplan(filtros);

		Assertions.assertTrue(resultado.contains(viaje));
		Assertions.assertTrue(resultado.contains(unViaje));
		Assertions.assertFalse(resultado.contains(otroViaje));
	}
	
	@Test
	void unaNavieraPuedeCalcularElTiempoQueLeLlevaIrDeUnaTerminalAOtra(){
		
		assertThrows(RuntimeException.class, () -> 
		naviera.tiempoDesdeHasta(terminalOrigen,terminalDestino), 
		"No existe circuito entre las terminales");
		
		when(circuito.existeRecorridoEntre(terminalOrigen, terminalDestino)).thenReturn(true);
		when(circuito.tiempoTotalDesdeHasta(terminalOrigen, terminalDestino)).thenReturn(2d);
		
		naviera.agregarCircuito(circuito);
		
		Assertions.assertEquals(2d,naviera.tiempoDesdeHasta(terminalOrigen, terminalDestino));
	}
}
