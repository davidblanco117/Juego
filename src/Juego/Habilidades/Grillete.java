package Juego.Habilidades;

import java.text.DecimalFormat;

import javax.swing.ImageIcon;

import Juego.Personajes.Personaje;

public class Grillete extends Habilidad {
	
	private double reduccionDeVelocidad = 0.06;
	public Grillete() {
		this.imagen= new ImageIcon(getClass().getResource("/Imagenes/Grillete.jpg"));
		this.setCosto(2);
	}

	@Override
	public boolean activar(Personaje jugador, Personaje rival) {
		if(!comprobarPH(jugador, rival))
			return false;
		rival.setVelocidad(Math.max(rival.getVelocidad()-reduccionDeVelocidad,0));
		jugador.setPuntosHabilidad((jugador.getPuntosHabilidad()-this.getCosto()<0)?0:jugador.getPuntosHabilidad()-this.getCosto());
		return true;
	}
	
	
	
	public String toString() {
		return "Grillete";
	}

	@Override
	public String getDescripcionUso() {
		DecimalFormat formato2 = new DecimalFormat("#.##");
		return "Reduce la velocidad del rival en un " + formato2.format(reduccionDeVelocidad*100) + "%";
	}
	
}