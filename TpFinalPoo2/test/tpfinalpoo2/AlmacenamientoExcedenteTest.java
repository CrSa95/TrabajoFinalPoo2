package tpfinalpoo2;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AlmacenamientoExcedenteTest {
	LocalDateTime ahora = LocalDateTime.now();
	double costo_fijo = 10d;
	AlmacenamientoExcedente servicio = new AlmacenamientoExcedente(costo_fijo);
	Container container = mock(Container.class);
	@Test
	void noSeCobraAlmacenamientoExcedente() {
		when(container.fechaRetiro())
		.thenReturn(ahora);
		assertEquals(0, servicio.costo(container));
	}
	
	@Test 
	void seCobraAlmacenamientoExcedentePorDia() {
		int numero = 5;
		for (int i = 0; i < numero; i++) {
			LocalDateTime dias = ahora.minusDays(i);
			when(container.fechaRetiro())
			.thenReturn(dias);
			assertEquals(costo_fijo * i, servicio.costo(container));
		}
	}

}
