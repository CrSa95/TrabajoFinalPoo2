package tpfinalpoo2;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AlmacenamientoExcedenteTest {
	LocalDateTime ahora = LocalDateTime.now();
	double costo_fijo = 10d;
	AlmacenamientoExcedente servicio;
	Container container;
	Orden orden;
	
	@BeforeEach
	void setup() {
		servicio = new AlmacenamientoExcedente(costo_fijo);
		container = mock(Container.class);
		orden = mock(Orden.class);
		when(orden.carga()).thenReturn(container);
	}
	@Test
	void noSeCobraAlmacenamientoExcedente() {
		when(container.fechaRetiro()).thenReturn(ahora);
		assertEquals(0, servicio.costo(orden));
	}
	
	@Test 
	void seCobraAlmacenamientoExcedentePorDia() {
		int numero = 5;
		for (int i = 0; i < numero; i++) {
			LocalDateTime dias = ahora.minusDays(i);
			when(container.fechaRetiro()).thenReturn(dias);
			assertEquals(costo_fijo * i, servicio.costo(orden));
		}
	}

}
