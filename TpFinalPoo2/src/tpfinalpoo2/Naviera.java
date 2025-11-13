package tpfinalpoo2;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Naviera {

	private List<Circuito> circuitos = new ArrayList<>();
	private List<Buque> buques = new ArrayList<>();
	private List<Viaje> viajes = new ArrayList<>();

	public void agregarBuque(Buque buque) {
		this.getBuques().add(buque);
	}

	public List<Buque> getBuques() {
		return this.buques;
	}

	public void agregarViaje(Viaje viaje) {
		this.getViajes().add(viaje);
	}

	public List<Viaje> getViajes() {
		return this.viajes;
	}

	public void agregarCircuito(Circuito circuito) {
		this.getCircuitos().add(circuito);
	}

	public List<Circuito> getCircuitos() {
		return this.circuitos;
	}

	public List<Viaje> buscarViajesQueCumplan(List<Filtro> filtros) {
		return filtros.stream().flatMap(filtro -> filtro.filtrar(this.getViajes()).stream()).distinct() // elimina
																										// viajes
																										// duplicados
				.collect(Collectors.toList());
	}
	
	public LocalDateTime proximaFecha(Terminal destino, Terminal origen) {
		Optional<LocalDateTime> proximaFecha = this.buques.stream()
				   .map(b -> b.proximaFecha(destino, origen))
				   .filter(fecha-> !fecha.isEqual(LocalDateTime.MAX))
				   .min((arg0, arg1) -> arg0.compareTo(arg1));
		
		return proximaFecha.orElse(LocalDateTime.MAX);
	}

	public double tiempoDesdeHasta(Terminal terminalOrigen, Terminal terminalDestino) {
		return  this.getCircuitos().stream()
				// 1. Filtrar solo los circuitos que tienen un recorrido entre origen y destino
                .filter(circuito -> circuito.existeRecorridoEntre(terminalOrigen, terminalDestino)) 
                // 2. Mapear cada circuito filtrado a su tiempo total
                .mapToDouble(circuito -> circuito.tiempoTotalDesdeHasta(terminalOrigen, terminalDestino))
                // 3. Encontrar el valor mínimo entre los tiempos obtenidos
                .min()
                // .min() devuelve un OptionalDouble. Si quieres Optional<Double>, puedes usar:
                // .boxed() // Convierte DoubleStream a Stream<Double>
                // .min(Comparator.naturalOrder()) 
                // pero .min() en DoubleStream es más eficiente.
                .stream().boxed().findFirst()// Esto convierte OptionalDouble a Optional<Double>
                .orElseThrow(() -> new IllegalArgumentException("No existe circuito entre las terminales")); 
	}
}
