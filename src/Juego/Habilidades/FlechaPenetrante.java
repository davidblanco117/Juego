package Juego.Habilidades;

import javax.swing.ImageIcon;

import Juego.Personajes.Personaje;

public class FlechaPenetrante extends Habilidad {

	private double probabilidad = 0;

	public FlechaPenetrante() {
		this.imagen = new ImageIcon(getClass().getResource("/Imagenes/FlechaPenetrante.jpg"));
		this.setCosto(3);
	}

	@Override
	public boolean activar(Personaje jugador, Personaje rival) {
		if(!comprobarPH(jugador, rival))
			return false;
		double daņoPenetrante = jugador.getProbDaņoPenetrante();
		daņoPenetrante = Math.min(0.6, daņoPenetrante + 0.15);
		probabilidad = daņoPenetrante;
		jugador.setProbDaņoPenetrante(daņoPenetrante);
		jugador.setPuntosHabilidad((jugador.getPuntosHabilidad()-this.getCosto()<0)?0:jugador.getPuntosHabilidad()-this.getCosto());
		return true;
	}

	@Override
	public String getDescripcionUso() {

		return "Aumenta en un 15% la probabilidad de lanzar una" + " flecha que ignora la armadura rival. Max 60%. Actual: "
				+ probabilidad * 100 + "%";
	}

	public String toString() {
		return "Flecha Penetrante";
	}

}
