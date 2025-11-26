package tpfinalpoo2;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LavadoTest {
	double monto_fijo = 10;
	double monto_adicional = 20;
	Lavado servicio;
	Container container;
	Orden orden;

	@Test
	void seAplicaPrecioAdicional() {
		when(container.metrosCubicos()).thenReturn((double) 71);
		Assertions.assertEquals(monto_fijo + monto_adicional, servicio.costo(orden));

	}

	@Test
	void seAplicaPrecioFijo() {
		Assertions.assertEquals(monto_fijo, servicio.costo(orden));
	}

	@BeforeEach
	void setup() {
		servicio = new Lavado(monto_fijo);
		container = mock(Container.class);
		orden = mock(Orden.class);
		when(orden.carga()).thenReturn(container);
	}
}
