package tpfinalpoo2;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EstadoGPSTest {
	EstadoGPS suject;

	@BeforeEach
	void setUp() throws Exception {
		suject = spy(EstadoGPS.class);
	}

	@Test
	void empezarTrabajo() {
		assertThrows(RuntimeException.class, () -> suject.empezarTrabajo(any(Buque.class)),
				"No se puede iniciar trabajo actualmente; muy lejos de la terminal");
	}

	@Test
	void permitirSalida() {
		assertThrows(RuntimeException.class, () -> suject.permitirSalida(any(Buque.class)),
				"El buque ya esta en un viaje");
	}

	@Test
	void avisarLlegada() {
		assertThrows(RuntimeException.class, () -> suject.avisarLlegada(any(Buque.class)),
				"El buque no puede avisarLlegada actualmente");
	}

	@Test
	void avisarPartida() {
		assertThrows(RuntimeException.class, () -> suject.avisarPartida(any(Buque.class)),
				"El buque no puede avisar su salida actualmente");
	}

	@Test
	void salir() {
		assertThrows(RuntimeException.class, () -> suject.salir(any(Buque.class)),
				"El buque no puede salir actualmente");
	}

	@Test
	void avanzar() {
		assertDoesNotThrow(() -> suject.avanzar(null));
	}
}
