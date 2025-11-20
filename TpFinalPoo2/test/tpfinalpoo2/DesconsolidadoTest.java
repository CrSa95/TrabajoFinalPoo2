package tpfinalpoo2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DesconsolidadoTest {
	Container container;
	Desconsolidado suject;
	Orden orden;
	double precio_fijo = 54d;

	@BeforeEach
	void setUp() throws Exception {
		suject = new Desconsolidado(precio_fijo);
		orden = mock(Orden.class);
	}

	@Test
	void desconsolidadoCosto() {
		container = new Dry("test", 5d, 5d, 5d, 5d);
		when(orden.carga()).thenReturn(container);
		assertEquals(precio_fijo, suject.costo(orden));
	}
	
	@Test
	void elCostoDeDesconsolidarUnContainerNormalEsCero() {
		container = new Tanque("test", 5d, 5d, 5d, 5d);
		when(orden.carga()).thenReturn(container);
		assertEquals(0, suject.costo(orden));
	}
	
	

}
