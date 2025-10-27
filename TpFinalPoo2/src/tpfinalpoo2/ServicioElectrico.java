package tpfinalpoo2;

import java.time.Duration;
import java.time.LocalDateTime;

public class ServicioElectrico extends Servicio{

	public ServicioElectrico(double precio_fijo) {
		super(precio_fijo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double costo(Container container) {
		// TODO Auto-generated method stub
		Duration duracion = Duration.between(container.fechaIngreso(), container.fechaRetiro());
		long horas = duracion.toHours();
		if(horas < 0) return 0;
		return horas * this.precio_fijo;
	}

}
