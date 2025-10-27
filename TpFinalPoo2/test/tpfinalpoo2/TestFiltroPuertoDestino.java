package tpfinalpoo2;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestFiltroPuertoDestino {

    private FiltroPuertoDestino filtroPD;
    private Viaje unViaje;
    private Viaje otroViaje;
    private OtraTerminal puertoDestino;
    private OtraTerminal otroPuertoDestino;

    @BeforeEach
    public void setUp() {
    	puertoDestino = mock(OtraTerminal.class);
    	otroPuertoDestino = mock(OtraTerminal.class);
    	filtroPD = new FiltroPuertoDestino(puertoDestino);

        unViaje = mock(Viaje.class);
        otroViaje = mock(Viaje.class);
        
        when(puertoDestino.getNombre()).thenReturn("Montevideo");
        when(unViaje.getTerminalDestino()).thenReturn(puertoDestino);
        when(unViaje.getTerminalDestino().getNombre()).thenReturn("Montevideo");
        when(otroViaje.getTerminalDestino()).thenReturn(otroPuertoDestino);
        when(otroViaje.getTerminalDestino().getNombre()).thenReturn("Santos");

    }

    @Test
    public void testFiltrar() {
        List<Viaje> viajes = Arrays.asList(unViaje, otroViaje);
        List<Viaje> resultado = filtroPD.filtrar(viajes);

        Assertions.assertEquals(1, resultado.size());
        Assertions.assertEquals(unViaje, resultado.get(0));
    }
}
