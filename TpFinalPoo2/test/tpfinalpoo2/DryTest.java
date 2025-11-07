package tpfinalpoo2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DryTest {
	Dry suject;
	Cliente cliente;
	@BeforeEach
	void setUp() {
		cliente = mock(Cliente.class);
		
		suject = new Dry(cliente, 1d, 1d, 1d, 1d);
	}
	
	
	@Test
	void elPesoAumentaPorDry() {
		Double total = 4d;
		Dry dry1 = new Dry(cliente, 1d, 1d, 1d, 1d);
		assertEquals(1d, suject.peso());
		suject.agregarProducto(dry1);
		assertEquals(2d, suject.peso());
		suject.agregarProducto(dry1);
		assertEquals(3d, suject.peso());
		
	}
}
