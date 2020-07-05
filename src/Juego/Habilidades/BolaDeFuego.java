package Juego.Habilidades;

import javax.swing.ImageIcon;

import Juego.Personajes.Personaje;

public class BolaDeFuego extends Habilidad {

	private double daño = 35;

	public BolaDeFuego() {
		this.imagen = new ImageIcon(getClass().getResource("/Imagenes/BolaDeFuegoVerde.png"));
		this.setCosto(2);
	}

	@Override
	public boolean activar(Personaje jugador, Personaje rival) {
		if (!comprobarPH(jugador, rival))
			return false;
		rival.serAtacado(daño);
		jugador.setPuntosHabilidad((jugador.getPuntosHabilidad() - this.getCosto() < 0) ? 0
				: jugador.getPuntosHabilidad() - this.getCosto());
		return true;
	}

	@Override
	public String getDescripcionUso() {

		return "Lanza una bola de fuego que inflige " + +daño + " puntos de daño al oponente";
	}

	public String toString() {
		return "Bola de Fuego";
	}

}
