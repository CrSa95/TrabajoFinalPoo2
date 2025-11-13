package tpfinalpoo2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClienteTest {

	private Cliente cliente;
	private static final String CORREO = "cliente@test.com";

	@BeforeEach
	void setUp() {
		this.cliente = new Cliente(CORREO);
	}

	@Test
	void correoDevuelveElValorCorrecto() {
		Cliente cliente = new Cliente(CORREO);

		String correoCliente = cliente.correo();

		assertEquals(CORREO, correoCliente);
	}

	@Test
	void notificarLlegadaNoHaceNada() {
		cliente.notificarLlegada(mock(Buque.class));
	}

	@Test
	void notificarPartidaNoHaceNada() {
		cliente.notificarPartida(mock(Buque.class));
	}
}
