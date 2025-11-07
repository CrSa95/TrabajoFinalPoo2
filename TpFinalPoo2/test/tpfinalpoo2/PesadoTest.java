package tpfinalpoo2;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class PesadoTest {
	double costo_fijo = 10d;
	Pesado servicio;
	Container container;
	Orden orden;
	
	@BeforeEach
	void setup() {
		servicio = new Pesado(costo_fijo);
		orden = mock(Orden.class);
		container  = mock(Container.class);
	}
	
	@Test
	void seRegistroElPesoDelContainer() {
		when(orden.carga()).thenReturn(container);
		assertEquals(costo_fijo, servicio.costo(orden));
	}

}
