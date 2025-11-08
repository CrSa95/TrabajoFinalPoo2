package tpfinalpoo2;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MenorPrecioADestinoTest {

	private MenorPrecioADestino menorPrecioADestino;
	private Naviera unaNaviera;
	private Naviera otraNaviera;
	private Circuito unCircuito;
	private Circuito otroCircuito;
	private TerminalGestionada terminalOrigen;
	private TerminalGestionada terminalDestino;

	@BeforeEach
	public void setUp() {

		menorPrecioADestino = new MenorPrecioADestino();
		unaNaviera = mock(Naviera.class);
		otraNaviera = mock(Naviera.class);
		unCircuito = mock(Circuito.class);
		otroCircuito = mock(Circuito.class);
		terminalOrigen = mock(TerminalGestionada.class);
		terminalDestino = mock(TerminalGestionada.class);

	}

	@Test
	void testPuedeSeleccionarElCircuitoDeMenorDuracionEntreDosTerminales() {

		List<Circuito> circuitosUnaNaviera = Arrays.asList(unCircuito);
		List<Circuito> circuitosOtraNaviera = Arrays.asList(otroCircuito);

		when(unaNaviera.getCircuitos()).thenReturn(circuitosUnaNaviera);
		when(otraNaviera.getCircuitos()).thenReturn(circuitosOtraNaviera);

		when(unCircuito.costoTotalDesdeHasta(terminalOrigen, terminalDestino)).thenReturn(5d);
		when(otroCircuito.costoTotalDesdeHasta(terminalOrigen, terminalDestino)).thenReturn(10d);

		List<Naviera> listaNavieras = Arrays.asList(unaNaviera, otraNaviera);

		Circuito mejorCircuito = menorPrecioADestino.seleccionarMejor(listaNavieras, terminalOrigen, terminalDestino);

		Assertions.assertTrue(mejorCircuito.equals(unCircuito));

		List<Naviera> listaVacia = new ArrayList<>();

		Assertions.assertThrows(Exception.class, () -> {
			menorPrecioADestino.seleccionarMejor(listaVacia, terminalOrigen, terminalDestino);
		});
	}
}
