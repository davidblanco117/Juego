package Juego.Habilidades;

import javax.swing.ImageIcon;

import Juego.Personajes.Personaje;

public class Sanacion extends Habilidad {

	private double puntosDeCuracion = 45;

	public Sanacion() {
		this.imagen = new ImageIcon(getClass().getResource("/Imagenes/Sanacion.png"));
		this.setCosto(4);
	}

	@Override
	public boolean activar(Personaje jugador, Personaje rival) {
		if (!comprobarPH(jugador, rival))
			return false;

		jugador.setSalud(Math.min(jugador.getSalud() + puntosDeCuracion, jugador.getSaludMaxima()));	
		jugador.setPuntosHabilidad(Math.max(jugador.getPuntosHabilidad() - this.getCosto(), 0));
		
		return true;
	}

	@Override
	public String getDescripcionUso() {

		return "Lanza un hechizo de curacion que recupera  " + puntosDeCuracion + " puntos de salud al jugador";
	}

	public String toString() {
		return "Sanacion";
	}

}
