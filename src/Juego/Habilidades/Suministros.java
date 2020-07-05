package Juego.Habilidades;

import javax.swing.ImageIcon;

import Juego.Personajes.Personaje;

public class Suministros extends Habilidad {

	public Suministros() {
		this.imagen = new ImageIcon(getClass().getResource("/Imagenes/Suministros.png"));
		this.setCosto(0);
	}

	@Override
	public boolean activar(Personaje jugador, Personaje rival) {
		if (!comprobarPH(jugador, rival))
			return false;

		jugador.setPuntosHabilidad(Math.max(jugador.getPuntosHabilidad() - this.getCosto() + 1, 0));
		return true;
	}

	@Override
	public String getDescripcionUso() {

		return "Aumenta en 1 tus puntos de habilidad";
	}

	public String toString() {
		return "Suministros";
	}

}
