package Juego.Habilidades;

import javax.swing.ImageIcon;

import Juego.Personajes.Personaje;

public class Tesoro extends Habilidad {

	public Tesoro() {
		this.imagen = new ImageIcon(getClass().getResource("/Imagenes/Tesoro.jpg"));
		this.setCosto(4);
	}

	@Override
	public boolean activar(Personaje jugador, Personaje rival) {
		if (!comprobarPH(jugador, rival))
			return false;
		int indice0 = 0;
		for (Habilidad habil : jugador.getListaHabilidades()) {
			if (habil.toString() == this.toString()) {
				break;
			}
			indice0++;
		}
		ReclamarTesoro rt= new ReclamarTesoro();
		jugador.getHabilidadesActivadas().add(rt);
		jugador.getListaHabilidades().set(indice0, rt);
		jugador.setPuntosHabilidad((jugador.getPuntosHabilidad()-this.getCosto()<0)?0:jugador.getPuntosHabilidad()-this.getCosto());
		return true;
	}

	@Override
	public String getDescripcionUso() {

		return "Hace que acumules puntos de habilidad y salud cada vez que actives una habilidad hasta que los reclames";

	}

	public String toString() {
		return "Tesoro";
	}



	
}