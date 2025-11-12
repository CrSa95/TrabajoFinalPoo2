package tpfinalpoo2;

import java.time.LocalDateTime;
import java.time.Duration;

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
