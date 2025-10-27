package tpfinalpoo2;

import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServicioElectricoTest {
	LocalDateTime fechaIngreso = LocalDateTime.now();
	double costo_fijo_kw_h = 10;
	ServicioElectrico servicio;
	Container container;
	
	@BeforeEach
	void setup() {
		servicio = new ServicioElectrico(costo_fijo_kw_h);
		container = mock(Container.class);
		when(container.fechaIngreso()).thenReturn(fechaIngreso);
	}
	@Test
	void consumoElectricoAumentaPorHora() {
		int cant_horas = 5;
		LocalDateTime fechaRetiro = fechaIngreso.plusHours(cant_horas);
		when(container.fechaRetiro()).thenReturn(fechaRetiro);
		
		
		assertEquals(costo_fijo_kw_h * cant_horas, servicio.costo(container));
	}
	
	@Test 
	void consumoElectricoAumentaPorDias() {
		int cant_dias = 5;
		LocalDateTime fechaRetiro = fechaIngreso.plusDays(cant_dias);
		when(container.fechaRetiro()).thenReturn(fechaRetiro);
		
		assertEquals(costo_fijo_kw_h * cant_dias * 24, servicio.costo(container));
	}
	
	@Test 
	void noSeCobraMontosNegativos() {
		LocalDateTime fechaRetiro = fechaIngreso.plusDays(-5);
		when(container.fechaRetiro()).thenReturn(fechaRetiro);
		assertTrue(servicio.costo(container) >= 0);

	}

}
