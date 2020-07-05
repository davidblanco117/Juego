package Juego.Personajes;

import javax.swing.ImageIcon;

import Juego.Habilidades.Contraataque;
import Juego.Habilidades.Entrenamiento;
import Juego.Habilidades.EspadaEncantada;
import Juego.Habilidades.Estandarte;
import Juego.Habilidades.HabilidadVacia;

public class Guerrero extends Personaje {

	
	
	public Guerrero() {
		setAtaque(18);
		setSalud(300);
		setSaludMaxima(30);
		setDefensa(0.3);
		setVelocidad(0.25);
		setEstaVivo(true);
		setRaza("Humano");
		inicializarValoresOriginales();
		getHabilidades().add(new Estandarte());
		getHabilidades().add(new Contraataque());
		getHabilidades().add(new EspadaEncantada());
		getHabilidades().add(new Entrenamiento());
		int aux=this.getCantHabilidades();
		for(int i=aux;i<6;i++) {
			getHabilidades().add(new HabilidadVacia());
		}
		this.imagen= new ImageIcon(getClass().getResource("/Imagenes/guerrero.jpg"));
		this.setDescripcionPersonaje("Se supone que aca va una descripcion copada sobre los beneficios de elegir a guerrero, peeeero...");
		
	}
	

	@Override
	public String toString() {
		return "Guerrero";
	}
	
	
	
}
