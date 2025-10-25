package tpfinalpoo2;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NavieraTest {
	
	private Naviera naviera;
	private Buque buque;
	private Buque otroBuque;
	private Viaje viaje;
	private Viaje otroViaje;
	private Circuito circuito;
	private Circuito otroCircuito;
	
	@BeforeEach
    public void setUp() {
		naviera = new Naviera();
		buque = mock(Buque.class);
		otroBuque = mock(Buque.class);
		viaje = mock(Viaje.class);
		otroViaje = mock(Viaje.class);
		circuito = mock(Circuito.class);
		otroCircuito = mock(Circuito.class);
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
		
		AND filtroANDMock = mock(AND.class);
		
		naviera.agregarViaje(viaje);
		naviera.agregarViaje(otroViaje);
		naviera.buscarViajes(filtroANDMock);
		
		verify(filtroANDMock).filtrar(naviera.getViajes());
	}
}

