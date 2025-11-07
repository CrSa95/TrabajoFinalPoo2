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

	@BeforeEach
	void setup() {
		servicio = new AlmacenamientoExcedente(costo_fijo);

		orden = mock(Orden.class);

	}

	@Test
	void noSeCobraAlmacenamientoExcedente() {
		when(orden.fechaRetiro()).thenReturn(ahora);
		assertEquals(0, servicio.costo(orden));
	}

	@Test
	void seCobraAlmacenamientoExcedentePorDia() {
		int numero = 5;
		for (int i = 0; i < numero; i++) {
			LocalDateTime dias = ahora.minusDays(i);
			when(orden.fechaRetiro()).thenReturn(dias);
			assertEquals(costo_fijo * i, servicio.costo(orden));
		}
	}

}
