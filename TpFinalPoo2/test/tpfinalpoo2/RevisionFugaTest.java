package tpfinalpoo2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RevisionFugaTest {
	RevisionFuga suject;
	Orden orden;
	Container container;
	double costo_fijo = 50d;

	@BeforeEach
	void setUp() throws Exception {
		orden = mock(Orden.class);
		suject = new RevisionFuga(costo_fijo);

	}

	@Test
	void revisionFuga() {
		assertEquals(costo_fijo, suject.costo(orden));
	}

}
