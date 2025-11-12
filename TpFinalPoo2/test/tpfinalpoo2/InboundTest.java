package tpfinalpoo2;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InboundTest {
	
	private Inbound estadoInbound;
	private Buque buqueMock;

	@BeforeEach
	public void setUp() {
		estadoInbound = new Inbound();
		buqueMock = mock(Buque.class);
	}

	@Test
	void testElEstadoInboundNoPuedeCambiarElEstadoAArrivedSiLaDistanciaADestinoNoEsLaCorrecta() {
		
		when(buqueMock.distanciaHaciaDestino()).thenReturn(10d);
		EstadoGPS cambioDeEstado = estadoInbound.actualizarGPS(buqueMock);
		Assertions.assertEquals(estadoInbound, cambioDeEstado);
		
	}
	
}
