package tpfinalpoo2;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LavadoTest {
	double monto_fijo = 10;
	Lavado servicio = new Lavado(monto_fijo);
	Container container = mock(Container.class);
	@Test
	void seAplicaPrecioFijo() {
		Assertions.assertEquals(monto_fijo, servicio.costo(container));
	}

}
