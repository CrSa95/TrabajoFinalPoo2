package tpfinalpoo2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FacturaConsigneeTest {
	Orden orden;
	FacturaConsignee suject;
	@BeforeEach
	void setUp() throws Exception {
		orden = mock(Orden.class);
		suject = new FacturaConsignee(orden);
	}

	@Test
	void facturaConsigneeFacturaServiciosYCostoRecorrdio() {
		double costo_servicios = 100d;
		double costo_recorrido = 100d;
		when(orden.costoEnServicios()).thenReturn(costo_servicios);
		when(orden.costoRecorrido()).thenReturn(costo_recorrido);
		assertEquals(costo_servicios + costo_recorrido, suject.costoTotal());
	}

}
