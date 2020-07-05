package Juego.Habilidades;

import java.text.DecimalFormat;

import javax.swing.ImageIcon;

import Juego.Personajes.Personaje;

public class Regenerar extends Habilidad {
	
	private double aumentoAtaque=3;
	private double aumentoDefensa=0.04;
	private double aumentoVelocidad=0.04;
	private double aumentoSalud=30;
	
	
	
	public Regenerar() {
		this.imagen= new ImageIcon(getClass().getResource("/Imagenes/Regenerar.png"));
		this.setCosto(4);
	}

	@Override
	public boolean activar(Personaje jugador, Personaje rival) {
		if(!comprobarPH(jugador, rival))
			return false;
		jugador.setAtaque(jugador.getAtaque()+1.5);
		jugador.setDefensa(Math.min(jugador.getDefensa()+0.04,0.5));
		jugador.setVelocidad(Math.min(jugador.getVelocidad()+0.04,0.5));
		jugador.setSalud(Math.min(jugador.getSalud()+30,jugador.getSaludMaxima()));
		jugador.setPuntosHabilidad(Math.max(jugador.getPuntosHabilidad()-this.getCosto(), 0));
		return true;
	}
	
	
	@Override
	public String getDescripcionUso() {
		DecimalFormat f = new DecimalFormat("#,##");
		return "Aumenta ataque en " + f.format(aumentoAtaque) + " puntos, defensa en un " + f.format(aumentoDefensa*100) + "%, "
				+ "velocidad en un "+ f.format(aumentoVelocidad*100) + "% y salud en " + f.format(aumentoSalud) + " puntos";
	}
	
	
	public String toString() {
		return "Regenerar";
	}
	
}
