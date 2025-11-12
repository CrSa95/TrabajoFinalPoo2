package tpfinalpoo2;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InboundTest {
	
	Inbound estadoInbound;
	Buque buqueMock;
	Terminal terminal;

	@BeforeEach
	public void setUp() {
		estadoInbound = new Inbound();
		buqueMock = mock(Buque.class);
		terminal = spy(Terminal.class);
	}

	@Test
	void testElEstadoInboundNoPuedeCambiarElEstadoAArrivedSiLaDistanciaADestinoNoEsLaCorrecta() {
		when(buqueMock.distanciaHaciaDestino()).thenReturn(10d);
		EstadoGPS cambioDeEstado = estadoInbound.actualizarGPS(buqueMock);
		Assertions.assertEquals(estadoInbound, cambioDeEstado);
	}
	
	@Test
	void inboundAvisaLlegada() {
		when(buqueMock.terminalDestino()).thenReturn(terminal);
		estadoInbound.avisarLlegada(buqueMock);
		
		verify(terminal).avisarLlegada(buqueMock);
	}
	
}
