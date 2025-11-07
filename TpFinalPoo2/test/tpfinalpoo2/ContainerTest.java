package tpfinalpoo2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContainerTest {
	String clienteId = "ABCD";
	Container suject;
	Cliente cliente;
	@BeforeEach
	void setUp() throws Exception {
		cliente = new Cliente(clienteId);
		suject = mock(Container.class, CALLS_REAL_METHODS);
		when(suject.getDueño()).thenReturn(cliente);
	}

	@Test
	void idContainer() {
		assertTrue(suject.id().startsWith(cliente.id()));
		assertEquals(11, suject.id().length());	
	}
	
	@Test 
	void idUnicoConElmismoDueño() {
		Container otroContainer = mock(Container.class, CALLS_REAL_METHODS);
		when(otroContainer.getDueño()).thenReturn(cliente);
		assertNotEquals(suject.id(), otroContainer.id());
		
	}
}
