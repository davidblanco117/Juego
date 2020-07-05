package Juego.Personajes;

import javax.swing.ImageIcon;

import Juego.Habilidades.Construir_N1;
import Juego.Habilidades.Construir_N2;
import Juego.Habilidades.Construir_N3;
import Juego.Habilidades.HabilidadVacia;

public class Ingeniero extends Personaje {

	
	
	public Ingeniero() {
		setAtaque(18);
		setSalud(300);
		setSaludMaxima(30);
		setDefensa(0.3);
		setVelocidad(0.25);
		setEstaVivo(true);
		setRaza("Humano");
		inicializarValoresOriginales();
		getHabilidades().add(new Construir_N1());	
		getHabilidades().add(new Construir_N2());	
		getHabilidades().add(new Construir_N3());	
		int aux=this.getCantHabilidades();
		for(int i=aux;i<6;i++) {
			getHabilidades().add(new HabilidadVacia());
		}
		this.imagen= new ImageIcon(getClass().getResource("/Imagenes/Ingeniero.jpg"));
		this.setDescripcionPersonaje("\"Tu no tienes habilidades, tu creas las habilidades\"");
		
	}
	

	@Override
	public String toString() {
		return "Ingeniero";
	}
	
	
	
}
