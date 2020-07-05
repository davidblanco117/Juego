package Juego.Habilidades;

import javax.swing.ImageIcon;

import Juego.Personajes.Personaje;

public class Castigo extends Habilidad {
	
	private double daño = 50;
	public Castigo() {
		this.imagen= new ImageIcon(getClass().getResource("/Imagenes/Castigo.jpeg"));
		this.setCosto(0);
	}

	@Override
	public boolean activar(Personaje jugador, Personaje rival) {
		int ph=jugador.getPuntosHabilidad();
		if(this.getCosto()>ph)
			return false;
		double saludFinal= jugador.getSalud()-daño;
		if(saludFinal<=0)
			return false;	
		jugador.setSalud(saludFinal);
		jugador.setPuntosHabilidad(Math.min(jugador.getPuntosHabilidad()+3,10));
		jugador.setPuntosHabilidad((jugador.getPuntosHabilidad()-this.getCosto()<0)?0:jugador.getPuntosHabilidad()-this.getCosto());
		return true;
	}

	
	@Override
	public String getDescripcionUso() {		
		return "Recibes "+ daño + " puntos de daño a cambio de 3 puntos de habilidad"; 
	}
	
	public String toString() {
		return "Castigo";
	}
	
}