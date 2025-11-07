package tpfinalpoo2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContainerSuject extends Container{
	public ContainerSuject(String dueño, Double altura, Double ancho, Double largo, Double peso) {
		super(dueño, altura, ancho, largo, peso);
	}

	@Override
	public Double costo() {
		// TODO Auto-generated method stub
		return null;
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
		suject = new ContainerSuject(clienteId, altura, ancho, largo, peso);
	}

	@Test
	void idContainer() {
		assertTrue(suject.id().startsWith(clienteId));
		assertEquals(11, suject.id().length());	
	}
	
	@Test 
	void idUnicoConElmismoDueño() {
		
		Container otroContainer = new ContainerSuject(clienteId, altura, ancho, largo, peso);
		assertNotEquals(suject.id(), otroContainer.id());
	}
	
	
	@Test 
	void metrosCubicos() {
		assertEquals(altura * ancho * largo, suject.metrosCubicos());
	}
	
	@Test 
	void peso() {
		assertEquals(peso, suject.peso());
	}
	
}
