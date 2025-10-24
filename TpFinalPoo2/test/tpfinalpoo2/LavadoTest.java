package tpfinalpoo2;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LavadoTest {
	double monto_fijo = 10;
	double monto_adicional = 20;
	Lavado servicio = new Lavado(monto_fijo);
	Container container = mock(Container.class);
	@Test
	void seAplicaPrecioFijo() {
		Assertions.assertEquals(monto_fijo, servicio.costo(container));
	}
	
	@Test 
	void seAplicaPrecioAdicional() {
		when(container.metrosCubicos())
		.thenReturn((double) 71);
		Assertions.assertEquals(monto_fijo + monto_adicional, servicio.costo(container));

	}
}
