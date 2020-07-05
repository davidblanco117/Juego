package Juego.Habilidades;

import javax.swing.ImageIcon;

import Juego.Personajes.Personaje;

public class Bazuca extends Habilidad {
	
	
	private double da�o=80;
	
	public Bazuca() {
		this.imagen= new ImageIcon(getClass().getResource("/Imagenes/Bazuca.jpg"));
		this.setCosto(4);
	}

	@Override
	public boolean activar(Personaje jugador, Personaje rival) {
		if(!comprobarPH(jugador, rival))
			return false;
		rival.serAtacado(da�o);
		jugador.setPuntosHabilidad((jugador.getPuntosHabilidad()-this.getCosto()<0)?0:jugador.getPuntosHabilidad()-this.getCosto());
		return true;
	}

	
	@Override
	public String getDescripcionUso() {
		
		return "Lanza un potente disparo que realiza " + da�o + "de da�o";
	}
	
	public String toString() {
		return "Buzuca";
	}
	
}
