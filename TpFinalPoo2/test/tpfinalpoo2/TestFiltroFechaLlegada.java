package tpfinalpoo2;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class TestFiltroFechaLlegada {

    private FiltroFechaLlegada filtroFL;
    private Viaje unViaje;
    private Viaje otroViaje;
    private LocalDate fechaLlegada;

    @BeforeEach
    public void setUp() {
    	fechaLlegada = LocalDate.of(2025, 11, 1);
        filtroFL = new FiltroFechaLlegada(fechaLlegada);

        unViaje = mock(Viaje.class);
        otroViaje = mock(Viaje.class);

        when(unViaje.getFechaLlegada()).thenReturn(LocalDate.of(2025, 11, 1));
        when(otroViaje.getFechaLlegada()).thenReturn(LocalDate.of(2025, 11, 10));

    }

    @Test
    public void testFiltrar() {
        List<Viaje> viajes = Arrays.asList(unViaje, otroViaje);
        List<Viaje> resultado = filtroFL.filtrar(viajes);

        Assertions.assertEquals(1, resultado.size());
        Assertions.assertEquals(unViaje, resultado.get(0));
    }
}
