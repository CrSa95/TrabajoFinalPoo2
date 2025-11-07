package tpfinalpoo2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DryTest {
	Dry suject;
	Cliente cliente;
	@BeforeEach
	void setUp() throws Exception {
		cliente = mock(Cliente.class);
		suject = new Dry(cliente, 1d, 1d, 1d, 1d);
	}

	@Test
	void idContainer() {
		String clienteId = "ABCD";
		when(cliente.id()).thenReturn(clienteId);
		assertTrue(suject.id().startsWith(cliente.id()));
		assertEquals(11, suject.id().length());
		
	}

}
