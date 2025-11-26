package tpfinalpoo2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PesadoTest {
	double costo_fijo = 10d;
	Pesado servicio;
	Container container;
	Orden orden;

	@Test
	void seRegistroElPesoDelContainer() {
		when(orden.carga()).thenReturn(container);
		assertEquals(costo_fijo, servicio.costo(orden));
	}

	@BeforeEach
	void setup() {
		servicio = new Pesado(costo_fijo);
		orden = mock(Orden.class);
		container = mock(Container.class);
	}

}
