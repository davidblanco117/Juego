package Juego.Habilidades;

import javax.swing.ImageIcon;

import Juego.Personajes.Personaje;

public class HabilidadVacia extends Habilidad {
	
	
	
	public HabilidadVacia() {
		this.imagen= new ImageIcon(getClass().getResource("/Imagenes/HabilidadVacia.jpg"));
		this.setCosto(0);
	}

	@Override
	public boolean activar(Personaje jugador, Personaje rival) {
		return false;
	}

	
	@Override
	public String getDescripcionUso() {
		
		return "Crea los items para poder activar esta habilidad";
	}
	
	public String toString() {
		return "Habilidad Bloqueada";
	}
	
}
