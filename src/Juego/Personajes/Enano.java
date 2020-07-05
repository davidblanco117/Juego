package Juego.Personajes;

import javax.swing.ImageIcon;

import Juego.Habilidades.HabilidadVacia;

public class Enano extends Personaje{

	
	public Enano() {
		setAtaque(15);
		setSalud(330);
		setSaludMaxima(330);
		setDefensa(0.23);
		setVelocidad(0.15);
		setEstaVivo(true);
		setRaza("Humano");
		inicializarValoresOriginales();
		int aux=this.getCantHabilidades();
		for(int i=aux;i<6;i++) {
			getHabilidades().add(new HabilidadVacia());
		}
		this.imagen= new ImageIcon(getClass().getResource("/Imagenes/enano.jpg"));
		this.setDescripcionPersonaje("No me elijas para pelear, no ves q estoy chiquito? :(");
	}
	

	@Override
	public String toString() {
		return "Enano";
	}
	
	
	
	
	
	
}
