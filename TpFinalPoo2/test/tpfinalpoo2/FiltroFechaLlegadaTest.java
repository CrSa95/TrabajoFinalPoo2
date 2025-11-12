package tpfinalpoo2;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FiltroFechaLlegadaTest {

	private FiltroFechaLlegada filtroFL;
	private Viaje unViaje;
	private Viaje otroViaje;
	private LocalDateTime fechaLlegada;

	@BeforeEach
	public void setUp() {
		fechaLlegada = LocalDateTime.of(2025, 11, 1, 0, 0);
		filtroFL = new FiltroFechaLlegada(fechaLlegada);

		unViaje = mock(Viaje.class);
		otroViaje = mock(Viaje.class);

		when(unViaje.getFechaLlegada()).thenReturn(LocalDateTime.of(2025, 11, 1, 0, 0));
		when(otroViaje.getFechaLlegada()).thenReturn(LocalDateTime.of(2025, 11, 10, 0, 0));

	}

	@Test
	public void testFiltrar() {
		List<Viaje> viajes = Arrays.asList(unViaje, otroViaje);
		List<Viaje> resultado = filtroFL.filtrar(viajes);

		Assertions.assertEquals(1, resultado.size());
		Assertions.assertEquals(unViaje, resultado.get(0));
	}
}
