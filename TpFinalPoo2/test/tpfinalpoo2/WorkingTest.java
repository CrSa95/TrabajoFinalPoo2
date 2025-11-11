package tpfinalpoo2;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WorkingTest {
	
	private Working estadoWorking;
	private Buque buqueMock;

	@BeforeEach
	public void setUp() {
		estadoWorking = new Working();
		buqueMock = mock(Buque.class);
	}

	@Test
	void testElEstadoWorkinNoPuedeCambiarElEstadoADepartingSiLaPartidaNoSeInicio() {
		
		EstadoGPS cambioDeEstado = estadoWorking.actualizarGPS(buqueMock);
		Assertions.assertEquals(estadoWorking, cambioDeEstado);
		
	}
	
	@Test
	void testElEstadoWorkinPermiteLaSalida() {
		estadoWorking.permitirSalida(buqueMock);
	}
	
}
