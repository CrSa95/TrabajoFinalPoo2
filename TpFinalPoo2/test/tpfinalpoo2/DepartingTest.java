package tpfinalpoo2;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DepartingTest {

	private Departing suject;
	private Buque buqueMock;

	@Test
	void departingPermiteSalirDeLaterminal() {
		suject.salir(buqueMock);
	}

	@BeforeEach
	public void setUp() {
		suject = new Departing();
		buqueMock = mock(Buque.class);
	}
}
