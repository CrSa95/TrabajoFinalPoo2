package tpfinalpoo2;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ANDTest {
	
	private AND filtroAND;
	private FiltroFechaLlegada filtroFechaLlegada;
	private FiltroFechaSalida filtroFechaSalida;
	private FiltroPuertoDestino filtroPuertoDestino;
	private Naviera naviera;
	private Viaje viaje;
	private Viaje otroViaje;
	
	@BeforeEach
    public void setUp() {
		filtroAND = new AND();
		filtroFechaLlegada = mock(FiltroFechaLlegada.class);
		filtroFechaSalida = mock(FiltroFechaSalida.class);	
		filtroPuertoDestino = mock(FiltroPuertoDestino.class);
		naviera = mock(Naviera.class);
		viaje = mock(Viaje.class);
		otroViaje = mock(Viaje.class);
    }
	
	@Test
	void testSePuedeAgregarUnFiltro() {
		filtroAND.agregarFiltro(filtroFechaLlegada);
		Assertions.assertTrue(filtroAND.getFiltros().contains(filtroFechaLlegada));
	}
	
	@Test
    void testSeAplicanLosFiltrosDeBusqueda() {

		List<Viaje> listaViajes = Arrays.asList(viaje, otroViaje);
		List<Viaje> listaFiltrada = Arrays.asList(viaje);
		
		when(naviera.getViajes()).thenReturn(listaViajes);
		when(filtroFechaSalida.filtrar(listaViajes)).thenReturn(listaFiltrada);
	    when(filtroPuertoDestino.filtrar(listaFiltrada)).thenReturn(listaFiltrada);
		
	    filtroAND.agregarFiltro(filtroFechaSalida);
	    filtroAND.agregarFiltro(filtroPuertoDestino);
	    
		List<Viaje> resultado = filtroAND.filtrar(naviera.getViajes());
		 
		verify(filtroFechaSalida).filtrar(listaViajes);
		verify(filtroPuertoDestino).filtrar(listaFiltrada);
		Assertions.assertEquals(resultado, listaFiltrada);
		Assertions.assertFalse(resultado.contains(otroViaje));
	}
}
