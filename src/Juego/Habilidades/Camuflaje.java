package Juego.Habilidades;

import java.text.DecimalFormat;

import javax.swing.ImageIcon;

import Juego.Personajes.Personaje;

public class Camuflaje extends Habilidad {
	
	private double aumentoDeVelocidad = 0.07;
	public Camuflaje() {
		this.imagen= new ImageIcon(getClass().getResource("/Imagenes/Camuflaje.jpg"));
		this.setCosto(2);
	}

	@Override
	public boolean activar(Personaje jugador, Personaje rival) {
		if(!comprobarPH(jugador, rival))
			return false;
		jugador.setVelocidad(Math.min(jugador.getVelocidad()+0.08,0.5));
		jugador.setPuntosHabilidad((jugador.getPuntosHabilidad()-this.getCosto()<0)?0:jugador.getPuntosHabilidad()-this.getCosto());
		return true;
	}
	
	
	
	public String toString() {
		return "Camuflaje";
	}

	@Override
	public String getDescripcionUso() {
		DecimalFormat formato= new DecimalFormat("#,##");
		return "Aumenta la velocidad en un " + formato.format(aumentoDeVelocidad *100) + "%";
	}
	
}
