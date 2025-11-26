package tpfinalpoo2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DryTest {
	Dry suject;
	String ownerID = "Dueño";
	Double altura_valida = 1d;
	Double ancho_valido = 1d;
	Double largo_valido = 1d;
	Double peso_valido = 1d;

	@Test
	void elPesoAumentaPorDry() {
		Dry dry1 = new Dry(ownerID, altura_valida, ancho_valido, largo_valido, peso_valido);
		assertEquals(1d, suject.peso());
		suject.agregarProducto(dry1);
		assertEquals(2d, suject.peso());
		suject.agregarProducto(dry1);
		assertEquals(3d, suject.peso());
	}

	@Test
	void noSePermitenDueñosVaciosONulos() {
		assertThrows(RuntimeException.class,
				() -> new Dry(null, altura_valida, ancho_valido, largo_valido, peso_valido));
		assertThrows(RuntimeException.class, () -> new Dry("", altura_valida, ancho_valido, largo_valido, peso_valido));
	}

	@Test
	void noSePermitenValoresNegativos() {
		assertThrows(RuntimeException.class, () -> new Dry(ownerID, -1d, ancho_valido, largo_valido, peso_valido));
		assertThrows(RuntimeException.class, () -> new Dry(ownerID, altura_valida, -1d, largo_valido, peso_valido));
		assertThrows(RuntimeException.class, () -> new Dry(ownerID, altura_valida, ancho_valido, -1d, peso_valido));
		assertThrows(RuntimeException.class, () -> new Dry(ownerID, altura_valida, ancho_valido, largo_valido, -1d));

	}

	@BeforeEach
	void setUp() {
		suject = new Dry(ownerID, 1d, 1d, 1d, 1d);
	}
}
