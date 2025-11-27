package tpfinalpoo2;

import java.time.Duration;

public class ServicioElectrico extends Servicio {

	public ServicioElectrico(double precio_fijo) {
		super(precio_fijo);
	}

	@Override
	public double costo(Orden orden) {
		if (!orden.carga().aplicaServicioElectrico() ) { return 0; }
		
		Refrigerable refrigerable = (Refrigerable) orden.carga();
		
		Duration duracion = Duration.between(orden.fechaSalida(), orden.fechaRetiro());
		long horas = duracion.toHours();
		if (horas < 0) {
			return 0;
		}
		return horas * refrigerable.consumoKwHora();
	}

}
