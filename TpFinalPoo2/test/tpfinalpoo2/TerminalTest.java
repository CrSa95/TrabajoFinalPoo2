package tpfinalpoo2;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TerminalTest {
	
	private Terminal terminalGestionada;
	private Terminal otraTerminal;
	private Naviera naviera;
	private Naviera otraNaviera;
	private MenorCantidadTerminalesIntermedias menorCantTerminalesIntermedias;
	private Circuito circuitoUno;
	private Circuito circuitoDos;
	private Circuito circuitoTres;
	private Circuito circuitoCuatro;
	
	@BeforeEach
    public void setUp() {
		terminalGestionada = new Terminal("Buenos Aires");
		otraTerminal = new Terminal("Montevideo");
		naviera = mock(Naviera.class);
		otraNaviera = mock(Naviera.class);
		menorCantTerminalesIntermedias = mock(MenorCantidadTerminalesIntermedias.class);
		circuitoUno = mock(Circuito.class);
		circuitoDos = mock(Circuito.class);
		circuitoTres = mock(Circuito.class);
		circuitoCuatro = mock(Circuito.class);
    }
	
	@Test
	void testLaTerminalConoceSuNombre() {
		Assertions.assertEquals("Buenos Aires", terminalGestionada.getNombre());
	}
	
	@Test
	void testLaTerminalPuedeAgregarNavieras() {
		Assertions.assertEquals(0, terminalGestionada.getNavieras().size());
		terminalGestionada.agregarNaviera(naviera);
		Assertions.assertEquals(1, terminalGestionada.getNavieras().size());
	}
	
	@Test
	void testLaTerminalPuedeCambiarDeEstrategiaParaLaBusquedaDeCircuitos() {
		Assertions.assertEquals(null, terminalGestionada.getEstrategiaDeBusqueda());
		terminalGestionada.setEstrategiaDeBusqueda(menorCantTerminalesIntermedias);
		Assertions.assertTrue(terminalGestionada.getEstrategiaDeBusqueda().equals(menorCantTerminalesIntermedias));
	}
	
	@Test
	void testLaTerminalPuedeBuscarUnMejorCircuito() {
		
		terminalGestionada.setEstrategiaDeBusqueda(menorCantTerminalesIntermedias);
		terminalGestionada.agregarNaviera(naviera);
		terminalGestionada.agregarNaviera(otraNaviera);
		
		List<Circuito> circuitos = Arrays.asList(circuitoUno, circuitoDos); 
		List<Circuito> otrosCircuitos = Arrays.asList(circuitoTres, circuitoCuatro);
		
		when(circuitoUno.parteDesde(terminalGestionada)).thenReturn(true);
		when(circuitoDos.parteDesde(terminalGestionada)).thenReturn(true);
		when(circuitoTres.parteDesde(terminalGestionada)).thenReturn(true);
		when(circuitoCuatro.parteDesde(terminalGestionada)).thenReturn(false);
		
		when(naviera.getCircuitos()).thenReturn(circuitos);
		when(otraNaviera.getCircuitos()).thenReturn(otrosCircuitos);
		
		when(menorCantTerminalesIntermedias.seleccionarMejor(terminalGestionada.filtrarCircuitosDeEstaTerminal(), otraTerminal)).thenReturn(circuitoUno);
		
		Circuito mejorCircuito = terminalGestionada.buscarMejorCircuitoParaLLegarA(otraTerminal);

        Assertions.assertTrue(mejorCircuito.equals(circuitoUno));
	}

}
