package tpfinalpoo2;

import java.util.ArrayList;
import java.util.List;

public class AND implements Filtro{
	
	private List<Filtro> filtros = new ArrayList<>();
	
    public void agregarFiltro(Filtro filtro) {
        this.filtros.add(filtro);
    }

    public List<Viaje> filtrar(List<Viaje> viajes) {
        List<Viaje> resultado = viajes;
        for (Filtro filtro : this.getFiltros()) {
            resultado = filtro.filtrar(resultado);
        }
        return resultado;
    }
    
    public List<Filtro> getFiltros(){
    	return this.filtros;
    }
    
}

