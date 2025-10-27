package tpfinalpoo2;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class TestFiltroFechaSalida {

    private FiltroFechaSalida filtroFS;
    private Viaje unViaje;
    private Viaje otroViaje;
    private LocalDate fechaSalida;

    @BeforeEach
    public void setUp() {
    	fechaSalida = LocalDate.of(2025, 12, 1);
    	filtroFS = new FiltroFechaSalida(fechaSalida);

        unViaje = mock(Viaje.class);
        otroViaje = mock(Viaje.class);

        when(unViaje.getFechaSalida()).thenReturn(LocalDate.of(2025, 12, 1));
        when(otroViaje.getFechaSalida()).thenReturn(LocalDate.of(2025, 11, 10));

    }

    @Test
    public void testFiltrar() {
        List<Viaje> viajes = Arrays.asList(unViaje, otroViaje);
        List<Viaje> resultado = filtroFS.filtrar(viajes);

        Assertions.assertEquals(1, resultado.size());
        Assertions.assertEquals(unViaje, resultado.get(0));
    }
}
