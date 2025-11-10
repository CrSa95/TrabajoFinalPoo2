package tpfinalpoo2;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DepartingTest {
	
	private Departing estadoDeparting;
	private Buque buqueMock;

	@BeforeEach
	public void setUp() {
		estadoDeparting = new Departing();
		buqueMock = mock(Buque.class);
	}
	
	@Test
	void testElEstadoInboundNoPuedeCambiarElEstadoAArrivedSiLaDistanciaADestinoNoEsLaCorrecta() {
		
		when(buqueMock.distanciaHaciaDestino()).thenReturn(2d);
		EstadoGPS cambioDeEstado = estadoDeparting.actualizarGPS(buqueMock);
		Assertions.assertTrue(cambioDeEstado.getClass().equals(Outbound.class));
		
	}
	
	@Test
	void testElEstadoDepartingPuedeDecirleAlBuqueQueSalga() {
		estadoDeparting.salir(buqueMock);
		verify(buqueMock).siguienteDestino();
	}

}
