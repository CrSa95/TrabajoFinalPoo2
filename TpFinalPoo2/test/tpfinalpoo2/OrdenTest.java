package tpfinalpoo2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    private TerminalGestionada destinoMock;
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
        this.destinoMock = mock(TerminalGestionada.class);
        this.buqueMock = mock(Buque.class);

        when(camionMock.patente()).thenReturn(PATENTE_CAMION);
        when(choferMock.dni()).thenReturn(DNI_CHOFER);
        when(cargaMock.id()).thenReturn(ID_CARGA);

        orden = new Orden(
        	clienteMock,
            camionMock,
            choferMock,
            cargaMock,
            viajeMock
        );
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
    void verificarCorrectoDevuelveTrue() {
        assertDoesNotThrow(() -> orden.verificar(camionMock, choferMock, cargaMock));
    }

    @Test
    void verificarCamionIncorrectoDevuelveFalse() {
        Camion camionIncorrecto = camionConPatente("ZZZ123");
        assertThrows(RuntimeException.class, () -> orden.verificar(camionIncorrecto, choferMock, cargaMock));
    }

    @Test
    void verificarChoferIncorrectoDevuelveFalse() {
        Chofer choferIncorrecto = choferConDni("99999999");
        assertThrows(RuntimeException.class, () -> orden.verificar(camionMock, choferIncorrecto, cargaMock));
    }

    @Test
    void verificarCargaIncorrectaDevuelveFalse() {
        Container cargaIncorrecta = cargaConId("TEST9999999");
        assertThrows(RuntimeException.class, () -> orden.verificar(camionMock, choferMock, cargaIncorrecta));
    }

    @Test
    void verificarTodoIncorrectoDevuelveFalse() {
        Camion camionIncorrecto = mock(Camion.class);
        Chofer choferIncorrecto = mock(Chofer.class);
        Container cargaIncorrecta = mock(Container.class);
        
        when(camionIncorrecto.patente()).thenReturn("ZZZ123");
        when(choferIncorrecto.dni()).thenReturn("99999999");
        when(cargaIncorrecta.id()).thenReturn("TEST9999999");
        
        
        assertThrows(RuntimeException.class, () -> orden.verificar(camionIncorrecto, choferIncorrecto, cargaIncorrecta));
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
        when(viajeMock.costo()).thenReturn(2000.0);

        assertEquals(2000.0, orden.costoRecorrido());
        verify(viajeMock).costo();
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
    	orden.terminalDestino(destinoMock);
    	
        LocalDateTime fechaLlegadaViaje = LocalDateTime.now().plusDays(3);
        when(viajeMock.fechaLlegada(destinoMock)).thenReturn(fechaLlegadaViaje);

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
    	orden.terminalDestino(destinoMock);
    	
        LocalDateTime FechaLlegadaViaje = LocalDateTime.of(2025, 11, 11, 14, 30);
        LocalDateTime retiroEsperado = FechaLlegadaViaje.plusDays(1).withHour(8);
        
        when(viajeMock.fechaLlegada(destinoMock)).thenReturn(FechaLlegadaViaje);

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
        orden.notificarPartida(buqueMock);
        
        verify(clienteMock).notificarPartida(buqueMock);
    }

    @Test
    void clienteRecibeNotificacionDeLlegada() {
        orden.notificarLlegada(buqueMock);
        
        verify(clienteMock).notificarLlegada(buqueMock);
    }
}
