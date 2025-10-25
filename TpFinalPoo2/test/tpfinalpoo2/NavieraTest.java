package tpfinalpoo2;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NavieraTest {
	
	Naviera naviera = new Naviera();
	Buque buque = mock(Buque.class);
	Buque otroBuque = mock(Buque.class);
	Viaje viaje = mock(Viaje.class);
	Viaje otroViaje = mock(Viaje.class);
	Circuito circuito = mock(Circuito.class);
	Circuito otroCircuito = mock(Circuito.class);
	
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

