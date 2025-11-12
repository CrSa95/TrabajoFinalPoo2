package tpfinalpoo2;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArrivedTest {
	
	private Arrived suject;
	private Buque buqueMock;

	@BeforeEach
	public void setUp() {
		suject = new Arrived();
		buqueMock = mock(Buque.class);
	}

	@Test
	void arrivedPuedeIniciarTrabajo() {
		suject.empezarTrabajo(buqueMock);
		verify(buqueMock).cambiarEstado(any());
	}
	
}
