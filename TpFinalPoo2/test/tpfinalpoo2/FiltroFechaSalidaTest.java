package tpfinalpoo2;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FiltroFechaSalidaTest {

	private FiltroFechaSalida filtroFS;
	private Viaje unViaje;
	private Viaje otroViaje;
	private LocalDateTime fechaSalida;

	@BeforeEach
	public void setUp() {
		fechaSalida = LocalDateTime.of(2025, 12, 1, 0, 0);
		filtroFS = new FiltroFechaSalida(fechaSalida);

		unViaje = mock(Viaje.class);
		otroViaje = mock(Viaje.class);

		when(unViaje.getFechaSalida()).thenReturn(LocalDateTime.of(2025, 12, 1, 0, 0));
		when(otroViaje.getFechaSalida()).thenReturn(LocalDateTime.of(2025, 11, 10, 0, 0));

	}

	@Test
	public void testFiltrar() {
		List<Viaje> viajes = Arrays.asList(unViaje, otroViaje);
		List<Viaje> resultado = filtroFS.filtrar(viajes);

		Assertions.assertEquals(1, resultado.size());
		Assertions.assertEquals(unViaje, resultado.get(0));
	}
}
