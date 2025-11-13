package tpfinalpoo2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FacturaShipperTest {
	Orden orden;
	FacturaShipper suject;
	@BeforeEach
	void setUp() throws Exception {
		orden = mock(Orden.class);
		suject = new FacturaShipper(orden);
	}

	@Test
	void facturaShipperUnicamenteCobraServicios() {
		double costo_servicios = 100d;
		when(orden.costoEnServicios()).thenReturn(costo_servicios);
		assertEquals(costo_servicios, suject.costoTotal());
	}

}
