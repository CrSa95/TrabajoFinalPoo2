package tpfinalpoo2;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WorkingTest {
	
	private Working suject;
	private Buque buqueMock;

	@BeforeEach
	public void setUp() {
		suject = new Working();
		buqueMock = mock(Buque.class);
	}

	@Test 
	void workingPermiteSalida() {
		assertDoesNotThrow(()-> suject.permitirSalida(buqueMock));
	}
	@Test 
	void permitirSalidaModificaEl() {
		suject.permitirSalida(buqueMock);
		verify(buqueMock).cambiarEstado(any());
	}
}
