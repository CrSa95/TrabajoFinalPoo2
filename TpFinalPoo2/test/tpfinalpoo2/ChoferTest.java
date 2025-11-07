package tpfinalpoo2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChoferTest {

	private Chofer chofer;
	private static final String DNI = "12345678";
	
	@BeforeEach
	void setUp() {
		this.chofer = new Chofer(DNI);
	}
	
	@Test
	void DevuelveElDniCorrecto() {
		assertEquals(DNI, chofer.dni());
	}
}
