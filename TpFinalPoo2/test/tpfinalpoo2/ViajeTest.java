package tpfinalpoo2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ViajeTest {
	Viaje suject;
	Circuito circuito;
	ArrayList<Tramo> tramos;
	LocalDate fechaSalida = LocalDate.now();
	@BeforeEach
	void setUp() throws Exception {
		circuito = mock(Circuito.class);
		suject = new Viaje(circuito, fechaSalida);
		
	}

	@Test
	void elCostoEsElMismoQueElCostoDelCircuito() {
		assertEquals(0d, suject.costo());
		when(circuito.costoCircuito()).thenReturn(5d);
		assertEquals(5d, suject.costo());
	}
	
	
	@Test
	void fechaLLegadaAumentaConLaDuracionDelCircuito() {
		when(circuito.tiempoTotal()).thenReturn(0d);
		assertEquals(fechaSalida, suject.getFechaLlegada());
		when(circuito.tiempoTotal()).thenReturn(5d);
		assertEquals(fechaSalida.plusDays(5), suject.getFechaLlegada());
	}

}
