package tpfinalpoo2;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class PesadoTest {
	double costo_fijo = 10d;
	Pesado servicio = new Pesado(costo_fijo);
	Container container = mock(Container.class);
	@Test
	void seRegistroElPesoDelContainer() {
		assertEquals(costo_fijo, servicio.costo(container));
	}

}
