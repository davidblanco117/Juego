package Juego.Personajes;

import javax.swing.ImageIcon;
import Juego.Habilidades.*;

public class Reptiliano extends Personaje {

	
	
	public Reptiliano() {
		setAtaque(15);
		setSalud(190);
		setSaludMaxima(190);
		setDefensa(0.10);
		setVelocidad(0.12);
		setEstaVivo(true);
		setRaza("NoHumano");
		inicializarValoresOriginales();
		getHabilidades().add(new Camuflaje());
		getHabilidades().add(new Regenerar());
		getHabilidades().add(new Escupitajo());
		int aux=this.getCantHabilidades();
		for(int i=aux;i<6;i++) {
			getHabilidades().add(new HabilidadVacia());
		}
		this.imagen= new ImageIcon(getClass().getResource("/Imagenes/Reptiliano.jpg"));
		this.setDescripcionPersonaje("Las habilidades del reptiliano le permiten volverse mas fuerte a medida que pasa el tiempo");
		
	}
	

	@Override
	public String toString() {
		return "Reptiliano";
	}
	
	
	
}
