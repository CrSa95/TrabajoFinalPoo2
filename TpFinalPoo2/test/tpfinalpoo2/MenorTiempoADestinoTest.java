package tpfinalpoo2;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MenorTiempoADestinoTest {
	
	private MenorTiempoADestino menorTiempoADestino;
	private Naviera unaNaviera;
	private Naviera otraNaviera;
	private Circuito unCircuito;
	private Circuito otroCircuito;
	private Terminal terminalOrigen;
	private Terminal terminalDestino;
	
	@BeforeEach
    public void setUp() {
		
		menorTiempoADestino = new MenorTiempoADestino();
		unaNaviera = mock(Naviera.class);
		otraNaviera = mock(Naviera.class);
		unCircuito = mock(Circuito.class);
		otroCircuito = mock(Circuito.class);
		terminalOrigen = mock(Terminal.class);
		terminalDestino = mock(Terminal.class);

    }
	
	@Test
	void testPuedeSeleccionarElCircuitoDeMenorDuracionEntreDosTerminales() {
		
		List<Circuito> circuitosUnaNaviera = Arrays.asList(unCircuito);
		List<Circuito> circuitosOtraNaviera = Arrays.asList(otroCircuito);
		
		when(unaNaviera.getCircuitos()).thenReturn(circuitosUnaNaviera);
		when(otraNaviera.getCircuitos()).thenReturn(circuitosOtraNaviera);
		
		when(unCircuito.tiempoTotalDesdeHasta(terminalOrigen, terminalDestino)).thenReturn(10d);
		when(otroCircuito.tiempoTotalDesdeHasta(terminalOrigen, terminalDestino)).thenReturn(5d);
		
		List<Naviera> listaNavieras = Arrays.asList(unaNaviera, otraNaviera);
		
		Circuito mejorCircuito = menorTiempoADestino.seleccionarMejor(listaNavieras, terminalOrigen, terminalDestino);

        Assertions.assertTrue(mejorCircuito.equals(otroCircuito));
        
        List<Naviera> listaVacia = new ArrayList<>();
        
        Assertions.assertThrows(Exception.class, () -> {menorTiempoADestino.seleccionarMejor(listaVacia, terminalOrigen, terminalDestino);});
	}
}
