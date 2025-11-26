package tpfinalpoo2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AlmacenamientoExcedenteTest {
	LocalDateTime ahora = LocalDateTime.now();
	double costo_fijo = 10d;
	AlmacenamientoExcedente servicio;
	Orden orden;

	@Test
	void noSeCobraAlmacenamientoExcedente() {
		when(orden.fechaRetiro()).thenReturn(ahora);
		assertEquals(0, servicio.costo(orden));
	}

	@Test
	void seCobraAlmacenamientoExcedentePorDia() {
		when(orden.fechaRetiro()).thenReturn(ahora);
		assertEquals(costo_fijo * 0, servicio.costo(orden));

		when(orden.fechaRetiro()).thenReturn(ahora.plusDays(1l));
		assertEquals(costo_fijo * -1, servicio.costo(orden));

		when(orden.fechaRetiro()).thenReturn(ahora.plusDays(2l));
		assertEquals(costo_fijo * -2, servicio.costo(orden));

		when(orden.fechaRetiro()).thenReturn(ahora.plusDays(3l));
		assertEquals(costo_fijo * -3, servicio.costo(orden));

		when(orden.fechaRetiro()).thenReturn(ahora.plusDays(4l));
		assertEquals(costo_fijo * -4, servicio.costo(orden));
	}

	@BeforeEach
	void setup() {
		servicio = new AlmacenamientoExcedente(costo_fijo);
		orden = mock(Orden.class);
	}

}
