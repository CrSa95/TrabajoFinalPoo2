package tpfinalpoo2;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReporteMuelleTest {
	
	@Test
	void generarReporteMuelleGeneraReporteEsperado() {
	    TerminalGestionada terminalMock = mock(TerminalGestionada.class);
	    Buque buqueMock = mock(Buque.class);
	    Viaje viajeMock = mock(Viaje.class);

	    when(buqueMock.viaje()).thenReturn(viajeMock);
	    when(buqueMock.nombre()).thenReturn("25 de Mayo");
	    when(viajeMock.fechaLlegada(terminalMock)).thenReturn(LocalDateTime.of(2025, 1, 1, 10, 0));
	    when(viajeMock.getFechaSalida()).thenReturn(LocalDateTime.of(2025, 1, 1, 20, 0));
	    when(terminalMock.cantCargasEnViaje(viajeMock)).thenReturn(10);

	    ReporteMuelle reporte = new ReporteMuelle(terminalMock);

	    when(buqueMock.generar(reporte)).thenCallRealMethod();
	    String resultado = buqueMock.generar(reporte);

	    String esperado = """
		        Nombre del buque: 25 de Mayo
		        Fecha de arribo: 2025-01-01T10:00
		        Fecha de partida: 2025-01-01T20:00
		        Cantidad de contenedores operados: 10
		        """;
	    
	    Assertions.assertEquals(esperado, resultado);
	}


}
