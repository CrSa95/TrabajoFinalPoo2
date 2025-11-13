package tpfinalpoo2;

import java.time.Duration;
import java.time.LocalDateTime;

public class AlmacenamientoExcedente extends Servicio {
	public AlmacenamientoExcedente(double precio_fijo) {
		super(precio_fijo);
	}

	@Override
	public double costo(Orden orden) {
		Duration periodo = Duration.between(orden.fechaRetiro(), LocalDateTime.now());
		return precio_fijo * periodo.toDays();
	}

}
