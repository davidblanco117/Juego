package Juego.Habilidades;

import javax.swing.ImageIcon;

import Juego.Personajes.Personaje;

public class Rafaga extends Habilidad {

	private double da�oMinimo = 8;
	private double da�oMaximo = 14;
	private double da�o = 0;

	public Rafaga() {
		this.imagen = new ImageIcon(getClass().getResource("/Imagenes/Revolver.png"));
		this.setCosto(4);
	}

	@Override
	public boolean activar(Personaje jugador, Personaje rival) {
		if(!comprobarPH(jugador, rival))
			return false;
		for (int i = 0; i < 5; i++) {
			da�o = da�oMinimo + Math.random() * (da�oMaximo - da�oMinimo);
			rival.serAtacado(da�o);
		}
		jugador.setPuntosHabilidad((jugador.getPuntosHabilidad()-this.getCosto()<0)?0:jugador.getPuntosHabilidad()-this.getCosto());
		return true;
	}

	public String toString() {
		return "Rafaga";
	}

	@Override
	public String getDescripcionUso() {
		return "Realiza 5 disparos con da�o variable entre  " + +da�oMinimo + " y " + da�oMaximo;
	}

}
