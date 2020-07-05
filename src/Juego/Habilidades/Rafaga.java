package Juego.Habilidades;

import javax.swing.ImageIcon;

import Juego.Personajes.Personaje;

public class Rafaga extends Habilidad {

	private double dañoMinimo = 8;
	private double dañoMaximo = 14;
	private double daño = 0;

	public Rafaga() {
		this.imagen = new ImageIcon(getClass().getResource("/Imagenes/Revolver.png"));
		this.setCosto(4);
	}

	@Override
	public boolean activar(Personaje jugador, Personaje rival) {
		if(!comprobarPH(jugador, rival))
			return false;
		for (int i = 0; i < 5; i++) {
			daño = dañoMinimo + Math.random() * (dañoMaximo - dañoMinimo);
			rival.serAtacado(daño);
		}
		jugador.setPuntosHabilidad((jugador.getPuntosHabilidad()-this.getCosto()<0)?0:jugador.getPuntosHabilidad()-this.getCosto());
		return true;
	}

	public String toString() {
		return "Rafaga";
	}

	@Override
	public String getDescripcionUso() {
		return "Realiza 5 disparos con daño variable entre  " + +dañoMinimo + " y " + dañoMaximo;
	}

}
