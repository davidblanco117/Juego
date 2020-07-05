package Juego.Habilidades;

import javax.swing.ImageIcon;

import Juego.Personajes.Personaje;

public class Escopetazo extends Habilidad {
	
	private double daño = 42;
	public Escopetazo() {
		this.imagen= new ImageIcon(getClass().getResource("/Imagenes/Escopeta.png"));
		this.setCosto(3);
	}

	@Override
	public boolean activar(Personaje jugador, Personaje rival) {
		if(!comprobarPH(jugador, rival))
			return false;
		rival.serAtacado(daño);
		jugador.setPuntosHabilidad((jugador.getPuntosHabilidad()-this.getCosto()<0)?0:jugador.getPuntosHabilidad()-this.getCosto());
		return true;
	}
	
	
	
	public String toString() {
		return "Escopetazo";
	}

	@Override
	public String getDescripcionUso() {
		
		return "Realiza un disparo con la escopeta que provoca " + 
					+ daño + " puntos de daño";
	}
	
}
