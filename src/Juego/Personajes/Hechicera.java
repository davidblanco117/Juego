package Juego.Personajes;

import javax.swing.ImageIcon;

import Juego.Habilidades.Atrapasueños;
import Juego.Habilidades.BolaDeFuego;
import Juego.Habilidades.Espejo;
import Juego.Habilidades.HabilidadVacia;
import Juego.Habilidades.Sanacion;

public class Hechicera extends Personaje {

	public Hechicera() {
		setAtaque(19);
		setSalud(240);
		setSaludMaxima(240);
		setDefensa(0.23);
		setVelocidad(0.21);
		setEstaVivo(true);
		setRaza("NoHumano");
		inicializarValoresOriginales();
		getHabilidades().add(new BolaDeFuego());
		getHabilidades().add(new Sanacion());
		getHabilidades().add(new Atrapasueños());
		getHabilidades().add(new Espejo());
		int aux = this.getCantHabilidades();
		for (int i = aux; i < 6; i++) {
			getHabilidades().add(new HabilidadVacia());
		}
		this.imagen = new ImageIcon(getClass().getResource("/Imagenes/hechicera.jpg"));
		this.setDescripcionPersonaje("La hechicera posee gran variedad de hechizos para distintas situaciones");

	}

	@Override
	public String toString() {
		return "Hechicera";
	}
}
