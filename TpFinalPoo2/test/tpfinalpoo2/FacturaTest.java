package tpfinalpoo2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FacturaTest {
	Factura suject;
	ArrayList<Servicio> servicios;
	Container container;
	Orden orden;
	@BeforeEach
	void setUp() throws Exception {
		orden = mock(Orden.class);
	}

	@Test
	void factuaCostoRecorrido() {
		Double costo_recorrido = 50d;
		when(orden.costoRecorrido()).thenReturn(costo_recorrido);
		when(orden.costoEnServicios()).thenReturn(0d);
		suject = new Factura(orden);
		assertEquals(costo_recorrido, suject.costoTotal());
	}
	
	@Test
	void facturaCostoServicios() {
		Double costo_servicios = 500d;
		when(orden.costoRecorrido()).thenReturn(0d);
		when(orden.costoEnServicios()).thenReturn(costo_servicios);
		suject = new Factura(orden);

		assertEquals(costo_servicios, suject.costoTotal());
	}
	
	
	@Test 
	void facturacionTotal() {
		Double costo_recorrido = 50d;
		Double costo_servicios = 500d;
		when(orden.costoEnServicios()).thenReturn(costo_servicios);
		when(orden.costoRecorrido()).thenReturn(costo_recorrido);
		suject = new Factura(orden);
		assertEquals(costo_recorrido + costo_servicios, suject.costoTotal());
	}
	

}
