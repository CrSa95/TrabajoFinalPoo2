package tpfinalpoo2;

import static org.mockito.ArgumentMatchers.contains;

import java.time.LocalDateTime;
import java.util.Random;

public abstract class Container {
	private Double altura;
	private Double ancho;
	private Double largo;
	protected Double peso;
	private String id;
	
	public Container(String ownerID, 
						Double altura, Double ancho, Double largo, Double peso) {
		this.setAltura(altura);
		this.setAncho(ancho);
		this.setLargo(largo);
		this.setPeso(peso);
		this.setOwner(ownerID);
	}
	
	private void setOwner(String ownerId) {
		if(ownerId == null ||ownerId.isEmpty() || ownerId.isBlank()) throw new RuntimeException("Container: Dueño invalido");
		int numeroRandom = (int) (Math.random() * (9999998));
		this.id = ownerId.concat(String.format("%07d", numeroRandom));
	}

	private void setPeso(Double peso) {
		if(peso < 0) throw new RuntimeException("Container: Peso invalido");
		this.peso = peso;
		
	}

	private void setLargo(Double largo) {
		if(largo < 0) throw new RuntimeException("Container: Largo invalido");
		this.largo = largo;
		
	}

	private void setAncho(Double ancho) {
		if(ancho < 0) throw new RuntimeException("Container: Ancho invalido");
		this.ancho = ancho;
	}

	private void setAltura(Double altura) {
		if(altura < 0) throw new RuntimeException("Container: Altura invalida");
		this.altura = altura;
	}
	

	public double metrosCubicos() {
		return (altura * ancho * largo);
	}


	
	public String id() {
		return this.id;
		
	}

	public Double peso() {
		return this.peso;
	}

	public boolean isEqual(Container container) {
		// TODO Auto-generated method stub
		return this.id().equals(container.id());
	}
}
