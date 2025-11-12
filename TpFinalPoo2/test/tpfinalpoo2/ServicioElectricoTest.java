package tpfinalpoo2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServicioElectricoTest {
	double costo_fijo_kw_h = 10;
	LocalDateTime fechaIngreso = LocalDateTime.now();
	ServicioElectrico servicio;
	Container container;
	Orden orden;

	@BeforeEach
	void setup() {
		servicio = new ServicioElectrico(costo_fijo_kw_h);
		container = mock(Container.class);
		orden = mock(Orden.class);
		when(orden.carga()).thenReturn(container);

	}

	@Test
	void consumoElectricoAumentaPorHora() {
		int cant_horas = 5;
		LocalDateTime fechaRetiro = fechaIngreso.plusHours(cant_horas);
		when(orden.fechaRetiro()).thenReturn(fechaRetiro);
		when(orden.fechaSalida()).thenReturn(fechaIngreso);

		assertEquals(costo_fijo_kw_h * cant_horas, servicio.costo(orden));
	}

	@Test
	void consumoElectricoAumentaPorDias() {
		int cant_dias = 5;
		LocalDateTime fechaRetiro = fechaIngreso.plusDays(cant_dias);
		when(orden.fechaRetiro()).thenReturn(fechaRetiro);
		when(orden.fechaSalida()).thenReturn(fechaIngreso);

		assertEquals(costo_fijo_kw_h * cant_dias * 24, servicio.costo(orden));
	}

	@Test
	void noSeCobraMontosNegativos() {
		LocalDateTime fechaRetiro = fechaIngreso.plusDays(-5);
		when(orden.fechaRetiro()).thenReturn(fechaRetiro);
		when(orden.fechaSalida()).thenReturn(fechaIngreso);
		assertTrue(servicio.costo(orden) >= 0);

	}

}
