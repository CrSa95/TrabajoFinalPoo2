package tpfinalpoo2;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InboundTest {
	
	Inbound suject;
	Buque buqueMock;
	Terminal terminal;

	@BeforeEach
	public void setUp() {
		suject = new Inbound();
		buqueMock = mock(Buque.class);
		terminal = spy(Terminal.class);
	}

	@Test
	void inboundCambiaEstadoConDistanciaADestinoMenorAIgual0() {
		when(buqueMock.distanciaHaciaDestino()).thenReturn(0d);
		suject.avanzar(buqueMock);
		
		verify(buqueMock).cambiarEstado(any());
	}
	
	@Test 
	void inboundNoCambiaEstadoConDistanciaMayorA1() {
		when(buqueMock.distanciaHaciaDestino()).thenReturn(1d);
		suject.avanzar(buqueMock);
		verify(buqueMock, never()).cambiarEstado(any());

	}
	
	@Test
	void inboundAvisaLlegada() {
		when(buqueMock.terminalDestino()).thenReturn(terminal);
		suject.avisarLlegada(buqueMock);
		
		verify(terminal).avisarLlegada(buqueMock);
	}
	
}
