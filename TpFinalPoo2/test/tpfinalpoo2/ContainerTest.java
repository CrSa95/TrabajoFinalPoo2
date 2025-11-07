package tpfinalpoo2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContainerSuject extends Container{
	public ContainerSuject(Cliente dueño, Double altura, Double ancho, Double largo, Double peso) {
		super(dueño, altura, ancho, largo, peso);
	}
}


class ContainerTest {
	String clienteId = "ABCD";
	Double altura = 50d;
	Double ancho = 50d;
	Double largo = 50d;
	Double peso = 50d;
	Container suject;
	Cliente cliente;
	@BeforeEach
	void setUp() throws Exception {
		cliente = mock(clienteId);
		when(cliente.id()).thenReturn(clienteId);
		suject = new ContainerSuject(cliente, altura, ancho, largo, peso);
	}

	@Test
	void idContainer() {
		assertTrue(suject.id().startsWith(cliente.id()));
		assertEquals(11, suject.id().length());	
	}
	
	@Test 
	void idUnicoConElmismoDueño() {
		
		Container otroContainer = new ContainerSuject(cliente, altura, ancho, largo, peso);
		assertNotEquals(suject.id(), otroContainer.id());
	}
	
	
	@Test 
	void metrosCubicos() {
		assertEquals(altura * ancho * largo, suject.metrosCubicos());
	}
	
}
