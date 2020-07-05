package Juego.Personajes;

import javax.swing.ImageIcon;

import Juego.Habilidades.Castigo;
import Juego.Habilidades.HabilidadVacia;
import Juego.Habilidades.Pacto;

public class Demonio extends Personaje {

	
	
	public Demonio() {
		setAtaque(18);
		setSalud(300);
		setSaludMaxima(300);
		setDefensa(0.20);
		setVelocidad(0.18);
		setEstaVivo(true);
		setRaza("NoHumano");
		inicializarValoresOriginales();
		getHabilidades().add(new Castigo());
		getHabilidades().add(new Pacto());
		int aux=this.getCantHabilidades();
		for(int i=aux;i<6;i++) {
			getHabilidades().add(new HabilidadVacia());
		}
		this.imagen= new ImageIcon(getClass().getResource("/Imagenes/Demonio.jpg"));
		this.setDescripcionPersonaje("El infierno no está tan mal, al menos no estamos en La Matanza");
		
	}
	

	@Override
	public String toString() {
		return "Demonio";
	}
	
	
	
}