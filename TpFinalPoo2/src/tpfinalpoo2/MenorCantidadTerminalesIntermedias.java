package tpfinalpoo2;

import java.util.List;

public class MenorCantidadTerminalesIntermedias implements IBusquedaCircuito {

	@Override
	public Circuito seleccionarMejor(List<Naviera> navieras, Terminal terminalOrigen, Terminal terminalDestino) {

		 List<Circuito> todosLosCircuitos = navieras.stream()
			        								.map(Naviera::getCircuitos)
			        								.flatMap(List::stream)
			        								.toList();
		
        Circuito mejorCircuito = null;

		int minIntermedias = Integer.MAX_VALUE;

        for (Circuito circuito : todosLosCircuitos) {
        	
        	int cantTerminalesIntermedias = circuito.terminalesIntermediasDesdeHasta(terminalOrigen, terminalDestino); 

        	if (cantTerminalesIntermedias < minIntermedias && cantTerminalesIntermedias >= 0) {
                minIntermedias = cantTerminalesIntermedias;
                mejorCircuito = circuito;
            }
        }
        
        return mejorCircuito;
	}

}