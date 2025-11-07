package tpfinalpoo2;

import java.time.LocalDateTime;
import java.util.Random;

public abstract class Container {
	private Double altura;
	private Double ancho;
	private Double largo;
	private Double peso;
	private Cliente dueño;
	private String id;
	
	public Container(Cliente dueño, 
						Double altura, Double ancho, Double largo, Double peso) {
		this.setAltura(altura);
		this.setAncho(ancho);
		this.setLargo(largo);
		this.setPeso(peso);
		this.setDueño(dueño);
	}
	
	private void generarID() {
		int numeroRandom = (int) (Math.random() * (9999998));
		this.id = this.getDueño().id() + String.format("%07d", numeroRandom);
	}

	private void setDueño(Cliente dueño) {
		if(dueño == null) throw new RuntimeException("Container: Dueño invalido");
		this.dueño = dueño;
	}

	private void setPeso(Double peso) {
		if(peso < 0) throw new RuntimeException("Container: Peso invalido");
		this.peso = peso;
		
	}

	private void setLargo(Double largo) {
		if(largo < 0) throw new RuntimeException("Container: Largo invalido");
		this.largo = peso;
		
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

	public LocalDateTime fechaRetiro() {
		// TODO Auto-generated method stub
		return null;
	}

	public LocalDateTime fechaIngreso() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String id() {
		if(this.id == null) {
			this.generarID();
		}
		return this.id;
		
	}
	
	public Cliente getDueño() {
		return this.dueño;
	}
}
