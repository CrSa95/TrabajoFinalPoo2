package tpfinalpoo2;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DepartingTest {
	
	private Departing suject;
	private Buque buqueMock;

	@BeforeEach
	public void setUp() {
		suject = new Departing();
		buqueMock = mock(Buque.class);
	}
	
	@Test 
	void departingPermiteSalirDeLaterminal() {
		suject.salir(buqueMock);
	}
}
