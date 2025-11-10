package tpfinalpoo2;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArrivedTest {
	
	private Arrived estadoArrived;
	private Buque buqueMock;

	@BeforeEach
	public void setUp() {
		estadoArrived = new Arrived();
		buqueMock = mock(Buque.class);
	}

	@Test
	void testElEstadoArrivedNoPuedeCambiarElEstadoAWorkingSiElTrabajoNoSeInicio() {
		
		EstadoGPS cambioDeEstado = estadoArrived.actualizarGPS(buqueMock);
		Assertions.assertEquals(estadoArrived, cambioDeEstado);
		
	}
	
}
