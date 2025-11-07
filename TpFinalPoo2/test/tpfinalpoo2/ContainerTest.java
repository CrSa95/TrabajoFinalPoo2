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
		cliente = mock(Cliente.class);
		suject = mock(Container.class, CALLS_REAL_METHODS);
	}

	@Test
	void idContainer() {
		when(suject.getDueño()).thenReturn(cliente);
		when(cliente.id()).thenReturn(clienteId);
		
		
		assertTrue(suject.id().startsWith(cliente.id()));
		assertEquals(11, suject.id().length());	
	}
}
