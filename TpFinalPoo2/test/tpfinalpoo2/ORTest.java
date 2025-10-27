package tpfinalpoo2;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ORTest {
	
	private OR filtroOR;
	private FiltroFechaLlegada filtroFechaLlegada;
	private FiltroFechaSalida filtroFechaSalida;
	private FiltroPuertoDestino filtroPuertoDestino;
	private Naviera naviera;
	private Viaje viaje;
	private Viaje otroViaje;
	private Viaje unViaje;
	
	@BeforeEach
    public void setUp() {
		filtroFechaLlegada = mock(FiltroFechaLlegada.class);
		filtroFechaSalida = mock(FiltroFechaSalida.class);	
		filtroPuertoDestino = mock(FiltroPuertoDestino.class);
		naviera = mock(Naviera.class);
		filtroOR = new OR();
		viaje = mock(Viaje.class);
		otroViaje = mock(Viaje.class);
		unViaje = mock(Viaje.class);
    }
	
	@Test
	void testSePuedeAgregarUnFiltro() {
		filtroOR.agregarFiltro(filtroFechaLlegada);
		Assertions.assertTrue(filtroOR.getFiltros().contains(filtroFechaLlegada));
	}
	
	@Test
    void testSeAplicanLosFiltrosDeBusqueda() {

		List<Viaje> listaViajes = Arrays.asList(viaje, otroViaje, unViaje);
	    List<Viaje> filtradosPorFecha = Arrays.asList(viaje);
	    List<Viaje> filtradosPorDestino = Arrays.asList(otroViaje);

	    when(naviera.getViajes()).thenReturn(listaViajes);
	    when(filtroFechaSalida.filtrar(listaViajes)).thenReturn(filtradosPorFecha);
	    when(filtroPuertoDestino.filtrar(listaViajes)).thenReturn(filtradosPorDestino);

	    filtroOR.agregarFiltro(filtroFechaSalida);
	    filtroOR.agregarFiltro(filtroPuertoDestino);

	    List<Viaje> resultado = filtroOR.filtrar(naviera.getViajes());
	    
		verify(filtroFechaSalida).filtrar(listaViajes);
		verify(filtroPuertoDestino).filtrar(listaViajes);
		
		Assertions.assertTrue(resultado.contains(viaje));
	    Assertions.assertTrue(resultado.contains(otroViaje));
	    Assertions.assertFalse(resultado.contains(unViaje));
	}
}
