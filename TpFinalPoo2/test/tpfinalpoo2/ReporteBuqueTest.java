package tpfinalpoo2;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReporteBuqueTest {

    @Test
    void generarReporteBuqueGeneraHtmlCompletoEsperado() {
        TerminalGestionada terminalMock = mock(TerminalGestionada.class);
        Buque buqueMock = mock(Buque.class);
        Viaje viajeMock = mock(Viaje.class);

        Container cargaImport1 = mock(Container.class);
        Container cargaImport2 = mock(Container.class);
        when(cargaImport1.id()).thenReturn("IMPO1111111");
        when(cargaImport2.id()).thenReturn("IMPO2222222");

        Container cargaExport1 = mock(Container.class);
        Container cargaExport2 = mock(Container.class);
        when(cargaExport1.id()).thenReturn("EXPO1111111");
        when(cargaExport2.id()).thenReturn("EXPO2222222");

        when(buqueMock.viaje()).thenReturn(viajeMock);
        when(terminalMock.cargasImportadasEnViaje(viajeMock))
                .thenReturn(List.of(cargaImport1, cargaImport2));
        when(terminalMock.cargasExportadasEnViaje(viajeMock))
                .thenReturn(List.of(cargaExport1, cargaExport2));

        ReporteBuque reporte = new ReporteBuque(terminalMock);

        when(buqueMock.generar(reporte)).thenCallRealMethod();
        String resultado = buqueMock.generar(reporte);

        String esperado = """
            <reporte>
                <importaciones>
                    <item>IMPO1111111</item>
                    <item>IMPO2222222</item>
                </importaciones>
                <exportaciones>
                    <item>EXPO1111111</item>
                    <item>EXPO2222222</item>
                </exportaciones>
            </reporte>
            """;

        Assertions.assertEquals(esperado, resultado);
    }
}
