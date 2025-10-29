package tpfinalpoo2;

import java.util.List;

public class MenorTiempoADestino implements IStrategyBusquedaCircuito {

	@Override
	public Circuito seleccionarMejor(List<Circuito> circuitos, Terminal terminalDestino) {
		// TODO Auto-generated method stub
		//Se inicializa la variable mejorCircuito con null ya que en caso de  
		//no encontrar ningun circuito se devuelve null. Podriamos devolver un error
		//personalizado para mejorar los test. 
		Circuito mejorCircuito = null;
		
		//Se inicializa la variable menorTiempo con el mayor valor que puede 
        //representar double, para poder realizar una comparacion de forma mas efectiva
		double menorTiempo = Double.MAX_VALUE;
		
		for(Circuito circuito : circuitos) {
			//Se obtiene el tiempo que lleva recorrer el circuito (Ver en Circuito)
			double menorTiempoActual = circuito.tiempoTotalHasta(terminalDestino);
			
			//Este if basicamente intercambia valores en caso de que se cumplan las condiciones
        	//y setea el nuevo circuito que cumple las condiciones
        	//el > 0 es por si el circuito.tiempoTotalSubtramos devuelve 0 en caso de que
			//no exista un tramo con la terminal destino, lo cual se tiene que evaluar
			//para que no haya confuciones a la hora de intercambiar las variables
			if(menorTiempoActual < menorTiempo && menorTiempoActual > 0) {
				menorTiempo = menorTiempoActual;
				mejorCircuito = circuito;
			}
		}
		
		return mejorCircuito;
	}
}

//Esto se agregaria en caso de devolver un circuito nuevo y no el que se evalua
//mejorCircuito = new Circuito();
//subTramosHasta(terminalDestino).forEach(mejorCircuito::agregarTramo);
