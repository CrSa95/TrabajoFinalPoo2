package tpfinalpoo2;

import java.util.List;

public class MenorCantidadTerminalesIntermedias implements IStrategyBusquedaCircuito {

	@Override
	public Circuito seleccionarMejor(List<Circuito> circuitos, Terminal terminalDestino) {
		// TODO Auto-generated method stub
		//Se inicializa la variable mejorCircuito con null ya que en caso de  
		//no encontrar ningun circuito se devuelve null. Podriamos devolver un error
		//personalizado para mejorar los test
        Circuito mejorCircuito = null;
        
        //Se inicializa la variable minIntermedias con el mayor valor que puede 
        //representar int, para poder realizar una comparacion de forma mas efectiva
		int minIntermedias = Integer.MAX_VALUE;

        for (Circuito circuito : circuitos) {
        	
        	//Se resta 1 porque el primer tramo no cuenta como intermedia
        	int cantTerminalesIntermedias = circuito.subTramosHasta(terminalDestino).size() - 1; 
            
        	//Este if basicamente intercambia valores en caso de que se cumplan las condiciones
        	//y setea el nuevo circuito que cumple las condiciones
        	//el >= 0 es por si subTramosHasta(terminalDestino).size() = 0
        	//ya que al devovler 0 se interpreta como que la lista de subtramos esta vacia
        	//el = es por si subTramosHasta(terminalDestino).size() = 1, es decir, si el 
        	//primer elemento de la lista de tramos tiene a la terminal destino
        	if (cantTerminalesIntermedias < minIntermedias && cantTerminalesIntermedias >= 0) {
                minIntermedias = cantTerminalesIntermedias;
                mejorCircuito = circuito;
            }
        }
        
        return mejorCircuito;
	}

}

//Esto se agregaria en caso de devolver un circuito nuevo y no el que se evalua
//mejorCircuito = new Circuito();
//subTramosHasta(terminalDestino).forEach(mejorCircuito::agregarTramo);

