package tpfinalpoo2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DryTest {
	Dry suject;

	@BeforeEach
	void setUp() {
		suject = new Dry("Dueño", 1d, 1d, 1d, 1d);
	}

	@Test
	void elPesoAumentaPorDry() {
		Dry dry1 = new Dry("Dueño", 1d, 1d, 1d, 1d);
		assertEquals(1d, suject.peso());
		suject.agregarProducto(dry1);
		assertEquals(2d, suject.peso());
		suject.agregarProducto(dry1);
		assertEquals(3d, suject.peso());
	}
}
