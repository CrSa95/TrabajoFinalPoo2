package tpfinalpoo2;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReporteAduanaTest {

    @Test
    void generarReporteAduanaGeneraHtmlCompletoEsperado() {
        TerminalGestionada terminalMock = mock(TerminalGestionada.class);
        Buque buqueMock = mock(Buque.class);
        Viaje viajeMock = mock(Viaje.class);

        Container carga1 = mock(Container.class);
        Container carga2 = mock(Container.class);
        when(carga1.tipo()).thenReturn("Refrigerado");
        when(carga1.id()).thenReturn("TEST1111111");
        when(carga2.tipo()).thenReturn("Seco");
        when(carga2.id()).thenReturn("TEST2222222");

        when(buqueMock.viaje()).thenReturn(viajeMock);
        when(buqueMock.nombre()).thenReturn("25 de Mayo");
        when(viajeMock.fechaLlegada(terminalMock)).thenReturn(LocalDateTime.of(2025, 11, 12, 10, 0));
        when(viajeMock.getFechaSalida()).thenReturn(LocalDateTime.of(2025, 11, 12, 20, 0));
        when(terminalMock.cargasEnViaje(viajeMock)).thenReturn(List.of(carga1, carga2));

        ReporteAduana reporte = new ReporteAduana(terminalMock);

        when(buqueMock.generar(reporte)).thenCallRealMethod();
        String resultado = buqueMock.generar(reporte);

        String esperado = """
            <html>
                <body>
                    <p>Nombre del buque: 25 de Mayo</p>
                    <p>Fecha de arribo: 2025-11-12T10:00</p>
                    <p>Fecha de partida: 2025-11-12T20:00</p>
                    <ul>
                        <li>Refrigerado | TEST1111111</li>
                        <li>Seco | TEST2222222</li>
                    </ul>
                </body>
            </html>
            """;

        Assertions.assertEquals(esperado, resultado);
    }
}
