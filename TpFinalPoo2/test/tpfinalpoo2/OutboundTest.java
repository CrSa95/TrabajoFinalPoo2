package tpfinalpoo2;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OutboundTest {
	
	private Outbound estadoOutbound;
	private Buque buqueMock;

	@BeforeEach
	public void setUp() {
		estadoOutbound = new Outbound();
		buqueMock = mock(Buque.class);
	}

	@Test
	void testElEstadoInboundNoPuedeCambiarElEstadoAArrivedSiLaDistanciaADestinoNoEsLaCorrecta() {
		
		when(buqueMock.distanciaHaciaDestino()).thenReturn(60d);
		EstadoGPS cambioDeEstado = estadoOutbound.actualizarGPS(buqueMock);
		Assertions.assertEquals(estadoOutbound, cambioDeEstado);
		
	}
	
}
