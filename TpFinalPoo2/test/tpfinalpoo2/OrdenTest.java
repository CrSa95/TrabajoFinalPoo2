package tpfinalpoo2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class OrdenTest {

	private Orden orden;
	private static final LocalDate FECHA_LLEGADA = LocalDate.now().plusDays(7);
	private static final LocalDate FECHA_SALIDA = LocalDate.now();
	private static final Terminal ORIGEN = new Terminal("Origen");
	private static final Terminal DESTINO = new Terminal("Destino");
	private static final Viaje VIAJE_SELECCIONADO = new Viaje();
	private static final Set<Servicio> SERVICIOS_CONTRATADOS = new HashSet<>();
	private static final Container CARGA = new Container();
	private static final Camion CAMION = new Camion("AA 123 AA");
	private static final Chofer CHOFER = new Chofer("12345678");
	private static final Cliente DUENO = new Cliente("José Hernández", "12345678");
	
	@BeforeEach
	void setUp() {
	    this.orden = new Orden(FECHA_LLEGADA, FECHA_SALIDA, ORIGEN, DESTINO,
	            VIAJE_SELECCIONADO, SERVICIOS_CONTRATADOS, CARGA, CAMION, CHOFER, DUENO);
	}
	
	// Verificar()
	
	@Test
	void verificarCorrectoDevuelveTrue() {
		assertTrue(this.orden.verificar(CAMION, CHOFER, CARGA));
	}
	
	@Test
	void verificarCamionDistintoDevuelveFalse() {
	    Camion camionIncorrecto = new Camion("ABC123");
	    
	    assertFalse(orden.verificar(camionIncorrecto, CHOFER, CARGA));
	}
	
	@Test
	void verificarChoferIncorrectoDevuelveFalse() {
		Chofer choferIncorrecto = new Chofer("11111111");
		
		assertFalse(this.orden.verificar(CAMION, choferIncorrecto, CARGA));
	}
	
	@Test
	void verificarCargaIncorrectaDevuelveFalse() {
		fail("Falta añadir identificación a las cargas");
	}
	
	@Test
	void verificarTodoIncorrectoDevuelveFalse() {
	    Camion camionIncorrecto = new Camion("ABC123");
		Chofer choferIncorrecto = new Chofer("11111111");
		Container containerIncorrecto = new Container();
		
		assertFalse(this.orden.verificar(camionIncorrecto, choferIncorrecto, containerIncorrecto));
	}
	
	// costoEnServicios()
	
	@Test
	void devuelveCostoTotalCorrecto() {
		Servicio servicio = mock(Servicio.class);
		
		Mockito.when(servicio.costo(CARGA)).thenReturn(100d);
		
		this.orden.agregarServicio(servicio);
		
		assertEquals(100, this.orden.costoEnServicios());
	}
	
	@Test
	void devuelveCostoTotalCorrectoConMultiplesServicios() {
	    Servicio servicio1 = Mockito.mock(Servicio.class);
	    Servicio servicio2 = Mockito.mock(Servicio.class);
	    Servicio servicio3 = Mockito.mock(Servicio.class);

	    Mockito.when(servicio1.costo(CARGA)).thenReturn(100.0);
	    Mockito.when(servicio2.costo(CARGA)).thenReturn(50.0);
	    Mockito.when(servicio3.costo(CARGA)).thenReturn(150.0);

	    this.orden.agregarServicio(servicio1);
	    this.orden.agregarServicio(servicio2);
		
		assertEquals(350, this.orden.costoEnServicios());
	}
	
	@Test
	void devuelveCeroSiNoHayServicios() {
		assertEquals(0, this.orden.costoEnServicios());
	}
	
	
	// Getters
	
	@Test
	void devuelveFechaLlegadaCorrecta() {
		assertEquals(FECHA_LLEGADA, this.orden.fechaLlegada());
	}
	
	@Test
	void devuelveFechaSalidaCorrecta() {
		assertEquals(FECHA_SALIDA, this.orden.fechaSalida());
	}
	
	@Test
	void devuelveViajeCorrecto() {
		assertEquals(VIAJE_SELECCIONADO, this.orden.viaje());
	}
}


