package tpfinalpoo2;

import java.time.LocalDate;
import java.time.Period;

public class AlmacenamientoExcedente extends Servicio {
	public AlmacenamientoExcedente(double precio_fijo) {
		super(precio_fijo);
	}

	@Override
	public double costo(Orden orden) {
		Period periodo = Period.between(orden.fechaRetiro().toLocalDate(), LocalDate.now());
		return precio_fijo * periodo.getDays();
	}

}
