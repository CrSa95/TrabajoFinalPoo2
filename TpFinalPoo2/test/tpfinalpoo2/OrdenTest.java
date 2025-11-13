package tpfinalpoo2;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrdenTest {

	private Orden orden;
	private Camion camionMock;
	private Chofer choferMock;
	private Container cargaMock;
	private Viaje viajeMock;
	private Cliente clienteMock;
	private Servicio servicioMock1;
	private Servicio servicioMock2;
	private Terminal terminalMock;
	private Buque buqueMock;

	private static final String PATENTE_CAMION = "AAA123";
	private static final String DNI_CHOFER = "12345678";
	private static final String ID_CARGA = "CARGA1";

	@BeforeEach
	void setUp() {
		this.camionMock = mock(Camion.class);
		this.choferMock = mock(Chofer.class);
		this.cargaMock = mock(Container.class);
		this.viajeMock = mock(Viaje.class);
		this.clienteMock = mock(Cliente.class);
		this.servicioMock1 = mock(Servicio.class);
		this.servicioMock2 = mock(Servicio.class);
		this.terminalMock = mock(Terminal.class);
		this.buqueMock = mock(Buque.class);

		when(camionMock.patente()).thenReturn(PATENTE_CAMION);
		when(choferMock.dni()).thenReturn(DNI_CHOFER);
		when(cargaMock.id()).thenReturn(ID_CARGA);

		orden = new Orden(clienteMock, camionMock, choferMock, cargaMock, viajeMock);
		when(buqueMock.viaje()).thenReturn(viajeMock);
	}

	private Camion camionConPatente(String patente) {
		Camion c = mock(Camion.class);
		when(c.patente()).thenReturn(patente);
		return c;
	}

	private Chofer choferConDni(String dni) {
		Chofer c = mock(Chofer.class);
		when(c.dni()).thenReturn(dni);
		return c;
	}

	private Container cargaConId(String id) {
		Container c = mock(Container.class);
		when(c.id()).thenReturn(id);
		return c;
	}

	@Test
	void facturacionConsignee() {
		orden.terminalDestino(terminalMock);
		orden.facturar(terminalMock, buqueMock);

		verify(clienteMock).enviar(any(FacturaConsignee.class));
	}

	@Test
	void facturacionShipper() {
		orden.terminalOrigen(terminalMock);
		orden.facturar(terminalMock, buqueMock);

		verify(clienteMock).enviar(any(FacturaShipper.class));
	}

	@Test
	void verificarCorrectoNoLanzaExcepcion() {
		assertDoesNotThrow(() -> orden.verificar(camionMock, choferMock, cargaMock));
	}

	@Test
	void verificarCamionIncorrectoLanzaExcepcion() {
		Camion camionIncorrecto = camionConPatente("ZZZ123");
		assertThrows(RuntimeException.class, () -> orden.verificar(camionIncorrecto, choferMock, cargaMock));
	}

	@Test
	void verificarChoferIncorrectoLanzaExcepcion() {
		Chofer choferIncorrecto = choferConDni("99999999");
		assertThrows(RuntimeException.class, () -> orden.verificar(camionMock, choferIncorrecto, cargaMock));
	}

	@Test
	void verificarCargaIncorrectaNoLanzaExcepcion() {
		Container cargaIncorrecta = cargaConId("TEST9999999");
		assertDoesNotThrow(() -> orden.verificar(camionMock, choferMock, cargaIncorrecta));
	}

	@Test
	void verificarTodoIncorrectoNoLanzaExcepcion() {
		Camion camionIncorrecto = mock(Camion.class);
		Chofer choferIncorrecto = mock(Chofer.class);
		Container cargaIncorrecta = mock(Container.class);

		when(camionIncorrecto.patente()).thenReturn("ZZZ123");
		when(choferIncorrecto.dni()).thenReturn("99999999");
		when(cargaIncorrecta.id()).thenReturn("TEST9999999");

		assertDoesNotThrow(() -> orden.verificar(camionIncorrecto, choferIncorrecto, cargaIncorrecta));
	}

	@Test
	void costoEnServiciosDevuelveValorCorrecto() {
		when(servicioMock1.costo(orden)).thenReturn(100.0);
		when(servicioMock2.costo(orden)).thenReturn(50.0);

		orden.agregar(servicioMock1);
		orden.agregar(servicioMock2);

		assertEquals(150.0, orden.costoEnServicios());
	}

	@Test
	void costoEnServiciosSinServiciosDevuelveCero() {
		assertEquals(0.0, orden.costoEnServicios());
	}

	@Test
	void costoRecorridoDevuelveValorCorrecto() {
		Terminal otraTerminalMock = mock(Terminal.class);
		orden.terminalOrigen(terminalMock);
		orden.terminalDestino(otraTerminalMock);
		
		when(viajeMock.costoEntre(terminalMock, otraTerminalMock)).thenReturn(2000.0);

		assertEquals(2000.0, orden.costoRecorrido());
		verify(viajeMock).costoEntre(terminalMock, otraTerminalMock);
	}

	@Test
	void AgregarAgregaServicioCorrectamente() {
		when(servicioMock1.costo(orden)).thenReturn(100.0);

		assertEquals(0.0, orden.costoEnServicios());

		orden.agregar(servicioMock1);

		assertEquals(100.0, orden.costoEnServicios());
	}

	@Test
	void fechaLlegadaDevuelveFechaLlegadaDeViaje() {
		orden.terminalDestino(terminalMock);

		LocalDateTime fechaLlegadaViaje = LocalDateTime.now().plusDays(3);
		when(viajeMock.fechaLlegada(terminalMock)).thenReturn(fechaLlegadaViaje);

		assertEquals(fechaLlegadaViaje, orden.fechaLlegada());
	}

	@Test
	void fechaSalidaDevuelveFechaSalidaDeViaje() {
		LocalDateTime fechaSalidaViaje = LocalDateTime.now();
		when(viajeMock.getFechaSalida()).thenReturn(fechaSalidaViaje);

		assertEquals(fechaSalidaViaje, orden.fechaSalida());
	}

	@Test
	void fechaRetiroCorrecta() {
		orden.terminalDestino(terminalMock);

		LocalDateTime FechaLlegadaViaje = LocalDateTime.of(2025, 11, 11, 14, 30);
		LocalDateTime retiroEsperado = FechaLlegadaViaje.plusDays(1).withHour(8);

		when(viajeMock.fechaLlegada(terminalMock)).thenReturn(FechaLlegadaViaje);

		LocalDateTime retiro = orden.fechaRetiro();

		assertEquals(retiroEsperado, retiro);
	}

	@Test
	void viajeDevuelveViajeCOrrecto() {
		assertEquals(viajeMock, orden.viaje());
	}

	@Test
	void cargaDevuelveCargaCorrecta() {
		assertEquals(cargaMock, orden.carga());
	}

	@Test
	void clienteRecibeNotificacionDePartida() {
		orden.terminalOrigen(terminalMock);
		orden.notificarPartida(terminalMock, buqueMock);
		verify(clienteMock).notificarPartida(buqueMock);
	}

	@Test
	void clienteRecibeNotificacionDeLlegada() {
		orden.terminalDestino(terminalMock);
		orden.notificarLlegada(terminalMock, buqueMock);
		verify(clienteMock).notificarLlegada(buqueMock);
	}
}
