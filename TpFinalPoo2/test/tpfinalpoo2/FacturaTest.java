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
	Double costo_recorrido = 50d;
	LocalDate fecha_facturacion;
	Container container;
	Orden orden;
	@BeforeEach
	void setUp() throws Exception {
	
	}

	@Test
	void facturaSinServiciosSoloSeSumaElCostoRecorrido() {
		servicios = new ArrayList<Servicio>();

		suject = new Factura(fecha_facturacion, servicios, 50d, orden);
		assertEquals(costo_recorrido, suject.costoTotal());
	}
	
	@Test 
	void facturaConServiciosSeSumaElCostoDeLosServicios() {
		servicios = new ArrayList<Servicio>();
		Servicio servicioMock = mock(Servicio.class);
		Double costo_servicio = 50d;
		servicios.add(servicioMock);
		servicios.add(servicioMock);
		servicios.add(servicioMock);
		servicios.add(servicioMock);
		
		when(servicioMock.costo(orden)).thenReturn(costo_servicio);
		
		suject = new Factura(fecha_facturacion, servicios, 50d, orden);
		assertEquals(costo_recorrido + (costo_servicio * 4), suject.costoTotal());
	}

}
